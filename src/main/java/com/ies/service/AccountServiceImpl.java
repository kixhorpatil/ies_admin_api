package com.ies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ies.bindings.UnlockAccForm;
import com.ies.bindings.UserAccountForm;
import com.ies.entities.UserEntity;
import com.ies.repositories.UserRepo;
import com.ies.utils.EmailUtils;

@Service // represents class as a spring bean
public class AccountServiceImpl implements AccountService {

	@Autowired // dependency injection UserRepo
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override			
	public boolean createUserAccount(UserAccountForm accForm) {
		// convert the form data(UserAccountForm) into the entity object
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(accForm, entity);

		// set random pwd
		entity.setPwd(generatePwd());

		// set acc status
		entity.setAccStatus("LOCKED");
		entity.setActiveSw("Y");
		userRepo.save(entity);

		// send email
		String subject = "";
		String body = "";
		boolean status = emailUtils.sendEmail(subject, body, accForm.getEmail());
		return status; //return status based on account inserted or not  	 	 		
	}

	@Override
	public List<UserAccountForm> fetchUserAccounts() {

		// retrieve list user account entity available in DB
		List<UserEntity> userEntities = userRepo.findAll();

		// convert the entities into the user binded object
		List<UserAccountForm> users = new ArrayList<UserAccountForm>();
		for (UserEntity userEntity : userEntities) { // taking each entity from list of entity
			UserAccountForm user = new UserAccountForm(); // converting each entity to binding object
			BeanUtils.copyProperties(userEntities, user);
			users.add(user); // adding binding object to users collection

		}

		return users;
	}

	@Override
	public UserAccountForm getUserAccById(Integer accId) {

		// for given Id record may available may not available
		Optional<UserEntity> optional = userRepo.findById(accId);
		if (optional.isPresent()) {
			UserEntity userEntity=optional.get();
			//get and convert to binding object
			UserAccountForm user = new UserAccountForm();
			BeanUtils.copyProperties(userEntity, user);
			return user;
			
		}
		return null;
	}

	/**
	 * Active or Deactivate the account
	 * 
	 * with single query we update the 2 record, by using custom query
	 */
	@Override
	public String changeAccStatus(Integer userId, String status) {
		Integer cnt = userRepo.updateAccountStatus(userId, status);
		
		if (cnt>0) {
			return "status changed";
		}
		return "status failed to changed";
	}
	
	/**
	 * Unlock/retrieve the user data, base on email id
	 */
	@Override
	public String unlockUserAccount(UnlockAccForm unlockAccForm) {
		//based on email we get the entity
		UserEntity entity = userRepo.findByEmail(unlockAccForm.getEmail());
		/*
		 * when user click on unlock account form data will come,
		 *  based on email id we retrieve the record
		 */
		entity.setPwd(unlockAccForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");
		userRepo.save(entity);
		
		return "Account Unlocked";
	}

	private String generatePwd() {
		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 6;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		return sb.toString();
	}

}
