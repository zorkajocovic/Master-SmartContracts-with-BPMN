package com.Service.camundaDto;

public class FieldDto {
	
	private String fieldId;
	private String fieldValue;
	
	public FieldDto() {
		super();
	}

	public FieldDto(String fieldId, String fieldValue) {
		super();
		this.fieldId = fieldId;
		this.fieldValue = fieldValue;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
}
