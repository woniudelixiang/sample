package com.senvon.sample.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.senvon.sample.service.MenuInfoService;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class MenuInfoServiceTest3 {

	@Autowired
	private MenuInfoService menuInfoService;
	
	@Test
	public void test1() throws Exception{
		boolean result = menuInfoService.invokeRemote(1);
		Assert.assertTrue(!result);
		
		/*boolean result1 = menuInfoService.invokeRemote(2);
		Assert.assertTrue(result1);*/
	}
}
