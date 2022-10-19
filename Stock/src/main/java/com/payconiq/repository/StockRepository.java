package com.payconiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.payconiq.entity.Stock;

@Repository
public interface StockRepository
        extends JpaRepository<Stock, Long> {
	
	@Modifying
	@Transactional
	@Query("update Stock s set s.currentPrice = ?2, s.lastUpdate=CURRENT_TIMESTAMP where s.id = ?1")
	int updateStockPrice(long id, double currentPrice);
}
	