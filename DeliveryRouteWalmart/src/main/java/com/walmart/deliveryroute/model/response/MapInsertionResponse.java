package com.walmart.deliveryroute.model.response;

/**
 * This POJO class represents a JSON response to the client in case of a map is inserted
 * @author Renato
 *
 */
public class MapInsertionResponse {

	private boolean status;
	private String message;
	
	/**
	 * Create a new object instance
	 * @param status
	 * @param message
	 */
	public MapInsertionResponse(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
