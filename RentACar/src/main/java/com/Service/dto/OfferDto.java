package com.Service.dto;

public class OfferDto {

	public Long price;
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
	public String contractText;
}
