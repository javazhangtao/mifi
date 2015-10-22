package com.mifi.service.impl;

import com.mifi.dto.ResponceInfo;
import com.mifi.main.proxy.MifiServer;
import com.mifi.service.UserService;
@MifiServer(name="userService")
public class UserServiceImpl implements UserService {

	@Override
	public ResponceInfo sayHello(String name){
		ResponceInfo info=new ResponceInfo();
		info.setCode(200);
		info.setData("Hello "+name);
		info.setMessage("SUCCESS");
		return  info;
	}
}
