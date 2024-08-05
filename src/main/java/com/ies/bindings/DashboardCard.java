package com.ies.bindings;

import lombok.Data;

@Data
public class DashboardCard {
	private long plansCnt;
	private long approvedCnt;
	private long deniedCnt;
	private Double benefitAmtGiven;

}
