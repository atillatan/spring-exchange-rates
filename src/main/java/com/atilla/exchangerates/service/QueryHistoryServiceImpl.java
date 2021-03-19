package com.atilla.exchangerates.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atilla.exchangerates.domain.QueryHistory;
import com.atilla.exchangerates.repository.QueryHistoryRepository;

/**
 * @author atilla
 *
 */
@Service
@Transactional
public class QueryHistoryServiceImpl implements QueryHistoryService {

	private final QueryHistoryRepository queryHistoryRepository;

	@Autowired
	public QueryHistoryServiceImpl(QueryHistoryRepository queryHistoryRepository) {
		this.queryHistoryRepository = queryHistoryRepository;

	}

	@Override
	public List<QueryHistory> getDaily(String year, String month, String day) {

		LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		return queryHistoryRepository.findByCreationDate(localDate);
	}

	@Override
	public List<QueryHistory> getMonthly(String year, String month) {

		LocalDate startDayofMonth = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
		LocalDate endDayofMonth = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month),
				startDayofMonth.lengthOfMonth());

		return queryHistoryRepository.findByDate(startDayofMonth, endDayofMonth);

	}

} 
