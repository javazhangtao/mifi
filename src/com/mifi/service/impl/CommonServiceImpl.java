package com.mifi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifi.common.Dictionary;
import com.mifi.dao.mifi.AttentionUserMapper;
import com.mifi.dao.mifi.CityMapper;
import com.mifi.dao.mifi.FlowgoldDetailMapper;
import com.mifi.dao.mifi.MessageMapper;
import com.mifi.dao.mifi.PublicNumberMapper;
import com.mifi.dao.mifi.SoftwareMapper;
import com.mifi.dao.mifi.UserFlowgoldMapper;
import com.mifi.dao.mifi.UserMapper;
import com.mifi.dto.AttentionUserDto;
import com.mifi.dto.CityDto;
import com.mifi.dto.FlowgoldDayDetailDto;
import com.mifi.dto.FlowgoldDetailDto;
import com.mifi.dto.MifiSoftware;
import com.mifi.dto.ResponceInfo;
import com.mifi.dto.Statistics;
import com.mifi.emay.SMSSender;
import com.mifi.po.mifi.City;
import com.mifi.po.mifi.Expense;
import com.mifi.po.mifi.FlowgoldDetail;
import com.mifi.po.mifi.Message;
import com.mifi.po.mifi.PublicNumber;
import com.mifi.po.mifi.Software;
import com.mifi.po.mifi.User;
import com.mifi.po.mifi.UserFlowgold;
import com.mifi.service.CommonService;
import com.mifi.utils.DateUtils;
import com.mifi.utils.InviteCodeBuilder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//import cn.emay.sdk.client.api.Client;

@Service
public class CommonServiceImpl implements CommonService {

	private static Logger log = Logger.getLogger(CommonServiceImpl.class);
	@Autowired
	UserMapper userMapper;
	@Autowired
	CityMapper cityMapper;
	@Autowired
	SoftwareMapper softwareMapper;
	@Resource(name="jedisPool")
	JedisPool jedisPool;
	@Autowired
	UserFlowgoldMapper userFlowgoldMapper;
	@Autowired
	FlowgoldDetailMapper flowgoldDetailMapper;
	@Autowired
	PublicNumberMapper publicNumberMapper;
	@Autowired
	AttentionUserMapper attentionUserMapper;
	@Autowired
	MessageMapper messageMapper;
	
	@Override
	public ResponceInfo authCode(String phone) {
		ResponceInfo responceInfo = new ResponceInfo();
		// 判断手机号是否已经注册过
		User user = userMapper.getUserByPhone(phone);
		if(user != null){
			responceInfo.setCode(Dictionary.CODE_USER_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_EXIST);
			return responceInfo;
		}
		
		// 生成验证码
		int authCode = (int) (Math.random()*9000+1000);
		Jedis jedis = jedisPool.getResource();
		if(jedis.get(phone)!=null){
			authCode = Integer.valueOf(jedis.get(phone));
		}
//		SMSSender.sendSMSForAuthCode(phone, authCode);
		jedis.setex(phone, Dictionary.REDIS_TIMEOUT, String.valueOf(authCode));
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setMessage("验证码已发送，请注意查收");
		return responceInfo;
	}
	
	@Override
	public ResponceInfo authCodeForRecoverPass(String phone) {
		ResponceInfo responceInfo = new ResponceInfo();
		// 判断手机号是否存在
		User user = userMapper.getUserByPhone(phone);
		if(user == null){
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			return responceInfo;
		}
		
		// 生成验证码
		int authCode = (int) (Math.random()*9000+1000);
		Jedis jedis = jedisPool.getResource();
		if(jedis.get(phone)!=null){
			authCode = Integer.valueOf(jedis.get(phone));
		}
//		SMSSender.sendSMSForAuthCode(phone, authCode);
		jedis.setex(phone, Dictionary.REDIS_TIMEOUT, String.valueOf(authCode));
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setMessage("验证码已发送，请注意查收");
		return responceInfo;
	}

