package com.mifi.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifi.common.Dictionary;
import com.mifi.dao.mifi.DeviceMapper;
import com.mifi.dao.mifi.JoinDetailMapper;
import com.mifi.dao.mifi.JoinIpMapper;
import com.mifi.dao.mifi.MifiInternetMapper;
import com.mifi.dao.mifi.UserDeviceMapper;
import com.mifi.dao.mifi.UserMapper;
import com.mifi.dto.AuthResult;
import com.mifi.dto.ResponceInfo;
import com.mifi.po.mifi.Device;
import com.mifi.po.mifi.JoinDetail;
import com.mifi.po.mifi.JoinIp;
import com.mifi.po.mifi.MifiInternet;
import com.mifi.po.mifi.User;
import com.mifi.po.mifi.UserDevice;
import com.mifi.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private static Logger log = Logger.getLogger(AuthServiceImpl.class);
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserDeviceMapper userDeviceMapper;
	@Autowired
	DeviceMapper deviceMapper;
	@Autowired
	JoinDetailMapper joinDetailMapper;
	@Autowired
	MifiInternetMapper mifiInternetMapper;
	@Autowired
	JoinIpMapper joinIpMapper;
	@Override
	public String login(String username, String password, String regcity, String mac, String apIp, String apMac,
			String device) {
		log.info("登录验证 login 参数 username：" + username + " password:" + password + " regcity:" + regcity + " mac:" + mac
				+ " apIp:" + apIp + " apMac:" + apMac + " device:" + device);
		// TODO 登录验证 多余的参数怎么处理，是否添加登录记录
		User user = new User(username, password);
		user = userMapper.getUser(user);
		if(user!=null){
			if(user.getStatus()==Dictionary.STATUS_DISABLED){
				return Dictionary.LOGIN_RESOULT_RELOGIN;
			}
		}else{
			return Dictionary.LOGIN_RESOULT_NOEXIST;
		}
		return null;
	}
	@Override
	public ResponceInfo loginMac(String phone, String password, String type, String apMac) {
		log.info("登录验证 login 参数 phone：" + phone + " password:" + password + " type:" + type + " apMac:" + apMac );
		ResponceInfo responceInfo = new ResponceInfo();
		if(phone==null||password==null||apMac==null||phone.equals("")||password.equals("")||apMac.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			return responceInfo;
		}
		
		//TODO 是否判断盒子具有上网功能
		//TODO 返回数据，需要加上司机的userId
		/**
		 * 0：判断盒子是否有上网功能；（应该不需要，如果不能上网，就不能访问后台地址，即请求不到这里）
		 * 1：验证用户名密码；验证盒子是否注册;
		 * 2：判断是从司机客户端APP传来的请求，还是其他：
		 * 	        如果是司机端APP，判断是否绑定到盒子，如果司机在用户端APP登录，当作乘客来处理；
		 * 	        如果不是司机端，判断此MAC的盒子，司机是否开通开始赚钱；
		 * 3：判断是否重复登录
		 * 4：添加连接记录，登录成功
		 */
		User user = new User(phone, password);
		user = userMapper.getUser(user);
		if(user == null){
			//用户没有注册
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			responceInfo.setData(Dictionary.LOGIN_RESOULT_FALSE);
			return responceInfo;
		}
		
		Device device = deviceMapper.getDeviceByMac(apMac);
		if(device == null){
			//没有找到对应的设备信息
			responceInfo.setCode(Dictionary.CODE_MIFI_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_MIFI_NOT_EXIST);
			responceInfo.setData(Dictionary.LOGIN_RESOULT_FALSE);
			return responceInfo;
		}
		Long driverId = user.getId();
		if(type.equals("1000")){
			UserDevice userDevice = userDeviceMapper.getUserDeviceStatus(user.getId(), device.getId());
			if(userDevice == null){
				responceInfo.setCode(Dictionary.CODE_MIFI_NOT_BIND);
				responceInfo.setMessage(Dictionary.MESSAGE_MIFI_NOT_BIND);
				responceInfo.setData(Dictionary.LOGIN_RESOULT_FALSE);
				return responceInfo;
			}
		}else{
			UserDevice userDevice = userDeviceMapper.getStartJobUserDevice(device.getId());
			if(userDevice == null){
				responceInfo.setCode(Dictionary.CODE_MIFI_NOT_AUTH);
				responceInfo.setMessage(Dictionary.MESSAGE_MIFI_NOT_AUTH);
				responceInfo.setData(Dictionary.LOGIN_RESOULT_FALSE);
				return responceInfo;
			}
			driverId = userDevice.getUserId();
		}
		
		JoinDetail linkJoinDetail = joinDetailMapper.getUserJoinDetailByDeviceMac(user.getId(), device.getId(),Dictionary.STATUS_NORMAL);
		if(linkJoinDetail == null){
			JoinDetail joinDetail = new JoinDetail(device.getId(), apMac, Dictionary.STATUS_NORMAL, user.getId(), driverId);
			int flag = joinDetailMapper.insert(joinDetail);
			if(flag<1){
				log.error("用户插入上网记录失败，用户ID："+user.getId()+" 上网设备MAC："+apMac);
			}
		}else{
			JoinDetail breakJoinDetail = joinDetailMapper.getUserJoinDetailByDeviceMac(user.getId(), device.getId(),Dictionary.STATUS_DISABLED);
			if(breakJoinDetail.getModifyTime().getTime()>linkJoinDetail.getModifyTime().getTime()){
				responceInfo.setCode(Dictionary.CODE_USER_HAS_LOGIN);
				responceInfo.setMessage(Dictionary.MESSAGE_USER_HAS_LOGIN);
				responceInfo.setData(Dictionary.LOGIN_RESOULT_RELOGIN);
				return responceInfo;
			}
		}
		//TODO 开通上网
		String requestUrl = "http://pass.eggwifi.com/portal?url=http://m.baidu.com";
		if(getAccessAuthority(requestUrl)){
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setMessage("验证成功，可以上网！");
			responceInfo.setData(Dictionary.LOGIN_RESOULT_RELOGIN);
		}else{
			responceInfo.setCode(Dictionary.CODE_MIFI_OPEN_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_MIFI_OPEN_ERROR);
			responceInfo.setData(Dictionary.LOGIN_RESOULT_RELOGIN);
		}
		
		return responceInfo;
	}
	
	@Override
	public ResponceInfo login(String phone, String password, String type, String apIp) {
		log.info("登录验证 login 参数 phone：" + phone + " password:" + password + " type:" + type + " apIp:" + apIp );
		ResponceInfo responceInfo = new ResponceInfo();
		AuthResult authResult = new AuthResult();
		if(phone==null||password==null||apIp==null||phone.equals("")||password.equals("")||apIp.equals("")){
			responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
			responceInfo.setMessage(Dictionary.MESSAGE_PARAM_NULL);
			authResult.setLogin("false");
			responceInfo.setData(authResult);
			return responceInfo;
		}
		
		//TODO 返回数据，需要加上司机的userId
		/**
		 * 0：判断盒子是否有上网功能；（应该不需要，如果不能上网，就不能访问后台地址，即请求不到这里）
		 * 1：验证用户名密码；验证盒子是否注册;
		 * 2：判断是从司机客户端APP传来的请求，还是其他：
		 * 	        如果是司机端APP，判断是否绑定到盒子，如果司机在用户端APP登录，当作乘客来处理；
		 * 	        如果不是司机端，判断此MAC的盒子，司机是否开通开始赚钱；
		 * 3：判断是否重复登录
		 * 4：添加连接记录，登录成功
		 */
		User user = new User(phone, password);
		user = userMapper.getUser(user);
		if(user == null){
			//用户没有注册
			responceInfo.setCode(Dictionary.CODE_USER_NOT_EXIST);
			responceInfo.setMessage(Dictionary.MESSAGE_USER_NOT_EXIST);
			authResult.setLogin("false");
			responceInfo.setData(authResult);
			return responceInfo;
		}
		
		Long driverId = user.getId();
		//司机，设备IP表格
		if(!type.equals("1000")){
			
			//司机，设备IP表格
			MifiInternet mifiInternet = mifiInternetMapper.getMifiInternetByIp(apIp);
			if(mifiInternet == null||mifiInternet.getStatus()!=Dictionary.STATUS_NORMAL){
				responceInfo.setCode(Dictionary.CODE_MIFI_NOT_AUTH);
				responceInfo.setMessage(Dictionary.MESSAGE_MIFI_NOT_AUTH);
				authResult.setLogin("false");
				responceInfo.setData(authResult);
				return responceInfo;
			}
			driverId = mifiInternet.getUserid();
		}else{
			if(user.getUserType()!=Dictionary.STATUS_NORMAL){
				responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
				responceInfo.setMessage("您不是司机用户，请使用用户版流量加进行登录！");
				authResult.setLogin("false");
				responceInfo.setData(authResult);
				return responceInfo;
			}
		}
		
		JoinIp linkJoinIp = joinIpMapper.getUserJoinDetailByDeviceMac(user.getId(), apIp,Dictionary.STATUS_NORMAL);
		if(linkJoinIp != null){
			JoinIp breakJoinIp = joinIpMapper.getUserJoinDetailByDeviceMac(user.getId(), apIp, Dictionary.STATUS_DISABLED);
			if(breakJoinIp!=null&&breakJoinIp.getModifyTime().getTime() > breakJoinIp.getModifyTime().getTime()){
				responceInfo.setCode(Dictionary.CODE_USER_HAS_LOGIN);
				responceInfo.setMessage(Dictionary.MESSAGE_USER_HAS_LOGIN);
				authResult.setLogin("relogin");
				responceInfo.setData(authResult);
				return responceInfo;
			}
		}
		//TODO 开通上网
		String requestUrl = "http://pass.eggwifi.com/portal?url=http://m.baidu.com";
		if(getAccessAuthority(requestUrl)){
			responceInfo.setCode(Dictionary.CODE_SYSTEM_NORMAL);
			responceInfo.setMessage("验证成功，可以上网！");
			authResult.setLogin("true");
			responceInfo.setData(authResult);
			JoinIp joinIp = new JoinIp(apIp, Dictionary.STATUS_NORMAL, driverId, user.getId());
			if(joinIpMapper.insert(joinIp)<1){
				log.error("用户上网插入记录失败,用户ID:"+user.getId()+" 设备IP："+apIp);
			}
			if(type.equals("1000")){
				MifiInternet mifiInternet = new MifiInternet();
				mifiInternet.setIp(apIp);
				mifiInternet.setUserid(user.getId());
				if(mifiInternetMapper.insert(mifiInternet)<1){
					log.error("司机上网插入设备访问网络权限记录失败,司机ID:"+user.getId()+" 设备IP："+apIp);
				}
			}
		}else{
			responceInfo.setCode(Dictionary.CODE_MIFI_OPEN_ERROR);
			responceInfo.setMessage(Dictionary.MESSAGE_MIFI_OPEN_ERROR);
			authResult.setLogin("false");
			responceInfo.setData(authResult);
		}
		
		return responceInfo;
	}
	
	//TODO 强制下线
	public boolean stopNet(){
		
		return false;
	}
	/**
	 * HTTP请求
	 * @param url
	 * @return
	 */
	public boolean getAccessAuthority(String url){
		boolean flag = false;
		String requestUrl = "http://pass.eggwifi.com/portal?url=http://m.baidu.com";
		String VST_USERAGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36";
		String response = null;
		HttpClient client = new HttpClient();
		GetMethod httpGet = new GetMethod(requestUrl);

		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getHttpConnectionManager().getParams().setSoTimeout(10000);

		client.getParams().setParameter(HttpMethodParams.USER_AGENT,VST_USERAGENT);
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		try {
			int statusCode = client.executeMethod(httpGet);
			if (statusCode != HttpStatus.SC_OK) { // 打印服务器返回的状态
				log.error("Method failed: " + httpGet.getStatusLine());
				httpGet.abort();
			} else {
				flag = true;
				response = httpGet.getResponseBodyAsString();
				if (response != null) {
					response = new String(response.getBytes(), "utf-8");
					log.info("易哥上网申请返回结果："+response);
				}
			}

		} catch (HttpException e) {
			httpGet.abort();
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			httpGet.abort();
			log.error(e.getMessage(), e);
		} finally {
			httpGet.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return flag;
	}
	

}
