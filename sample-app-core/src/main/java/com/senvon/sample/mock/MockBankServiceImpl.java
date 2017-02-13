package com.senvon.sample.mock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.senvon.bank.BankService;
import com.senvon.bank.model.BankResponse;
import com.senvon.bank.model.OrderVO;

public class MockBankServiceImpl implements BankService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public BankResponse moneyOut(OrderVO orderInfo) {
		logger.info("============== 自己模拟的远程服务实现   =================");
		if(StringUtils.isNotEmpty(orderInfo.getId())){
			Integer i = Integer.parseInt(orderInfo.getId());
			if(i % 2 ==0){
				return new BankResponse();
			}
		}
		return null;
	}

}
