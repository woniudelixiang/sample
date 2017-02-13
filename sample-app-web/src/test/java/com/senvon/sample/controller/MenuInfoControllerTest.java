package com.senvon.sample.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 控制层的测试
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class MenuInfoControllerTest {

	@Autowired
	MenuInfoController menuInfoController;
	
	@Test
	public void test1() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("name", "senvonaasf");
		HttpSession session = request.getSession();
		session.setAttribute("name", "senvonInSession");
		
		Map<String , Object> result = menuInfoController.test(request);
		Assert.assertTrue(result != null);
		Assert.assertTrue("123".equalsIgnoreCase(result.get("returnCode")+"" ));
	}
}
