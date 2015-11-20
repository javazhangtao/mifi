package com.mifi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifi.common.Dictionary;
import com.mifi.dao.mifi.CityMapper;
import com.mifi.dao.mifi.DeviceMapper;
import com.mifi.dao.mifi.ExpenseMapper;
import com.mifi.dao.mifi.FlowgoldDetailMapper;
import com.mifi.dao.mifi.JoinDetailMapper;
import com.mifi.dao.mifi.MifiInternetMapper;
import com.mifi.dao.mifi.PraiseMapper;
import com.mifi.dao.mifi.TaskDetailMapper;
import com.mifi.dao.mifi.TaskItemMapper;
import com.mifi.dao.mifi.UserDeviceMapper;
import com.mifi.dao.mifi.UserFlowgoldMapper;
import com.mifi.dao.mifi.UserMapper;
import com.mifi.dto.FlowgoldDayDetailDto;
import com.mifi.dto.FlowgoldDetailDto;
import com.mifi.dto.LoginDriverInfo;
import com.mifi.dto.ResponceInfo;
import com.mifi.dto.Statistics;
import com.mifi.dto.UserDetail;
import com.mifi.po.mifi.Device;
import com.mifi.po.mifi.Expense;
import com.mifi.po.mifi.MifiInternet;
import com.mifi.po.mifi.TaskItem;
import com.mifi.po.mifi.User;
import com.mifi.po.mifi.UserDevice;
import com.mifi.po.mifi.UserFlowgold;
import com.mifi.service.DriverService;
import com.mifi.utils.DateUtils;

@Service
public class DriverServiceImpl implements DriverService{
	
	private static Logger log = Logger.getLogger(DriverServiceImpl.class);

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserDeviceMapper userDeviceMapper;
	@Autowired
	JoinDetailMapper joinDetailMapper;
	@Autowired
	TaskDetailMapper taskDetailMapper;
	@Autowired
	UserFlowgoldMapper userFlowgoldMapper;
	@Autowired
	DeviceMapper deviceMapper;
	@Autowired
	FlowgoldDetailMapper flowgoldDetailMapper;
	@Autowired
	PraiseMapper praiseMapper;
	@Autowired
	MifiInternetMapper mifiInternetMapper;
	@Autowired
	CityMapper cityMapper;
	@Autowired
	ExpenseMapper expenseMapper;
//	@Autowired
//	TaskItemMapper taskItemMapper;
	
	
	@Override
	public ResponceInfo login(String phone, String password) {
		log.info("service 司机登录 login 参数 phone："+phone+" password:"+password);
		ResponceInfo responceInfo = new ResponceInfo();
		if(phone==null||password==null||phone.equals("")||password.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		//TODO 效验手机号码是否符合要求
		
		User user = new User(phone, password);
		user = userMapper.getUserByPhone(phone);
		if(user!=null){
			if(user.getPassword()!=null&&!password.equals(user.getPassword())){
				responceInfo.setCode(Dictionary.CODE_USER_NOT_RIGHT);
				responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_RIGHT);
				return responceInfo;
			}
			//TODO 司机登录成功需要哪些操作
			/**
			 * 	connect	Integer	设备连接人数(OK)
				generalize	Integer	推广人数(ok)--通过验证码推广的用户
				expendflow	Long	消耗流量
				task	Integer	完成任务数(OK)
				cash	Integer	现金收益数
				voucher	Integer	抵用券数
				top	    Integer 司机获取的点赞
				userId	Long	司机用户Id(OK)

			 */
			LoginDriverInfo driverInfo = new LoginDriverInfo();
			
			Date date = new Date();
			// 先查询一键登录的记录，如果找到，再通过用户ID和设备ID去查询设备在线人数；设备在线人数是通过设备的加入链接个数减去断开链接个数
			UserDevice userDevice = userDeviceMapper.getAvailableUserDevice(user.getId());
			if(userDevice!=null){
				Integer connectUser = joinDetailMapper.getDeviceConnect(DateUtils.getDayBegin(date), user.getId(), userDevice.getDeviceId(), Dictionary.STATUS_NORMAL) ;
				Integer breakUser = joinDetailMapper.getDeviceConnect(DateUtils.getDayBegin(date), user.getId(), userDevice.getDeviceId(), Dictionary.STATUS_DISABLED) ;
				if(connectUser == null){
					connectUser = 0;
				}
				if(breakUser == null){
					breakUser = 0;
				}
				if(connectUser>=breakUser){
					driverInfo.setConnect(connectUser-breakUser);
				}else{
					driverInfo.setConnect(0);
				}
			}else{
				driverInfo.setConnect(0);
			}
			
			int generalize = userMapper.getUserGeneralizeTotal(user.getId());
			driverInfo.setGeneralize(generalize);
			
			Integer task =  taskDetailMapper.getUserTaskCount(user.getId());
			if(task == null){
				task = 0;
			}
			driverInfo.setTask(task);
			driverInfo.setTop(praiseMapper.getPraiseTotalCount(user.getId()));
			String cityName = cityMapper.getCityById(user.getCity());
			UserDetail userDetail = new UserDetail(user, cityName);
			driverInfo.setUserDetail(userDetail);
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(driverInfo);
			
			UUID tokenUuid = UUID.randomUUID();
			tokenUuid.toString();
		}else{
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
		}
		return responceInfo;
	}

