package com.ies.service;

import java.util.List;

import com.ies.bindings.UnlockAccForm;
import com.ies.bindings.UserAccountForm;

public interface AccountService {
	/**
	 * To Create Account
	 * 
	 * passed parameters bcoz it is taking input
	 * 
	 * It returns true account created successfully else not create successfully
	 * 
	 */
	public boolean createUserAccount(UserAccountForm accForm);

	/**
	 * 
	 * @return detail of users account in View Accounts
	 * 
	 *         no parameters are passed because it is not taking any input only
	 *         giving output and List is taken do that more than one can be shown on
	 *         the output
	 */
	public List<UserAccountForm> fetchUserAccounts();

	/**
	 * Retrieves the user account information based on the provided ID for editable
	 * functionality
	 *
	 * @param accId the ID of the user account to be retrieved
	 * @return the UserAccountForm containing the user account information
	 */
	public UserAccountForm getUserAccById(Integer accId);

	/**
	 * Soft delete will by done by account status switch(active/inactive)
	 * 
	 * Admin can changes account status(active/inactive) based on the accId and
	 * status(activate/deactivate)
	 * 
	 * @param accId  the ID of the user account whose status is to be changed
	 * @param status the new status for the user account (either "activate" or "deactivate")
	 * @return a message indicating the result of the status change
	 */
	public String changeAccStatus(Integer accId, String status);

	
	/**
	 * When administrator create the Caseworker account, CW should receive a mail
	 * which that mail cw should unlock the account
	 * 
	 * account status should be active
	 */
	public String unlockUserAccount(UnlockAccForm unlockAccForm);
	
}











