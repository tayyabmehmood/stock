package com.payconiq.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class StockResponse {

	private Long id;
	private Date lastUpdate;
	private double currentPrice;
	private String name;


}
