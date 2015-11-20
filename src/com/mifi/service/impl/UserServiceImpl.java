package com.mifi.service.impl;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifi.common.Dictionary;
import com.mifi.dao.mifi.CityMapper;
import com.mifi.dao.mifi.FlowgoldDetailMapper;
import com.mifi.dao.mifi.MessageMapper;
import com.mifi.dao.mifi.PraiseMapper;
import com.mifi.dao.mifi.UserFlowgoldMapper;
import com.mifi.dao.mifi.UserMapper;
import com.mifi.dao.mifi.UserRegistrationMapper;
import com.mifi.dto.LoginUserInfo;
import com.mifi.dto.ResponceInfo;
import com.mifi.dto.UserDetail;
import com.mifi.dto.UserHomeInfo;
import com.mifi.po.mifi.FlowgoldDetail;
import com.mifi.po.mifi.Praise;
import com.mifi.po.mifi.User;
import com.mifi.po.mifi.UserFlowgold;
import com.mifi.po.mifi.UserRegistration;
import com.mifi.service.UserService;
import com.mifi.utils.DateUtils;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	UserMapper userMapper;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	UserFlowgoldMapper userFlowgoldMapper;
	@Autowired
	PraiseMapper praiseMapper;
	@Autowired
	CityMapper cityMapper;
	@Autowired
	FlowgoldDetailMapper flowgoldDetailMapper;
	@Autowired
	UserRegistrationMapper userRegistrationMapper;
	
	@Override
	public ResponceInfo getUserInfo(String userId) {
		ResponceInfo responceInfo = new ResponceInfo();
		User userAttr = userMapper.getUserById(Long.valueOf(userId));
		UserDetail userDetail = new UserDetail();
		
		if(userAttr != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String cityName = cityMapper.getCityById(userAttr.getCity());
			
			userDetail.setUserId(Long.valueOf(userId));
			userDetail.setNickName(userAttr.getNickName());
			userDetail.setGender(userAttr.getGender());
			userDetail.setCityId(userAttr.getCity());
			userDetail.setCityName(cityName);
			userDetail.setCarNum(userAttr.getCarNumber());
			userDetail.setDrivingCard(userAttr.getDrivingCard());
			userDetail.setCardId(userAttr.getCardId());
			userDetail.setDrivingLicense(userAttr.getDrivingLicense());
			userDetail.setIntro(userAttr.getIntro());
			userDetail.setUserType(userAttr.getUserType());
			if(userAttr.getBirth() != null && !"".equals(userAttr.getBirth())){
				userDetail.setBirth(dateFormat.format(userAttr.getBirth()));
			}
			
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(userDetail);
		} else{
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
		}
		
		return responceInfo;
	}

	@Override
	public ResponceInfo editInfo(String userId, String nickname, String gender, String birth, String city,
			String cardid, String intro, String carnum, String drivingcard, String drivinglicense, File head) {
		ResponceInfo responceInfo = new ResponceInfo();
		User user = userMapper.getUserById(Long.valueOf(userId));
		
		// 将生日字符串转换为date类型
		if(birth != null && !"".equals(birth)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			Date date = new Date();
		    try {
				date = sdf.parse(birth);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
		    user.setBirth(date);
		}
		if(gender != null && !"".equals(gender)){
			user.setGender(Integer.valueOf(gender));
		}
		if(city != null && !"".equals(city)){
			user.setCity(Long.valueOf(city));
		}
		user.setNickName(nickname);
		user.setCardId(cardid);
		user.setIntro(intro);
		user.setCarNumber(carnum);
		user.setDrivingCard(drivingcard);
		user.setDrivingLicense(drivinglicense);
		
		int i = userMapper.update(user);
		if(i > 0){
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setMessage("true");
		}else{
			responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
			responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
		}
		
		return responceInfo;
	}

	@Override
	public ResponceInfo login(String phone, String password) {
		log.info("登录 method:login 参数 phone："+phone+" password:"+password);
		ResponceInfo responceInfo = new ResponceInfo();
		if(phone==null||password==null){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		User user = new User(phone, password);
		user = userMapper.getUser(user);
		if(user != null){
			// 剩余流量币个数
			int surplusflow = 0;
			UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(user.getId());
			if(userFlowgold != null){
				surplusflow = userFlowgold.getBalance().intValue();
			}
			
			List<UserRegistration> userRegistrationToday = userRegistrationMapper.getLastRegist(user.getId(), new Date());
			List<UserRegistration> userRegistrationYestoday = userRegistrationMapper.getLastRegist(user.getId(), DateUtils.getNDaysBefore(new Date(), 1));
			
			// 今天是否签到,默认未签到
			int isResign = 0;   
			// 连续签到天数
			int consequentday = 0;
			if(userRegistrationToday != null && userRegistrationToday.size() > 0){
				isResign = 1;
				consequentday = userRegistrationToday.get(0).getDays();
			} else if(userRegistrationYestoday != null && userRegistrationYestoday.size() > 0) {
				consequentday = userRegistrationYestoday.get(0).getDays();
			}
			
			// 消息参数
			int informationnum = messageMapper.selectUnreadMessageCount(user.getId());
			
			// 徒弟个数
			int apprenticecount = 19;
			
			LoginUserInfo userInfo = new LoginUserInfo();
			userInfo.setHead(user.getHead());
			userInfo.setNickname(user.getNickName());
			userInfo.setIntro(user.getIntro());
			userInfo.setApprenticecount(apprenticecount);
			userInfo.setConsequentday(consequentday);
			userInfo.setInformationnum(informationnum);
			userInfo.setIsresign(isResign);
			userInfo.setSurplusflow(surplusflow);
			userInfo.setUserId(user.getId());
			
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(userInfo);
		}else{
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
		}
		return responceInfo;
	}

	@Override
	public ResponceInfo myHomeInfo(String userId) {
		log.info("登录 后获取首页数据method:myHomeInfo 参数 userId："+userId);
		ResponceInfo responceInfo = new ResponceInfo();
		User user = userMapper.getUserById(Long.valueOf(userId));
		if(user != null){
			// 剩余流量币个数
			int surplusflow = 0;
			UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(user.getId());
			if(userFlowgold != null){
				surplusflow = userFlowgold.getBalance().intValue();
			}
			
			List<UserRegistration> userRegistrationToday = userRegistrationMapper.getLastRegist(user.getId(), new Date());
			List<UserRegistration> userRegistrationYestoday = userRegistrationMapper.getLastRegist(user.getId(), DateUtils.getNDaysBefore(new Date(), 1));
			
			// 今天是否签到,默认未签到
			int isResign = 0;   
			// 连续签到天数
			int consequentday = 0;
			if(userRegistrationToday != null  && userRegistrationToday.size() > 0){
				isResign = 1;
				consequentday = userRegistrationToday.get(0).getDays();
			} else if(userRegistrationYestoday != null && userRegistrationYestoday.size() > 0) {
				consequentday = userRegistrationYestoday.get(0).getDays();
			}
			
			// 消息参数
			int informationnum = messageMapper.selectUnreadMessageCount(user.getId());
			
			// 徒弟个数
			int apprenticecount = 19;
			
			LoginUserInfo userInfo = new LoginUserInfo();
			userInfo.setHead(user.getHead());
			userInfo.setNickname(user.getNickName());
			userInfo.setIntro(user.getIntro());
			userInfo.setApprenticecount(apprenticecount);
			userInfo.setConsequentday(consequentday);
			userInfo.setInformationnum(informationnum);
			userInfo.setIsresign(isResign);
			userInfo.setSurplusflow(surplusflow);
			userInfo.setUserId(user.getId());
			
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(userInfo);
		}else{
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
		}
		return responceInfo;
	}

	@Override
	public ResponceInfo top(String userId, String apMac, String driverId) {
		ResponceInfo responceInfo = new ResponceInfo();
		if(userId==null||apMac==null||driverId==null){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		
		Praise praise = new Praise(Long.valueOf(userId), apMac, Long.valueOf(driverId));
		praise.setCreateTime(new Date());
		int flag = praiseMapper.insert(praise);
		if(flag > 0){
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setMessage("true");
		}else{
			responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
			responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
		}
		
		return responceInfo;
	}

	@Override
	public ResponceInfo homeInfo(String userId) {
		ResponceInfo responceInfo = new ResponceInfo();
		if(userId == null || "".equals(userId)){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		
		User user = userMapper.getUserById(Long.valueOf(userId));
		if(user == null){
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			return responceInfo;
		} else {
			// 返回数据
			Long flowgoldBalance = 0L;
			int yestodayFlowgoldCount = 0;
			int todayFlowgoldCount = 0;
			UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
			if(userFlowgold != null){
				// 我的流量币个数
				flowgoldBalance = userFlowgold.getBalance();
				// 昨日流量币
				yestodayFlowgoldCount = 
						flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getYesterdayBegin(new Date()), DateUtils.getYesterdayEnd(new Date())) == null ? 0 : 
							flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getYesterdayBegin(new Date()), DateUtils.getYesterdayEnd(new Date()));
				// 今日流量币
				todayFlowgoldCount = 
						flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getDayBegin(new Date()), DateUtils.getDayEnd(new Date())) == null ? 0 : 
							flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getDayBegin(new Date()), DateUtils.getDayEnd(new Date()));
			}
			// TODO 昨日收徒
			int yestodayApprentice = 10;
			
			UserHomeInfo userHomeInfo = new UserHomeInfo();
			userHomeInfo.setUserId(Long.valueOf(userId));
			userHomeInfo.setFlowgoldbalance(flowgoldBalance);
			userHomeInfo.setYestodayFlowgoldCount(yestodayFlowgoldCount);
			userHomeInfo.setTodayFlowgoldCount(todayFlowgoldCount);
			userHomeInfo.setYestodayApprentice(yestodayApprentice);
			
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(userHomeInfo);
		}
		
		return responceInfo;
	}

	@Override
	public ResponceInfo userCheckIn(String userId) {
		ResponceInfo responceInfo = new ResponceInfo();
		if(userId == null || "".equals(userId)){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		
		User user = userMapper.getUserById(Long.valueOf(userId));
		if(user == null){
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			return responceInfo;
		} else {
			List<UserRegistration> userRegistrationToday = userRegistrationMapper.getLastRegist(Long.valueOf(userId), new Date());
			if(userRegistrationToday != null && userRegistrationToday.size() > 0){
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setMessage("用户已签到");
				return responceInfo;
			} else {
				List<UserRegistration> userRegistrationYestoday = userRegistrationMapper.getLastRegist(Long.valueOf(userId), DateUtils.getNDaysBefore(new Date(), 1));
				
				UserRegistration userRegistrationInsert = new UserRegistration();
				userRegistrationInsert.setUserId(Long.valueOf(userId));
				userRegistrationInsert.setDate(new Date());
				
				if(userRegistrationYestoday == null || userRegistrationYestoday.size() == 0){
					userRegistrationInsert.setDays(1);
				} else {
					userRegistrationInsert.setDays(userRegistrationYestoday.get(0).getDays() + 1);
				}
				
				int insert = userRegistrationMapper.insert(userRegistrationInsert);
				if(insert > 0){
					// 签到成功，发放流量币
					UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
					if (userFlowgold == null) {
						UserFlowgold flowgoldInsert = new UserFlowgold();
						flowgoldInsert.setUserId(user.getId());
						int insertFlowgold = userFlowgoldMapper.insert(flowgoldInsert);
						if (insertFlowgold > 0) {
							userFlowgold = userFlowgoldMapper.getUserFlowgold(user.getId());
						} else {
							responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
							responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
							return responceInfo;
						}
					}
					userFlowgold.setBalance(userFlowgold.getBalance() + 1L);
					int u = userFlowgoldMapper.update(userFlowgold);
					if(u <= 0){
						responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
						responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
						return responceInfo;
					}
					// 插入流量币记录表
					FlowgoldDetail flowgoldDetail = new FlowgoldDetail();
					flowgoldDetail.setExpenseType(11L);
					flowgoldDetail.setFigure(1);
					flowgoldDetail.setDescribes("签到奖励");
					flowgoldDetail.setFlowgoldId(userFlowgold.getId());
					int in = flowgoldDetailMapper.insert(flowgoldDetail);
					if(in <= 0){
						responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
						responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
						return responceInfo;
					} else {
						responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
					}
					
				} else {
					responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
					responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
					return responceInfo;
				}
			}
		}
		
		return responceInfo;
	}

}