	@Override
	public ResponceInfo register(String phone, String authcode, String password, String invitecode) {
		ResponceInfo responceInfo = new ResponceInfo();
		if(phone==null||authcode==null||password==null||"".equals(password)||"".equals(phone)||"".equals(password)){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		
		// 判断手机号是否是11位
		if(phone.length() != 11){
			responceInfo.setCode(Dictionary.CODE_PARAM_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_ERROR);
			return responceInfo;
		}
		
		// 判断手机号是否已经注册过
		User regUser = userMapper.getUserByPhone(phone);
		if(regUser != null){
			responceInfo.setCode(Dictionary.CODE_USER_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_EXIST);
			return responceInfo;
		}
		
		// 1，判断验证码是否正确；(authcode)
		Jedis jedis = jedisPool.getResource();
		String jedisPhone = jedis.get(phone);
		System.out.println(jedisPhone);
		if(jedisPhone==null){
			responceInfo.setCode(Dictionary.CODE_AUTH_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_AUTH_NOT_FOUND);
			return responceInfo;
		}
		if(!authcode.equals(jedisPhone)){
			responceInfo.setCode(Dictionary.CODE_AUTH_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_AUTH_ERROR);
			return responceInfo;
		}
		
		// 判断邀请码是否存在；
		User inviteUser = null;
		if(invitecode != null && !"".equals(invitecode)){
			inviteUser = userMapper.getUserByInvite(invitecode);
			if(inviteUser == null){
				responceInfo.setCode(Dictionary.CODE_INVITECODE_ERROR);
				responceInfo.setMessage(Dictionary.MESSAGE_INVITECODE_ERROR);
				return responceInfo;
			}
		}
		
		// 插入user对象
		User user = new User(phone,password,new Date());
		if(inviteUser != null){
			user.setRecommender(inviteUser.getId());
		}
		int i = userMapper.insert(user);
		if(i>0){
			User userUpdate = userMapper.getUserByPhone(phone);
			String myInviteCode = InviteCodeBuilder.getInviteCode(userUpdate.getId());
			userUpdate.setInviteCode(myInviteCode);
			int u = userMapper.update(userUpdate);
			if(u > 0){
				responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_NORMAL);
				jedis.del(phone);
			} else {
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
			}
		}else{
			responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
			responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
		}
		
		return responceInfo;
	}

	@Override
	public ResponceInfo recoverPass(String phone, String authcode, String password) {
		ResponceInfo responceInfo = new ResponceInfo();
		
		if(phone==null||authcode==null||password==null){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		
		// 1，判断验证码是否正确；(authcode)
		Jedis jedis = jedisPool.getResource();
		String jedisPhone = jedis.get(phone);
		if(jedisPhone==null){
			responceInfo.setCode(Dictionary.CODE_AUTH_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_AUTH_NOT_FOUND);
			return responceInfo;
		}
		if(!authcode.equals(jedisPhone)){
			responceInfo.setCode(Dictionary.CODE_AUTH_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_AUTH_ERROR);
			return responceInfo;
		}
		
		User user = userMapper.getUserByPhone(phone);
		if(user != null){
			user.setPassword(password);
			int result = userMapper.update(user);
			if(result > 0){
				responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_NORMAL);
			}else{
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
			}
		} else{
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
		}
		
		return responceInfo;
	}

