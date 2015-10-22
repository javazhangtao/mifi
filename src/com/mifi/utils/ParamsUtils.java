package com.mifi.utils;
import java.util.List;
import java.util.Map;

public class ParamsUtils {
	
	public static String getParamsToValue (String url, String param) {
		int len = param.length();
		if(url.indexOf(param) > -1) {
			url = url.substring(url.indexOf(param) + len + 1, url.length());
			if(url.indexOf("&") > 0) {
				url = url.substring(0, url.indexOf("&"));
			}
			return url;
		}
		return null;
	}

	public static String getStringFromMap(Map<String, List<String>> params, String key){
		if(null==params || params.size()<=0)
			return null ;
		if(params.containsKey(key)){
			List<String> values=params.get(key);
			if(values!=null&&values.size()>=1){
				return values.get(0);
			}
		}
		return null;
	}
}
