package com.mifi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Properties;

public class PropertiesUtil {

	private static Properties props;
	private URI uri;

	public PropertiesUtil(String ...fileNames) {
		readProperties(fileNames);
	}

	private void readProperties(String ...fileNames) {
		try {
			props = new Properties();
			if(fileNames != null && fileNames.length > 0) {
				for(String fileName : fileNames) {
					InputStream fis = getClass().getResourceAsStream(fileName);
					props.load(fis);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取某个属性
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * 在控制台上打印出所有属性，调试时用。
	 */
	public void printProperties() {
		props.list(System.out);
	}

	/**
	 * 写入properties信息
	 */
	public void writeProperties(String key, String value) {
		try {
			OutputStream fos = new FileOutputStream(new File(uri));
			props.setProperty(key, value);
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "『comments』Update key：" + key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void init(String ...fileNames) {
		PropertiesUtil util = new PropertiesUtil(fileNames);
		util.printProperties();
	}
}
