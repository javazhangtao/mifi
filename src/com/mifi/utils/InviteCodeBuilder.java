package com.mifi.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据手机号码生成6位邀请码。 #手机号中最大数字为9，9+9=18，故自定义18进制，
 * #手机号第一位作为6位邀请码第一位，手机号后十位数字两两相加得到5个数字，将这5个数字转换成位18进制后与第一位拼接成新的字符串。
 * 
 * @author Chylin
 *
 */
public class InviteCodeBuilder {

	public static String getInviteCode(Long userId) {
		char[] c = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		String ssss = "";
		List<Long> list = new ArrayList<Long>();
		while (userId != 0) {
			long cc = userId % 36;
			list.add(cc);
			userId = userId / 36;
		}
		System.out.println(list);
	
		for (int i = list.size() - 1; i >= 0; i--) {
			ssss += c[list.get(i).intValue()] + "";
		}
		while (ssss.length() < 6) {
			ssss = "0" + ssss;
		}
		System.out.println(ssss);
		return ssss;
	}
	
}
