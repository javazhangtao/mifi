package com.mifi.main.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import com.google.common.base.Strings;
import com.mifi.dto.ResponceInfo;

public class CGLibCode {
	
	public ResponceInfo execute(Object targetObject,String methodName,Class<?>[] parameterTypes, Map<String, String> parameters){
		try {
			FastClass serviceFastClass = FastClass.create(targetObject.getClass());
			FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
			String[] _paramsName=_getParameterNames(serviceFastMethod.getJavaMethod());
			Object[] _parameters=new Object[parameterTypes.length];
			for (int i = 0; i < _paramsName.length; i++) {
				_parameters[i]=Strings.emptyToNull(parameters.get(_paramsName[i]));
			}
			return (ResponceInfo)serviceFastMethod.invoke(targetObject, _parameters);
		} catch (Exception e) {
			return null ;
		}
	}
	
	/**
	 * 获取方法参数名称
	 * @param method
	 * @return
	 */
	String[] _getParameterNames(final Method method) {  
		ParameterNameDiscoverer parameterNameDiscoverer  = new LocalVariableTableParameterNameDiscoverer();
        return parameterNameDiscoverer.getParameterNames(method);  
    }

}
