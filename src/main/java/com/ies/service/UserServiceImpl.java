package com.ies.service;

import org.springframework.stereotype.Service;

import com.ies.bindings.DashboardCard;
import com.ies.bindings.LoginForm;

@Service //represents class as a spring bean
public class UserServiceImpl implements UserService{

	@Override
	public String login(LoginForm loginForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean recoverPwd(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DashboardCard fetchDashboardInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