	@Override
	public ResponceInfo flowDetail(String userId) {
		log.info("service 我的流量币详情 method:flowDetail 参数 userId："+userId);
		ResponceInfo responceInfo = new ResponceInfo();  
		if(userId==null||userId.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
		if(userFlowgold == null){
			responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
			responceInfo.setMessage(Dictionary.MESSAGE_RESOURCE_NOT_FOUNT);
		}else{
			Date date = new Date();
			Statistics statistics = new Statistics();
			statistics.setToday(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getDayBegin(date), date));
			statistics.setYesterday(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getYesterdayBegin(date), DateUtils.getYesterdayEnd(date)));
			statistics.setWeek(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.curWeekBegin(date), date));
			statistics.setLastweek(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.lastWeekBegin(date), DateUtils.lastWeekEnd(date)));
			statistics.setMonth(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.curMonthBegin(date), date));
			statistics.setLastmonth(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.lastMonthBegin(date), DateUtils.lastMonthEnd(date)));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cashcollect", userFlowgold.getBalance());
			map.put("collect", statistics);
			
			int flag = 1;
			int dayCount = 1;
			List<FlowgoldDetailDto> flowgoldList = new ArrayList<FlowgoldDetailDto>();
			while (flag <= Dictionary.FLOWGOLD_DETAIL_NUMBER) {
				List<FlowgoldDayDetailDto> daydetails = new ArrayList<FlowgoldDayDetailDto>();
				
				Date dayBegin = DateUtils.getNdayBegin(date, dayCount);
				Date dayEnd = DateUtils.getNdayEnd(date, dayCount);
				
				List<FlowgoldDetail> dayList = flowgoldDetailMapper.selectFlowgoldDetailByDay(userFlowgold.getId(), dayBegin, dayEnd);
				if(dayList!=null&&dayList.size()>0){
					for(FlowgoldDetail flowgoldDetail:dayList){
						FlowgoldDayDetailDto flowgoldDayDetailDto = new FlowgoldDayDetailDto();
						flowgoldDayDetailDto.setDescribe(flowgoldDetail.getDescribes());
						flowgoldDayDetailDto.setCash(flowgoldDetail.getFigure());
						daydetails.add(flowgoldDayDetailDto);
						flag++;
						if(flag>Dictionary.FLOWGOLD_DETAIL_NUMBER){
							break;
						}
					}
					FlowgoldDetailDto flowgoldDetailDto = new FlowgoldDetailDto();
					flowgoldDetailDto.setDatetime(DateUtils.simpleDateToString(dayBegin));
					flowgoldDetailDto.setDaydetails(daydetails);
					flowgoldList.add(flowgoldDetailDto);
				}
				
				dayCount++;
				if(dayCount>30){
					responceInfo.setMessage("没有更多的数据了！");
					break;
				}
			}
			
			map.put("detail", flowgoldList);
			
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(map);
		}
		
		
		return responceInfo;
	}

	@Override
	public ResponceInfo city() {
		log.info("通用接口，获取注册城市列表service");
		ResponceInfo responceInfo = new ResponceInfo();
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		List<City> list = cityMapper.selectAll();
		List<CityDto> listDto = new ArrayList<CityDto>();
		for(City city:list){
			CityDto cityDto = new CityDto(city);
			listDto.add(cityDto);
		}
		responceInfo.setData(listDto);
		return responceInfo;
	}
	
	@Override
	public ResponceInfo softwareInfo(String type) {
		log.info("通用接口，获取最新软件版本service 参数type:"+type);
		ResponceInfo responceInfo = new ResponceInfo();
		if(type==null){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		Software software = softwareMapper.getBestNewSoft(Integer.valueOf(type));
		if(software!=null){
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(new MifiSoftware(software));
		}else{
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(new MifiSoftware());
		}
		return responceInfo;
	}
	
	@Override
	public ResponceInfo getUserAttention(String userId) {
		ResponceInfo responceInfo = new ResponceInfo();
		List<AttentionUserDto> attentionUserDtos = new ArrayList<AttentionUserDto>();
		List<PublicNumber> publicNumbers = publicNumberMapper.getAll();
		for(int i = 0; i < publicNumbers.size(); i++){
			PublicNumber number = publicNumbers.get(i);
			int attentionUser = attentionUserMapper.getInfoByNumber(number.getNumber(),Long.valueOf(userId));
			AttentionUserDto attentionUserDto = new AttentionUserDto();
			if(attentionUser > 0){
				attentionUserDto.setPhoto(number.getPhoto());
				attentionUserDto.setName(number.getName());
				attentionUserDto.setNumber(number.getNumber());
				attentionUserDto.setIsAttention(1);
			} else {
				attentionUserDto.setPhoto(number.getPhoto());
				attentionUserDto.setName(number.getName());
				attentionUserDto.setNumber(number.getNumber());
				attentionUserDto.setIsAttention(0);
			}
			attentionUserDtos.add(attentionUserDto);
		}
		
		responceInfo.setCode(200);
		responceInfo.setData(attentionUserDtos);
		
		return responceInfo;
	}

	@Override
	public ResponceInfo attentionPublic(String phone) {
		ResponceInfo responceInfo = new ResponceInfo();
		Integer rewardCount = 10;
		Long rewardType = 10L;
		// 判断手机号合法
		if(phone == null || "".equals(phone) || phone.length() != 11){
			Map<String, String> map = new HashMap<String, String>();
			map.put("rewardcount", rewardCount.toString());
			map.put("phone", phone);
			responceInfo.setCode(Dictionary.CODE_PARAM_ERROR);
			responceInfo.setData(map);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_ERROR);
			return responceInfo;
		}
		
		// 判断手机号码是否注册过
		User user = userMapper.getUserByPhone(phone);
		if(user == null){
			// 注册
			User insertUser = new User();
			insertUser.setPhone(phone);
			int insert = userMapper.insert(insertUser);
			if(insert <= 0){
				Map<String, String> map = new HashMap<String, String>();
				map.put("rewardcount", rewardCount.toString());
				map.put("phone", phone);
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setData(map);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
				return responceInfo;
			}
		} 
		
		User user2 = userMapper.getUserByPhone(phone);
		// 判断是否领过关注公众号的奖励
		UserFlowgold flowgold = userFlowgoldMapper.getUserFlowgold(user2.getId());
		if(flowgold != null){
			FlowgoldDetail detail = flowgoldDetailMapper.getFlowgoldByFlowgoldId(flowgold.getId(), rewardType);
			if(detail != null){
				Map<String, String> map = new HashMap<String, String>();
				map.put("rewardcount", rewardCount.toString());
				map.put("phone", phone);
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setData(map);
				responceInfo.setMessage("您已经领过奖励");
				return responceInfo;
			}
		}
		
		// 发放奖励
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(user2.getId());
		if(userFlowgold != null){
			Long l = userFlowgold.getBalance() + 10;
			userFlowgold.setBalance(l);
			int up = userFlowgoldMapper.update(userFlowgold);
			if(up > 0){
				UserFlowgold userFlowgolds = userFlowgoldMapper.getUserFlowgold(user2.getId());
				FlowgoldDetail flowgoldDetail = new FlowgoldDetail();
				flowgoldDetail.setFlowgoldId(userFlowgolds.getId());
				flowgoldDetail.setCreateTime(new Date());
				flowgoldDetail.setExpenseType(rewardType);
				flowgoldDetail.setFigure(rewardCount);
				int ins = flowgoldDetailMapper.insert(flowgoldDetail);
				if(ins > 0){
					Map<String, String> map = new HashMap<String, String>();
					map.put("rewardcount", rewardCount.toString());
					map.put("phone", phone);
					responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
					responceInfo.setData(map);
				} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("rewardcount", rewardCount.toString());
					map.put("phone", phone);
					responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
					responceInfo.setData(map);
					responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
					return responceInfo;
				}
			}
		} else {
			UserFlowgold userFlowgoldInsert = new UserFlowgold();
			userFlowgoldInsert.setUserId(user2.getId());
			userFlowgoldInsert.setCreateTime(new Date());
			userFlowgoldInsert.setBalance(10L);
			int in = userFlowgoldMapper.insert(userFlowgoldInsert);
			if(in > 0){
				UserFlowgold userFlowgolds = userFlowgoldMapper.getUserFlowgold(user2.getId());
				FlowgoldDetail flowgoldDetail = new FlowgoldDetail();
				flowgoldDetail.setFlowgoldId(userFlowgolds.getId());
				flowgoldDetail.setCreateTime(new Date());
				flowgoldDetail.setExpenseType(10L);
				flowgoldDetail.setFigure(10);
				int ins = flowgoldDetailMapper.insert(flowgoldDetail);
				if(ins > 0){
					Map<String, String> map = new HashMap<String, String>();
					map.put("rewardcount", rewardCount.toString());
					map.put("phone", phone);
					responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
					responceInfo.setData(map);
					
				} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("rewardcount", rewardCount.toString());
					map.put("phone", phone);
					responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
					responceInfo.setData(map);
					responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
					return responceInfo;
				}
			}
		}
		
		return responceInfo;
	}

	@Override
	public ResponceInfo cashDetail(String userId) {
		log.info("service 我的收益 method:cashDetail 参数 userId："+userId);
		ResponceInfo responceInfo = new ResponceInfo();  
		if(userId==null||userId.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
		if(userFlowgold == null){
			responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
			responceInfo.setMessage(Dictionary.MESSAGE_RESOURCE_NOT_FOUNT);
		}else{
			Date date = new Date();
			Statistics statistics = new Statistics();
			statistics.setToday(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getDayBegin(date), date));
			statistics.setYesterday(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.getYesterdayBegin(date), DateUtils.getYesterdayEnd(date)));
			statistics.setWeek(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.curWeekBegin(date), date));
			statistics.setLastweek(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.lastWeekBegin(date), DateUtils.lastWeekEnd(date)));
			statistics.setMonth(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.curMonthBegin(date), date));
			statistics.setLastmonth(flowgoldDetailMapper.getFlowgoldCount(userFlowgold.getId(), DateUtils.lastMonthBegin(date), DateUtils.lastMonthEnd(date)));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cashcollect", userFlowgold.getBalance());
			map.put("collect", statistics);
			
			int flag = 1;
			int dayCount = 1;
			List<FlowgoldDetailDto> flowgoldList = new ArrayList<FlowgoldDetailDto>();
			while (flag <= Dictionary.FLOWGOLD_DETAIL_NUMBER) {
				List<FlowgoldDayDetailDto> daydetails = new ArrayList<FlowgoldDayDetailDto>();
				
				Date dayBegin = DateUtils.getNdayBegin(date, dayCount);
				Date dayEnd = DateUtils.getNdayEnd(date, dayCount);
//				int daycollect = 0;
				
				List<FlowgoldDetail> dayList = flowgoldDetailMapper.selectFlowgoldDetailByDay(userFlowgold.getId(), dayBegin, dayEnd);
				if(dayList!=null&&dayList.size()>0){
					for(FlowgoldDetail flowgoldDetail:dayList){
						FlowgoldDayDetailDto flowgoldDayDetailDto = new FlowgoldDayDetailDto();
						flowgoldDayDetailDto.setDescribe(flowgoldDetail.getDescribes());
						flowgoldDayDetailDto.setCash(flowgoldDetail.getFigure());
						daydetails.add(flowgoldDayDetailDto);
//						daycollect+=flowgoldDetail.getFigure();
						flag++;
						if(flag>Dictionary.FLOWGOLD_DETAIL_NUMBER){
							break;
						}
					}
					FlowgoldDetailDto flowgoldDetailDto = new FlowgoldDetailDto();
					flowgoldDetailDto.setDatetime(DateUtils.simpleDateToString(dayBegin));
					flowgoldDetailDto.setDaydetails(daydetails);
//					flowgoldDetailDto.setStatus(1);
//					flowgoldDetailDto.setDaycollect(daycollect);
					flowgoldList.add(flowgoldDetailDto);
				}
				
				dayCount++;
				if(dayCount>30){
					if(flowgoldList.size()>0){
//						flowgoldList.get(flowgoldList.size()-1).setStatus(0);
					}
					responceInfo.setMessage("没有更多的数据了！");
					break;
				}
			}
			
			map.put("detail", flowgoldList);
			
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(map);
		}
		
		
		return responceInfo;
	}

	@Override
	public ResponceInfo messages(String userId) {
		log.info("service 通用接口获取一条的消息 method:messages 参数 userID:"+userId);
		ResponceInfo responceInfo = new ResponceInfo();  
		if(userId==null||userId.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		Date date = new Date();
		List<Message> list = messageMapper.selectMessageListByDay(Long.valueOf(userId), DateUtils.getDayBegin(date), DateUtils.getDayEnd(date));
		if(list == null){
			list = new ArrayList<Message>();
		}
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(list);
		return responceInfo;
	}

	@Override
	public ResponceInfo authCodeNoReq(String phone) {
		ResponceInfo responceInfo = new ResponceInfo();
		
		// 生成验证码
		int authCode = (int) (Math.random()*9000+1000);
		Jedis jedis = jedisPool.getResource();
		if(jedis.get(phone)!=null){
			authCode = Integer.valueOf(jedis.get(phone));
		}
//		SMSSender.sendSMSForAuthCode(phone, authCode);
		jedis.setex(phone, Dictionary.REDIS_TIMEOUT, String.valueOf(authCode));
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setMessage("验证码已发送，请注意查收");
		return responceInfo;
	}

}
