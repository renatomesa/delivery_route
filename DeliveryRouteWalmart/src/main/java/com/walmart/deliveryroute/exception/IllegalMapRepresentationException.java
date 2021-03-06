/**
 * 
 */
package com.walmart.deliveryroute.exception;

import java.io.Serializable;

/**
 * Customized exception to be thrown when an input map can not be parsed
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class IllegalMapRepresentationException extends IllegalArgumentException{

	/**
	 * Serial version UID, since it is a class who implements {@link Serializable}
	 */
	private static final long serialVersionUID = -3944089710680423818L;
	
	/**
	 * Creates a new object with default error message.
	 */
	public IllegalMapRepresentationException() {
		super("Invalid Arguments in Map Input");
	}


}
