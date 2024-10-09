package com.thyme.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourseName;
	String fieldName;
	long fieldValue;
	String fieldValue1;
	public ResourceNotFoundException(String resourseName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s: %l", resourseName, fieldName, fieldValue));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourseName, String fieldName, String fieldValue1) {
		super(String.format("%s not found with %s: %s", resourseName, fieldName, fieldValue1));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue1;
	}
	
	
	public String getFieldValue1() {
		return fieldValue1;
	}

	public void setFieldValue1(String fieldValue1) {
		this.fieldValue1 = fieldValue1;
	}

	public String getResourseName() {
		return resourseName;
	}
	public void setResourseName(String resourseName) {
		this.resourseName = resourseName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	

}
