package com.mifi.emay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.mifi.common.Dictionary;

public class SMSSender {

	public static void sendSMSForAuthCode(String phone, int authCode){
		String message = "【流量加】验证码"+ authCode +"，为了您的账号安全，请勿告诉他人。";
	    try {
			message = URLEncoder.encode(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    String param = "cdkey=" + Dictionary.getProperty("MESSAGE_SN") + "&password=" + Dictionary.getProperty("MESSAGE_KEY") + "&phone=" + phone + "&message=" + message;
	    String url = Dictionary.getProperty("MESSAGE_BASEURL") + "sendtimesms.action";
	    SDKHttpClient.sendSMS(url, param);
	}
	
}
