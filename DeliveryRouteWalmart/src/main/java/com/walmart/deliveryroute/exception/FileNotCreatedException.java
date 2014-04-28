package com.walmart.deliveryroute.exception;

import java.io.IOException;

public class FileNotCreatedException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 332884388567103319L;

	public FileNotCreatedException() {
		super("File or directory could not be created. Check folder permissions");
	}
	
	public FileNotCreatedException(Exception e) {
		super(e);
	}
	
}
