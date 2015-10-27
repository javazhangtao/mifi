package com.mifi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mifi.service.BlackListService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring/context.xml" })  
public class BlackListTest {

	@Autowired
	BlackListService blackListService;
	
	@Test
	public void Test(){
		try {
			System.out.println(blackListService.isExistBlack("18611431686"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
