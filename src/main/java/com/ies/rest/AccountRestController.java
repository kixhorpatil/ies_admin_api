package com.ies.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ies.bindings.UserAccountForm;
import com.ies.service.AccountService;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * To handle the request from UI and send response to UI
 */
@RestController
public class AccountRestController {

	@Autowired
	private AccountService accService;

	/*
	 * ToInteract with Create account Admin Section
	 * 
	 * @RequestBody to get the form data
	 * 
	 * @PostMapping("/user") because we are sending response to create the record/inseting the records
	 * 
	 */

	@PostMapping("/user")
	public ResponseEntity<String> createAccount(@RequestBody UserAccountForm userAccountForm) {
		boolean status = accService.createUserAccount(userAccountForm);
		if (status) {
			return new ResponseEntity<>("Account Created", HttpStatus.CREATED); // 201
		} else {
			return new ResponseEntity<>("Account Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR); // 500;
		}
	}

	/*
	 * To interact with view account
	 */
	@GetMapping("/user")
	public ResponseEntity<List<UserAccountForm>> getUsers() {
		List<UserAccountForm> userAccountsForm = accService.fetchUserAccounts();
		return new ResponseEntity<>(userAccountsForm, HttpStatus.OK);
	}

	/*
	 * Get the userId from the UI and put that userId in the editable/profile mode
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccountForm> getUser(@PathVariable("userId") Integer userId) {
		UserAccountForm userAccById = accService.getUserAccById(userId);

		return new ResponseEntity<>(userAccById, HttpStatus.OK);
	}

	/*
	 * Method to change the user status / @PutMapping for updating the status
	 */
	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccountForm>> updateUserAcc(@PathVariable("userId") Integer userId,
			@PathVariable("status") String status) {
		
		accService.changeAccStatus(userId, status);
		// once success we need to return the existing record back to the user
		List<UserAccountForm> userAccountsForm = accService.fetchUserAccounts();
		return new ResponseEntity<>(userAccountsForm, HttpStatus.OK);

	}

}
