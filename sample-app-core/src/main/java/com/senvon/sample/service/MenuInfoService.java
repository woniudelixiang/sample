package com.senvon.sample.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istock.base.ibatis.model.Page;
import com.senvon.bank.BankService;
import com.senvon.bank.model.OrderVO;
import com.senvon.sample.dao.MenuInfoDAO;
import com.senvon.sample.model.MenuInfo;
import com.senvon.sample.model.MenuInfoExample;

@Repository
public class MenuInfoService {

	@Autowired
	private MenuInfoDAO menuInfoDao;
	
	@Autowired
	private BankService bankService;
	
	public MenuInfo findMenuInfoById(Integer id){
		return menuInfoDao.selectByPrimaryKey(id);
	}
	
	public List<MenuInfo> findByName(String name , Page page){
		MenuInfoExample example = new MenuInfoExample();
		MenuInfoExample.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andNameLike("%"+name+"%");
		}
		return menuInfoDao.selectByPage(example, page);
	}
	
	public Integer saveMenuInfo(MenuInfo menuInfo){
		if(menuInfo != null){
			if(menuInfo.getId() != null && menuInfo.getId()>0){
				//update
				return menuInfoDao.updateByPrimaryKeySelective(menuInfo);
			}else{
				//insert
				return menuInfoDao.insertSelective(menuInfo);
			}
		}
		return 0;
	}
	
	public Integer deleteMenuInfo(Integer id){
		if(id != null && id>0){
			return menuInfoDao.deleteByPrimaryKey(id);
		}
		return 0;
	}
	
	
	public Integer deleteMenuInfoByName(String name){
		if(StringUtils.isNotEmpty(name)){
			MenuInfoExample example = new MenuInfoExample();
			MenuInfoExample.Criteria criteria = example.createCriteria();
			criteria.andNameEqualTo(name);
			return menuInfoDao.deleteByExample(example);
		}
		return 0;
	}
	
	public boolean invokeRemote(Integer i){
		OrderVO orderVO = new OrderVO();
		orderVO.setAmount(BigDecimal.TEN);
		orderVO.setName("senvon");
		orderVO.setId(""+i);
		return null != bankService.moneyOut(orderVO);
	}
}
