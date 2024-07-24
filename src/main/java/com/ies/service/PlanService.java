package com.ies.service;

import java.util.List;

import com.ies.bindings.PlanForm;

public interface PlanService {
	
	/**
	 * PlanForm: Capture form data object from frontend to backend
	 * boolean:  give success message
	 */
	public boolean createPlan(PlanForm planForm);
	
	/**
	 * Show list of plans on the View Plan page
	 */
	public List<PlanForm> fetchPlans();
	
	/**
	 * By using plaId Update the plan on PlanForm
	 */
	public PlanForm getPlanById(Integer planId);
	
	/**
	 * to make plan active inactive
	 * @param planId
	 * @param status
	 * @return 
	 */
	public String changePlanStatus(Integer planId, String status);
	
	
	

}
