package com.senvon.sample.serviceTest;

import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.senvon.sample.model.MenuInfo;
import com.senvon.sample.service.MenuInfoService;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test.xml"})
//@TransactionConfiguration(defaultRollback = true)
//@Transactional
public class MenuInfoServiceTest2 {

	@Autowired
	private MenuInfoService menuInfoService;
	
//	@Test
	public void test1() throws Exception{
		MenuInfo menuInfo = new MenuInfo();
//		menuInfo.setId(11);
		menuInfo.setMemo("senvon");
		menuInfo.setName("senvon");
		Integer i = menuInfoService.saveMenuInfo(menuInfo);
//		System.out.println("============"+i);
		Assert.assertTrue(i > 0);
		
		MenuInfo result = menuInfoService.findMenuInfoById(i);
		Assert.assertTrue(result != null);
		Assert.assertTrue(result.getName().equalsIgnoreCase("senvon"));
	}
	
	@Test
	public void test2() throws Exception{
		for(int i = 0;i<10 ; i++){
			Thread t = new Thread(new ExecuteThread());
			t.start();
			latch.countDown();
		}
		Thread.currentThread().sleep(5000);
	}
	
	
	@Before
	public void init() throws Exception{
		
	}
	
	@After
	public void destory() throws Exception{
		menuInfoService.deleteMenuInfoByName("senvon");
	}
	
	
	private CountDownLatch latch = new CountDownLatch(10);
	
	private class ExecuteThread implements Runnable {
		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			MenuInfo menuInfo = new MenuInfo();
			menuInfo.setMemo("senvon");
			menuInfo.setName("senvon");
			Integer i = menuInfoService.saveMenuInfo(menuInfo);
			
			MenuInfo searchResult = menuInfoService.findMenuInfoById(i);
			System.out.println("=================="+searchResult);
			Assert.assertTrue(searchResult != null);
		}
	}
}
