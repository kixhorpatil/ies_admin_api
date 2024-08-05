package com.ies.service;

import java.util.List;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ies.bindings.DashboardCard;
import com.ies.bindings.LoginForm;
import com.ies.entities.EligEntity;
import com.ies.entities.UserEntity;
import com.ies.repositories.EligRepo;
import com.ies.repositories.PlanRepo;
import com.ies.repositories.UserRepo;
import com.ies.utils.EmailUtils;

@Service // represents class as a spring bean
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo; // To find the user by email and password and let the login

	@Autowired
	private EmailUtils emailUtils; // to send the recover password email to the user

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private EligEntity eligEntity; // to inject into the dashboard

	@Autowired
	private EligRepo eligRepo;

	@Override
	public String login(LoginForm loginForm) {
		UserEntity entity = userRepo.findByEmailAndPwd(loginForm.getEmail(), loginForm.getPwd());
		if (entity == null) {
			return "Invalid Credentil";
		}

		/*
		 * "Y".equals(entity.getActiveSw()) if admin has deactivated the account, case
		 * worker should not be able to login.
		 * 
		 * which user is logged in or what is the rode
		 */
		if ("Y".equals(entity.getActiveSw()) && "UNLOCKED".equals(entity.getAccStatus())) {
			return "success@role="+entity.getRoleId();
		} else {
			return "Account Locked/Inactive";
		}

	}

	@Override
	public boolean recoverPwd(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (null == userEntity) {
			return false;
		} else {
			String subject = "";
			String body = "";
			return emailUtils.sendEmail(subject, body, email);
		}
	}

	@Override
	public DashboardCard fetchDashboardInfo() {
		
		// will display no. of available plans on dashboard
		long plansCount = planRepo.count();
		List<EligEntity> eligList = eligRepo.findAll();

		// will give the list of approved count on the DashBoard
		long approvedCnt = eligList.stream().filter(ed -> ed.getPlanStatus().equals("AP")).count();

		// will give the list of approved count on the DashBoard
		long deniedCnt = eligList.stream().filter(ed -> ed.getPlanStatus().equals("DN")).count();
		
		//will display benfit ammount given on DashBoard
		double total = eligList.stream().mapToDouble(ed->ed.getBenefitAmt()).sum();

		DashboardCard card = new DashboardCard();
		card.setPlansCnt(plansCount);
		card.setApprovedCnt(approvedCnt);
		card.setDeniedCnt(deniedCnt);
		card.setBenefitAmtGiven(total);

		return card;
	}

}
