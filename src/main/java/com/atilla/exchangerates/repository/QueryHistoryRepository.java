package com.atilla.exchangerates.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atilla.exchangerates.domain.QueryHistory;

/**
 * @author atilla
 *
 */
@Repository
public interface QueryHistoryRepository extends JpaRepository<QueryHistory, Long> {

	@Query(value = "SELECT * FROM QUERY_HISTORY u WHERE DATE(create_date) = ?1", nativeQuery = true)
	List<QueryHistory> findByCreationDate(LocalDate createDate);

	@Query(value = "SELECT * FROM QUERY_HISTORY u WHERE DATE(create_date) >= ?1 and DATE(create_date)<=?2", nativeQuery = true)
	List<QueryHistory> findByDate(LocalDate startDate, LocalDate endDate);

}