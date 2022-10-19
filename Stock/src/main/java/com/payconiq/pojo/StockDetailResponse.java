package com.payconiq.pojo;

import java.util.List;

import lombok.Data;

@Data
public class StockDetailResponse {
	private List <StockResponse> stocks;
	private StockResponse stock;
	private String status;


}
