package com.mifi.main;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.mifi.common.Dictionary;
import com.mifi.main.proxy.MifiServer;

/**
 *	初始化系统上下文静态常量
 */
@Component
public class Init implements ApplicationContextAware , InitializingBean{
	
	Logger logger=Logger.getLogger(Init.class);
	
	ApplicationContext context;

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, Object> _map=context.getBeansWithAnnotation(MifiServer.class);
		for (Entry<String,Object> e:_map.entrySet()) {
			String name=e.getKey().replace("Impl", "");
			Class<?> _clazz=e.getValue().getClass();
			MifiServer _ms=_clazz.getAnnotation(MifiServer.class);
			if(null!=_ms)
				name=_ms.name();
			Dictionary.MIFI_SERVICES.put(name,e.getValue());
		}
		logger.info("["+Dictionary.MIFI_SERVICES.size()+"] server inited ");
		
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context=context;
	}

}
