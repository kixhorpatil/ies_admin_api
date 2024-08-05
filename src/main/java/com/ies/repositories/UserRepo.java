package com.ies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ies.entities.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	/*
	 * Directly update the status(activate/deactivate with the custom query
	 * 
	 * with single query we update the 2 record, by using custom query
	 */
	@Query("update UserEntity set accStatus=:status where userId:=userId")
	public Integer updateAccountStatus(Integer userId, String status);
	
	/*
	 * retrive user record based on email
	 */
	public UserEntity findByEmail(String email);
	
	
	/*
	 * whenever user click on login button we have to validate either the 
	 * credentials are valid or not, if valid then with given email and pwd 
	 * retrieve the record
	 */
	public UserEntity findByEmailAndPwd(String email, String pwd);

}
