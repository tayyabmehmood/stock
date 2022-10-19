package com.payconiq.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.payconiq.entity.Stock;
import com.payconiq.pojo.RequestPayload;
import com.payconiq.repository.StockRepository;

@Service
public class StockService {
	@Autowired 
	StockRepository stockRepository;
	
	public Stock getBalance(Long id) throws Exception{
		return stockRepository.findById(id)
			      .orElseThrow(() -> new Exception());
	}
	
	
	public void deleteStock(Long id) {
		stockRepository.deleteById(id);
	}
	
	
	public void updateStockPrice(long id, double price) {
		stockRepository.updateStockPrice(id, price);
	}
	
	public Optional<Stock> getStock(long id) {
		Optional<Stock> optionalStock = stockRepository.findById(id);
		return optionalStock;
	}
	
	public List<Stock> getStockAll(String pageNumber, String pageSize) {
		List<Stock> list = null;
		int pageNumberInt = 0;
		int pageSizeInt = 0;
		try {
			pageNumberInt = Integer.valueOf(pageNumber);
			pageSizeInt = Integer.valueOf(pageSize);
		}catch(NumberFormatException e) {
			pageSize = null;
			pageNumber = null;
		}
		if (pageNumber == null || pageSize == null) {
			list = stockRepository.findAll();
		}else {
			Page page =  stockRepository.findAll(PageRequest.of(pageNumberInt, pageSizeInt));
			list = (List<Stock>) page.stream().collect(Collectors.toList()); 
		}
		return list;
	}
	
	
	public void saveStock(RequestPayload payload) {
		Stock stock = new Stock();
		stock.setName(payload.getName());
		stock.setCurrentPrice(payload.getCurrentPrice());
		stock.setLastUpdate(LocalDateTime.now());
		stockRepository.save(stock);
	}
}
