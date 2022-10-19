package com.payconiq.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Stock")
@Table(name = "stock")
public class Stock {

	@Id
	@SequenceGenerator(name = "stock_sequence", sequenceName = "stock_sequence", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "stock_sequence")
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "last_update", nullable = false)
	private LocalDateTime lastUpdate;

	@Column(name = "current_price", nullable = false)
	private double currentPrice;

	@Column(name = "name", nullable = false)
	private String name;

	public Stock() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public double getCurrentPrice() {
		return this.currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
