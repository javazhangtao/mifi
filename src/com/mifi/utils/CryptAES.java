package com.mifi.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/**
 * AES加密和解密
 *
 */
public class CryptAES {
	
	private static final String AESTYPE = "AES/ECB/PKCS5Padding";
	
	/**
	 * 加密
	 * ECB算法模式，密钥长度128 ， 补码 PKCS5Padding  编码方式 Base64
	 * @param keyStr
	 * @param plainText
	 * @return
	 */
	public static String Encrypt(final String keyStr, final String plainText){
		try {
			byte[] raw=keyStr.getBytes("utf-8");
			SecretKeySpec skeySpec=new SecretKeySpec(raw, "AES");
			Cipher cipher=Cipher.getInstance(AESTYPE);//"算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE,skeySpec);
			byte[] encrypted = cipher.doFinal(plainText.getBytes("utf-8"));
			return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception e) {
			return null ;
		}
		
	}
	
	/**
	 * 解密
	 * ECB算法模式，密钥长度128 ， 补码 PKCS5Padding  编码方式 Base64
	 * @param keyStr
	 * @param plainText
	 * @return
	 */
	public static String Decrypt(final String keyStr, final String plainText){
		try {
			byte[] raw = keyStr.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	        byte[] encrypted1 = new Base64().decode(plainText);//先用base64解密
	        byte[] original = cipher.doFinal(encrypted1);
	        return new String(original,"utf-8");
		} catch (Exception e) {
			return null ;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(Encrypt("mifiappserverkey","cln=userService&mod=sayHello&name=zhangtao"));
		System.out.println(Decrypt("mifiappserverkey","Uqm2l6U8xSNNldqlEohByhmPB9xI+C4puiiQBnvtSFfh7KDIVQNyP2ZGUtwwCoaW"));
	}
}