	@Override
	public ResponceInfo cashDetail(String userId) {
		log.info("service 司机版我的收益 method:cashDetail 参数 userId："+userId);
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
			List<Expense> expenseList = expenseMapper.selectExpense();
			//TODO 查询日常任务的列表，在下面while中循环，找到每个任务的获取流量币在总值；2：判断所有总值都等于0，这放弃这一天，继续下一天
			while (flag <= Dictionary.FLOWGOLD_DETAIL_NUMBER) {
				List<FlowgoldDayDetailDto> daydetails = new ArrayList<FlowgoldDayDetailDto>();
				
				Date dayBegin = DateUtils.getNdayBegin(date, dayCount);
				Date dayEnd = DateUtils.getNdayEnd(date, dayCount);
				int daycollect = 0;
				for(Expense expense:expenseList){
					Integer flagDayCount = flowgoldDetailMapper.getFlowgoldCountByType(userFlowgold.getId(), expense.getId(), dayBegin, dayEnd);
					if(flagDayCount!=null&&flagDayCount>0){
						FlowgoldDayDetailDto flowgoldDayDetailDto = new FlowgoldDayDetailDto();
						flowgoldDayDetailDto.setDescribe(expense.getName());
						flowgoldDayDetailDto.setCash(flagDayCount);
						daydetails.add(flowgoldDayDetailDto);
						daycollect+=flagDayCount;
					}
				}
				if(daydetails.size()>0){
					FlowgoldDetailDto flowgoldDetailDto = new FlowgoldDetailDto();
					flowgoldDetailDto.setDatetime(DateUtils.simpleDateToString(dayBegin));
					flowgoldDetailDto.setDaydetails(daydetails);
//					flowgoldDetailDto.setStatus(1);
//					flowgoldDetailDto.setDaycollect(daycollect);
					flowgoldList.add(flowgoldDetailDto);
					flag++;
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
	public ResponceInfo deviceGeneralizeDetail(String userId) {
		log.info("service 司机版我的设备推广信息 method:deviceGeneralizeDetail 参数 userId："+userId);
		ResponceInfo responceInfo = new ResponceInfo();  
		if(userId==null||userId.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		Date date = new Date();
		Statistics connect = new Statistics();
		connect.setToday(joinDetailMapper.getDeviceConnectByStatistics(Long.valueOf(userId), DateUtils.getDayBegin(date), date));
		connect.setYesterday(joinDetailMapper.getDeviceConnectByStatistics(Long.valueOf(userId), DateUtils.getYesterdayBegin(date), DateUtils.getYesterdayEnd(date)));
		connect.setWeek(joinDetailMapper.getDeviceConnectByStatistics(Long.valueOf(userId), DateUtils.curWeekBegin(date), date));
		connect.setLastweek(joinDetailMapper.getDeviceConnectByStatistics(Long.valueOf(userId), DateUtils.lastWeekBegin(date), DateUtils.lastWeekEnd(date)));
		connect.setMonth(joinDetailMapper.getDeviceConnectByStatistics(Long.valueOf(userId), DateUtils.curMonthBegin(date), date));
		connect.setLastmonth(joinDetailMapper.getDeviceConnectByStatistics(Long.valueOf(userId), DateUtils.lastMonthBegin(date), DateUtils.lastMonthEnd(date)));
		
		Statistics generalize = new Statistics();
		generalize.setToday(userMapper.getUserGeneralize(Long.valueOf(userId), DateUtils.getDayBegin(date), date));
		generalize.setYesterday(userMapper.getUserGeneralize(Long.valueOf(userId), DateUtils.getYesterdayBegin(date), DateUtils.getYesterdayEnd(date)));
		generalize.setWeek(userMapper.getUserGeneralize(Long.valueOf(userId), DateUtils.curWeekBegin(date), date));
		generalize.setLastweek(userMapper.getUserGeneralize(Long.valueOf(userId), DateUtils.lastWeekBegin(date), DateUtils.lastWeekEnd(date)));
		generalize.setMonth(userMapper.getUserGeneralize(Long.valueOf(userId), DateUtils.curMonthBegin(date), date));
		generalize.setLastmonth(userMapper.getUserGeneralize(Long.valueOf(userId), DateUtils.lastMonthBegin(date), DateUtils.lastMonthEnd(date)));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("connect", connect);
		map.put("generalize", generalize);
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	@Override
	public ResponceInfo deviceFlowDetail(String userId) {
		log.info("service 司机版我的设备流量信息接口 method:deviceFlowDetail 参数 userId："+userId);
		// TODO service 司机版我的设备流量信息接口（未实现）
		ResponceInfo responceInfo = new ResponceInfo();  
		if(userId==null||userId.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Statistics consume = new Statistics();
		map.put("surplus", 9999);
		map.put("consume", consume);
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	@Override
	public ResponceInfo bindPhone(String phone, String userMac, String apMac) {
		log.info("service 司机版绑定手机号 method:bindPhone 参数 phone："+phone+" userMac:"+userMac+" apMac:"+apMac);
		ResponceInfo responceInfo = new ResponceInfo();
		if(phone==null||userMac==null||apMac==null||phone.equals("")||userMac.equals("")||apMac.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		User user = userMapper.getUserByPhone(phone);
		if(user == null){
			//用户没有注册
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			return responceInfo;
		}
		Device device = deviceMapper.getDeviceByMac(apMac);
		if(device == null){
			//没有找到对应的设备
			responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
			responceInfo.setMessage(Dictionary.MESSAGE_RESOURCE_NOT_FOUNT);
			return responceInfo;
		}
		//判断设备已经绑定几个手机号
		int count = userDeviceMapper.getUserDeviceCountByDeviceMac(device.getId());
		if(count>1){
			responceInfo.setCode(Dictionary.CODE_MIFI_BIND_OVER);
			responceInfo.setMessage(Dictionary.MESSAGE_MIFI_BIND_OVER);
			return responceInfo;
		}
		UserDevice userDevice = new UserDevice(user.getId(), device.getId(), new Date());
		int flag = userDeviceMapper.insert(userDevice);
		Map<String, Object> map = new HashMap<String, Object>();
		if(flag > 0 ){
			//TODO 绑定成功后，是不是直接开启上网功能
			
			//绑定成功
			map.put("isBind", true);
		}else{
			//绑定失败
			map.put("isBind", false);
		}
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	@Override
	public ResponceInfo checkPhoneBind(String phone) {
		log.info("service 司机版验证手机号是否绑定接口 method:checkPhoneBind 参数 phone："+phone);
		ResponceInfo responceInfo = new ResponceInfo();
		if(phone==null||phone.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		User user = userMapper.getUserByPhone(phone);
		if(user == null){
			//用户没有注册
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			return responceInfo;
		}
		
		UserDevice userDevice = userDeviceMapper.getUserDeviceByUserId(user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		if(userDevice == null){
			//没有绑定
			map.put("isBind", false);
		}else{
			//已经绑定
			map.put("isBind", true);
		}
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	@Override
	public ResponceInfo topInfo(String userId) {
		log.info("service 司机点赞信息接口 method:topInfo 参数 userId："+userId);
		ResponceInfo responceInfo = new ResponceInfo();
		if(userId==null||userId.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		Statistics admire = new Statistics();
		Date date = new Date();
		admire.setToday(praiseMapper.getPraiseCountByDate(Long.valueOf(userId), DateUtils.getDayBegin(date), date));
		admire.setYesterday(praiseMapper.getPraiseCountByDate(Long.valueOf(userId), DateUtils.getYesterdayBegin(date), DateUtils.getYesterdayEnd(date)));
		admire.setWeek(praiseMapper.getPraiseCountByDate(Long.valueOf(userId), DateUtils.curWeekBegin(date), date));
		admire.setLastweek(praiseMapper.getPraiseCountByDate(Long.valueOf(userId), DateUtils.lastWeekBegin(date), DateUtils.lastWeekEnd(date)));
		admire.setMonth(praiseMapper.getPraiseCountByDate(Long.valueOf(userId), DateUtils.curMonthBegin(date), date));
		admire.setLastmonth(praiseMapper.getPraiseCountByDate(Long.valueOf(userId), DateUtils.lastMonthBegin(date), DateUtils.lastMonthEnd(date)));
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(admire);
		return responceInfo;
	}

	@Override
	public ResponceInfo startJob(String userId, String apMac) {
		log.info("service  司机开始赚钱接口 method:startJob 参数 userId："+userId+" apMac:"+apMac);
		ResponceInfo responceInfo = new ResponceInfo();  
		Map<String, Object> map = new HashMap<String, Object>();
		if(userId==null||apMac==null||userId.equals("")||apMac.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			map.put("isJob", false);
			responceInfo.setData(map);
			return responceInfo;
		}
		Device device = deviceMapper.getDeviceByMac(apMac);
		if(device == null){
			//没有找到对应的设备
			responceInfo.setCode(Dictionary.CODE_MIFI_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_MIFI_NOT_EXIST);
			map.put("isJob", false);
			responceInfo.setData(map);
			return responceInfo;
		}
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
		if(userFlowgold==null){
			userFlowgold = new UserFlowgold();
			userFlowgold.setBalance(0l);
			userFlowgold.setUserId(Long.valueOf(userId));
			int i = userFlowgoldMapper.insert(userFlowgold);
			if(i < 1){
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
				map.put("isJob", false);
			}
		}
		map.put("balance", userFlowgold.getBalance());
		UserDevice userDevice = userDeviceMapper.getUserDeviceStatus(Long.valueOf(userId), device.getId());
		int flag = 0 ;
		if(userDevice == null){
			//没有记录，插入一条新纪录
			userDevice = new UserDevice();
			userDevice.setUserId(Long.valueOf(userId));
			userDevice.setDeviceId(device.getId());
			userDevice.setCreateTime(new Date());
			
			userDevice.setInternetStatus(Dictionary.STATUS_NORMAL);
			flag = userDeviceMapper.insert(userDevice);
		}else{
			//更新状态开始工作
			userDevice.setInternetStatus(Dictionary.STATUS_NORMAL);
			flag = userDeviceMapper.update(userDevice);
		}
		if(flag>0){
			
			map.put("isJob", true);
		}else{
			map.put("isJob", false);
		}
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	@Override
	public ResponceInfo stopJob(String userId, String apMac) {
		log.info("service 司机结束赚钱 method:stopJob 参数 userId："+userId+" apMac:"+apMac);
		ResponceInfo responceInfo = new ResponceInfo();  
		Map<String, Object> map = new HashMap<String, Object>();
		if(userId==null||apMac==null||userId.equals("")||apMac.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			map.put("isJob", false);
			responceInfo.setData(map);
			return responceInfo;
		}
		Device device = deviceMapper.getDeviceByMac(apMac);
		if(device == null){
			//没有找到对应的设备
			responceInfo.setCode(Dictionary.CODE_MIFI_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_MIFI_NOT_EXIST);
			map.put("isJob", false);
			responceInfo.setData(map);
			return responceInfo;
		}
		UserDevice userDevice = userDeviceMapper.getUserDeviceStatus(Long.valueOf(userId), device.getId());
		int flag = 0 ;
		if(userDevice != null){
			//更新联网状态结束工作
			userDevice.setInternetStatus(Dictionary.STATUS_DISABLED);
			flag = userDeviceMapper.update(userDevice);
		}
		if(flag>0){
			map.put("isJob", true);
		}else{
			map.put("isJob", false);
		}
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}


	@Override
	public ResponceInfo unBindPhone(String phone, String userMac, String apMac) {
		log.info("service 司机版解除绑定手机号 method:unBindPhone0 参数 phone："+phone+" userMac:"+userMac+" apMac:"+apMac);
		ResponceInfo responceInfo = new ResponceInfo();
		if(phone==null||userMac==null||apMac==null||phone.equals("")||userMac.equals("")||apMac.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		User user = userMapper.getUserByPhone(phone);
		if(user == null){
			//用户没有注册
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			return responceInfo;
		}
		Device device = deviceMapper.getDeviceByMac(apMac);
		if(device == null){
			//没有找到对应的设备
			responceInfo.setCode(Dictionary.CODE_MIFI_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_MIFI_NOT_EXIST);
			return responceInfo;
		}
		//判断设备已经绑定几个手机号
		
		UserDevice userDevice = userDeviceMapper.getUserDeviceStatus(user.getId(), device.getId());
		if(userDevice == null){
			//没有找到对应的设备
			responceInfo.setCode(Dictionary.CODE_MIFI_NOT_BIND);
			responceInfo.setMessage(Dictionary.MESSAGE_MIFI_NOT_BIND);
			return responceInfo;
		}
		userDevice.setStatus(Dictionary.STATUS_DISABLED);
		int flag = userDeviceMapper.update(userDevice);
		Map<String, Object> map = new HashMap<String, Object>();
		if(flag > 0){
			map.put("unBind", true);
		}else{
			map.put("unBind", false);
		}
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	@Override
	public ResponceInfo findCashDetailByPage(String userId, String date) {
		log.info("service 查找收益详情  method:findCashDetailByPage 参数 userId:"+userId+" date:"+date);
		ResponceInfo responceInfo = new ResponceInfo();
		if(userId==null||userId.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
		if(userFlowgold==null){
			userFlowgold = new UserFlowgold();
			userFlowgold.setUserId(Long.valueOf(userId));
			userFlowgoldMapper.insert(userFlowgold);
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			return responceInfo;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = null;
		try {
			curDate = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			log.error(e,e);
			responceInfo.setCode(Dictionary.CODE_PARAM_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_ERROR);
			return responceInfo;
		}
		//查找用户流量币的id
		int flag = 1;
		int dayCount = 1;
		List<FlowgoldDetailDto> flowgoldList = new ArrayList<FlowgoldDetailDto>();
		List<Expense> expenseList = expenseMapper.selectExpense();
		//TODO 查询日常任务的列表，在下面while中循环，找到每个任务的获取流量币在总值；2：判断所有总值都等于0，这放弃这一天，继续下一天
		while (flag <= Dictionary.FLOWGOLD_DETAIL_NUMBER) {
			List<FlowgoldDayDetailDto> daydetails = new ArrayList<FlowgoldDayDetailDto>();
			
			Date dayBegin = DateUtils.getNdayBegin(curDate, dayCount);
			Date dayEnd = DateUtils.getNdayEnd(curDate, dayCount);
			for(Expense expense:expenseList){
				Integer flagDayCount = flowgoldDetailMapper.getFlowgoldCountByType(userFlowgold.getId(), expense.getId(), dayBegin, dayEnd);
				if(flagDayCount!=null&&flagDayCount>0){
					FlowgoldDayDetailDto flowgoldDayDetailDto = new FlowgoldDayDetailDto();
					flowgoldDayDetailDto.setDescribe(expense.getName());
					flowgoldDayDetailDto.setCash(flagDayCount);
					daydetails.add(flowgoldDayDetailDto);
				}
			}
			if(daydetails.size()>0){
				FlowgoldDetailDto flowgoldDetailDto = new FlowgoldDetailDto();
				flowgoldDetailDto.setDatetime(DateUtils.simpleDateToString(dayBegin));
				flowgoldDetailDto.setDaydetails(daydetails);
//				flowgoldDetailDto.setStatus(1);
				flowgoldList.add(flowgoldDetailDto);
				flag++;
			}
			dayCount++;
			if(dayCount>30){
				if(flowgoldList.size()>0){
//					flowgoldList.get(flowgoldList.size()-1).setStatus(0);
				}
				responceInfo.setMessage("没有更多的数据了！");
				break;
			}
		}
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(flowgoldList);
		return responceInfo;
	}

	@Override
	public ResponceInfo index(String userId) {
		log.info("service 首页 参数 phone："+userId);
		ResponceInfo responceInfo = new ResponceInfo();
		if(userId==null||userId.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		User user = userMapper.getUserById(Integer.valueOf(userId));
		if(user!=null){
			//TODO 司机登录成功需要哪些操作
			/**
			 * 	connect	Integer	设备连接人数(OK)
				generalize	Integer	推广人数(ok)--通过验证码推广的用户
				expendflow	Long	消耗流量
				task	Integer	完成任务数(OK)
				cash	Integer	现金收益数
				voucher	Integer	抵用券数
				userId	Long	司机用户Id(OK)

			 */
			LoginDriverInfo driverInfo = new LoginDriverInfo();
			
			Date date = new Date();
			// 先查询一键登录的记录，如果找到，再通过用户ID和设备ID去查询设备在线人数；设备在线人数是通过设备的加入链接个数减去断开链接个数
			UserDevice userDevice = userDeviceMapper.getAvailableUserDevice(user.getId());
			if(userDevice!=null){
				Integer connectUser = joinDetailMapper.getDeviceConnect(DateUtils.getDayBegin(date), user.getId(), userDevice.getDeviceId(), Dictionary.STATUS_NORMAL) ;
				Integer breakUser = joinDetailMapper.getDeviceConnect(DateUtils.getDayBegin(date), user.getId(), userDevice.getDeviceId(), Dictionary.STATUS_DISABLED) ;
				if(connectUser == null){
					connectUser = 0;
				}
				if(breakUser == null){
					breakUser = 0;
				}
				if(connectUser>=breakUser){
					driverInfo.setConnect(connectUser-breakUser);
				}else{
					driverInfo.setConnect(0);
				}
			}else{
				driverInfo.setConnect(0);
			}
			
			int generalize = userMapper.getUserGeneralizeTotal(user.getId());
			driverInfo.setGeneralize(generalize);
			driverInfo.setTop(praiseMapper.getPraiseTotalCount(user.getId()));
			Integer task =  taskDetailMapper.getUserTaskCount(user.getId());
			if(task == null){
				task = 0;
			}
			driverInfo.setTask(task);
			
			String cityName = cityMapper.getCityById(user.getCity());
			UserDetail userDetail = new UserDetail(user, cityName);
			driverInfo.setUserDetail(userDetail);
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setData(driverInfo);
		}else{
			responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
		}
		return responceInfo;
	}

	@Override
	public ResponceInfo startJobToIp(String userId, String apIp) {
		log.info("service IP版 司机开始赚钱接口 method:startJobToIp 参数 userId："+userId+" apIp:"+apIp);
		ResponceInfo responceInfo = new ResponceInfo();  
		Map<String, Object> map = new HashMap<String, Object>();
		if(userId==null||apIp==null||userId.equals("")||apIp.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			map.put("isJob", false);
			responceInfo.setData(map);
			return responceInfo;
		}
		UserFlowgold userFlowgold = userFlowgoldMapper.getUserFlowgold(Long.valueOf(userId));
		if(userFlowgold==null){
			userFlowgold = new UserFlowgold();
			userFlowgold.setBalance(0l);
			userFlowgold.setUserId(Long.valueOf(userId));
			int i = userFlowgoldMapper.insert(userFlowgold);
			if(i < 1){
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setMessage(Dictionary.MESSAGE_SYSTEM_ERROT);
				map.put("isJob", false);
			}
		}
		map.put("balance", userFlowgold.getBalance());
		MifiInternet mifiInternet = mifiInternetMapper.getMifiInternetByIp(apIp);
		int flag = 0 ;
		if(mifiInternet == null){
			//没有记录，插入一条新纪录
			mifiInternet = new MifiInternet();
			mifiInternet.setIp(apIp);
			mifiInternet.setStatus(Dictionary.STATUS_NORMAL);
			mifiInternet.setUserid(Long.valueOf(userId));
			flag = mifiInternetMapper.insert(mifiInternet);
		}else{
			//更新状态开始工作
			mifiInternet.setStatus(Dictionary.STATUS_NORMAL);
			flag = mifiInternetMapper.update(mifiInternet);
		}
		if(flag>0){
			map.put("isJob", true);
		}else{
			map.put("isJob", false);
		}
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	@Override
	public ResponceInfo stopJobToIp(String userId, String apIp) {
		log.info("service IP版 司机结束赚钱接口 method:stopJobToIp 参数 userId："+userId+" apIp:"+apIp);
		ResponceInfo responceInfo = new ResponceInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		if(userId==null||apIp==null||userId.equals("")||apIp.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			map.put("isJob", false);
			responceInfo.setData(map);
			return responceInfo;
		}
		MifiInternet mifiInternet = mifiInternetMapper.getMifiInternetByIp(apIp);
		int flag = 0 ;
		if(mifiInternet != null){
			if(mifiInternet.getStatus()!=Dictionary.STATUS_DISABLED){
				mifiInternet.setStatus(Dictionary.STATUS_DISABLED);
				flag = mifiInternetMapper.update(mifiInternet);
			}else{
				flag = 1;
			}
		}
		if(flag>0){
			map.put("isJob", true);
		}else{
			map.put("isJob", false);
		}
		
		responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
		responceInfo.setData(map);
		return responceInfo;
	}

	
}
