package com.Service.dto;

public class OrderDto {
	
	public String customerName;
	
	public String customerSurname;
	
	public String type;
	
	public String model;
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerSurname() {
		return customerSurname;
	}
	
	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
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
