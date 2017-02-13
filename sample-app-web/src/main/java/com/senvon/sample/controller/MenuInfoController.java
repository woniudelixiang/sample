package com.senvon.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.istock.base.ibatis.model.Page;
import com.senvon.sample.model.MenuInfo;
import com.senvon.sample.service.MenuInfoService;

@Controller
public class MenuInfoController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MenuInfoService menuInfoService;
	
	@Value("${menu.title}")
	private String menuTitle;
	
	@RequestMapping("showMenuList")
	public String showMenuList(ModelMap model , String name , Page page){
		if(page == null){
			page = new Page();
		}
		page.setPageSize(3);
		menuInfoService.findByName(name, page);
		model.put("name", name);
		model.put("menuTitle", menuTitle);
		model.put("page", page);
		return "menuInfo/menuList";
	}
	
	@RequestMapping("editPre")
	public String editPre(ModelMap model , Integer id){
		if(id != null && id>0){
			//update
			MenuInfo menuInfo = menuInfoService.findMenuInfoById(id);
			model.put("menuInfo", menuInfo);
		}
		return "menuInfo/menuEdit";
	}
	
	@RequestMapping("edit")
	public String edit(ModelMap model , MenuInfo menuInfo){
		Integer result = menuInfoService.saveMenuInfo(menuInfo);
		if(result >0){
			model.put("message", "保存成功");
			
		}else{
			model.put("message", "保存失败");
			model.put("menuInfo", menuInfo);
			return "menuInfo/menuEdit";
		}
		return showMenuList(model ,null, new Page());
	}
	
	@RequestMapping("delete")
	public String delete(ModelMap model , Integer id){
		Integer result = menuInfoService.deleteMenuInfo(id);
		if(result >0){
			model.put("message", "保存成功");
		}else{
			model.put("message", "保存失败");
		}
		return showMenuList(model ,null, new Page());
	}
	
	
	@RequestMapping("test")
	public @ResponseBody Map<String , Object> test(HttpServletRequest request){
		logger.info("==================requestParam:{}" , request.getParameter("name"));
		logger.info("==================sessionAttribute:{}" , request.getSession().getAttribute("name"));
		Map<String , Object> result = new HashMap<String , Object>();
		result.put("returnCode", 123);
		result.put("message", "senvon");
		
		String name = "$senvon$";
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setMemo("senvonTest");
		menuInfo.setName(name);
		menuInfoService.saveMenuInfo(menuInfo);

		List<MenuInfo> menuList = menuInfoService.findByName(name, new Page());
		logger.info("menuList is :{}" , menuList);
		Assert.assertTrue(menuList != null);
		Assert.assertTrue(menuList.size() == 1);
		
		return result;
	}
	
}
