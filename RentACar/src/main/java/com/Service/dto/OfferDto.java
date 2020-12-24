package com.Service.dto;

public class OfferDto {

	public Long price;
	
	public String contractText;

	public Long deposit;
	
	public Long getPrice() {
		return price;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public String getText() {
		return contractText;
	}
	
	public void setText(String text) {
		this.contractText = text;
	}
	
	public Long getDeposit() {
		return deposit;
	}
	
	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
}
