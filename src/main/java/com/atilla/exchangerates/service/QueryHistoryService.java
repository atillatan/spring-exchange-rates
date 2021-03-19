package com.atilla.exchangerates.service;

import java.util.List;

import com.atilla.exchangerates.domain.QueryHistory;

public interface QueryHistoryService {
	
	List<QueryHistory> getDaily(String year, String month, String day);
	
	List<QueryHistory> getMonthly(String year, String month);	


}
