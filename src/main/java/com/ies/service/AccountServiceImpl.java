package com.ies.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ies.bindings.UnlockAccForm;
import com.ies.bindings.UserAccountForm;

@Service //represents class as a spring bean
public class AccountServiceImpl implements AccountService {

	@Override
	public boolean createUserAccount(UserAccountForm accForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserAccountForm> fetchUserAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccountForm getUserAccById(Integer accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeAccStatus(Integer accId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unlockUserAccount(UnlockAccForm unlockAccForm) {
		// TODO Auto-generated method stub
		return null;
	}

}
