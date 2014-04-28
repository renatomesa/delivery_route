package com.walmart.deliveryroute.exception;

public class IllegalMapPointInputException extends IllegalArgumentException {

	public IllegalMapPointInputException() {
		new IllegalArgumentException("Given origin or destination is not valid or it was not processed yet.");
	}
	
}
