package com.atm.poc.service;

import com.atm.poc.base.AtmBase;
import com.atm.poc.base.AtmBase_I;
import com.atm.poc.dao.CustomerProcessDao;
import com.atm.poc.model.Customer;

public class AuthenticationService extends AtmBase implements AtmBase_I{

	@Override
	public boolean authenticateCustomer(Customer customer) {
		
		
		CustomerProcessDao custDao = new CustomerProcessDao();
		
		custDao.getCustomer(customer);
		return true;
		
	}

}
