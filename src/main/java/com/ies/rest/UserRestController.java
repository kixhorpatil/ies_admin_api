package com.ies.rest;

import org.springframework.web.bind.annotation.RestController;

import com.ies.bindings.DashboardCard;
import com.ies.bindings.LoginForm;
import com.ies.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * Login funtionallity / forget password / dashboard 
 */
@RestController
public class UserRestController {

	private UserService userService;

	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		String status = userService.login(loginForm);

		if (status.equals("success")) {
			return "redirect:/dashboard"; // when login is successful redirect to /dashboard
		} else {
			return status;
		}
	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardCard> buildDashboard() {
		DashboardCard dashboardCard = userService.fetchDashboardInfo(); // dashboard fetch the data
		return new ResponseEntity<DashboardCard>(dashboardCard, HttpStatus.OK); // return in the form of dashboard
	}

}
