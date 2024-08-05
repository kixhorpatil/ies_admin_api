package com.ies.service;

import com.ies.bindings.DashboardCard;
import com.ies.bindings.LoginForm;

/**
 * For user management and user functionalities. 
 */
public interface UserService {
	
	/**
	 * On login page after entering the valid credential user 
	 * will login and go on Dashboard's page.
	 * 
	 * When login is successful Dashboad should be the return type
	 * else error message should display on the LoginForm page
	 * 
	 * String-> Handles more than 2 scenarios
	 *          show error based on the situation, invalid credential/Login success/ 
	 *          account is locked(if admin has deactivated the account)
	 */
	public String login(LoginForm loginForm);
	
	/**
	 * To recover pass send password to user's mail, 
	 * boolean return success message or error message only 2 scenarios
	 */
	public boolean recoverPwd(String email);
	
	
	/**
	 * After successful login display the dashboard data
	 */
	public DashboardCard fetchDashboardInfo();
	
	
	/**
	 * For user my profile option will be available
	 * which ever the user is logged in that user info is displayed
	 * get user info based on userId
	 * 
	 * we can reuse the profile update info from the AccountService method	
	 * public UserAccountForm getUserAccById(Integer accId);
	 */

	
	
	
}





















