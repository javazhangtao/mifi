package com.mifi.main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

import com.google.common.base.Strings;
import com.mifi.common.Dictionary;
import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.CGLibCode;
import com.mifi.utils.CryptAES;
import com.mifi.utils.HttpUrlDecode;
import com.mifi.utils.ParamsUtils;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
/**
 * 通用server对外接口暴露解析
 *
 */
public class GenericServer{
	
	final String key=Dictionary.getProperty("APP_AES_KEY");
	
	HttpRequest req;
	
	public GenericServer(HttpRequest req){
		this.req=req;
	}
	
	public String handler(){
		Map<String, List<String>> _params=null;
		String srcUrl = req.getUri();
		try {
			if(req.getMethod()==HttpMethod.GET)
				_params=_getParams(srcUrl);
			else
				_params=_postParams(srcUrl);
		} catch (Exception e) {}
		return _execute(_params);
		
		
	}
	
	/**
	 * 方法执行
	 * @param params
	 * @return
	 */
	String _execute(Map<String, List<String>> params){
		try {
			String cln=ParamsUtils.getStringFromMap(params, "cln");//接口名
			String mod=ParamsUtils.getStringFromMap(params, "mod");//方法名
			if(StringUtils.hasText(cln) && StringUtils.hasText(mod)){
				Object targetObject=Dictionary.MIFI_SERVICES.get(cln);
				if(null!=targetObject){
					params.remove("cln");
					params.remove("mod");
					Class<?>[] _cs=new Class<?>[params.size()];
					 Map<String, String> _parameters=new HashMap<String, String>();
					int i=0;
					for (Entry<String, List<String>> e : params.entrySet()) {
						_cs[i]=String.class;
						if(null!=e.getValue() && e.getValue().size()>0)
							_parameters.put(e.getKey(), Strings.nullToEmpty(e.getValue().get(0)));
						else
							_parameters.put(e.getKey(), "");
						i++;
					}
					CGLibCode cgLibCode=new CGLibCode();
					return cgLibCode.execute(targetObject, mod, _cs, _parameters).toString();
				}else{
					ResponceInfo responceInfo=new ResponceInfo(); 
					responceInfo.setCode(Dictionary.CODE_RESOURCE_NOT_FOUNT);
					responceInfo.setMessage("["+cln+"] not found ");
					return responceInfo.toString();
				}
			}else{
				ResponceInfo responceInfo=new ResponceInfo(); 
				responceInfo.setCode(Dictionary.CODE_PARAM_NULL);
				responceInfo.setMessage(" cln or mod is null ");
				return responceInfo.toString();
			}
		} catch (Exception e) {
			ResponceInfo responceInfo=new ResponceInfo(); 
			responceInfo.setCode(Dictionary.CODE_SYSTEM_ERROT);
			responceInfo.setMessage(" SYSTEM ERROR ");
			return responceInfo.toString();
		}
	}
	
	
	
	/**
	 * 获取get方式参数
	 * @param req
	 * @param srcUrl
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Map<String, List<String>> _getParams(final String srcUrl) throws Exception{
		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(srcUrl);
		Map<String, List<String>> params=new HashMap<String, List<String>>();
		params = queryStringDecoder.parameters();
		String q = ParamsUtils.getParamsToValue(srcUrl, "q");
		q = q.replaceAll("%20", "+");
		q = HttpUrlDecode.decode(q, "UTF-8");
		String url="xxx?" + CryptAES.Decrypt(key, q);
		queryStringDecoder = new QueryStringDecoder(url);
		params=queryStringDecoder.parameters();
		return params;
	}
	
	/**
	 * 获取post方式参数
	 * @param req
	 * @param srcUrl
	 * @param key
	 * @return
	 * @throws Exception
	 */
	Map<String, List<String>> _postParams(final String srcUrl) throws Exception{
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), req);
		Map<String, List<String>> _map=new HashMap<String, List<String>>();
		if (decoder != null) {
			List<InterfaceHttpData> postDatas = decoder.getBodyHttpDatas(); 
			for (InterfaceHttpData postData:postDatas) {
				if (postData.getHttpDataType() == HttpDataType.Attribute){
					Attribute attribute = (Attribute) postData;
					String _ps=attribute.getValue();
					List<String> _list=new ArrayList<String>();
					_list.add(Strings.nullToEmpty(_ps));
					_map.put(attribute.getName(), _list);
				}
			}
		}
		return _map;
	}
}
