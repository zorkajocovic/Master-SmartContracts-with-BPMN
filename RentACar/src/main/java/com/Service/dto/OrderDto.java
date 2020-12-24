package com.Service.dto;

import java.util.Date;

public class OrderDto {
	
	public String customerName;
	
	public String customerSurame;
	
	public String type;
	
	public String model;
	
	public String dateFrom;
	
	public String dateTo;

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerSurname() {
		return customerSurame;
	}
	
	public void setCustomerSurname(String customerSurname) {
		this.customerSurame = customerSurname;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
}
