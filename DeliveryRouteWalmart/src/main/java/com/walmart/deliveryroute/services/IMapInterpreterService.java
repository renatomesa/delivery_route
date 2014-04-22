/**
 * 
 */
package com.walmart.deliveryroute.services;

/**
 * Interface that defines methods regarding how a service responsible for interpretating map inputs should behave
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IMapInterpreterService {

	/**
	 * Parses a {@link String} which represents a logistic map in the following form:
	 * 
	 * Line 1: A B 30
	 * Line 2: B C 15
	 * Line 3: A C 10
	 * 
	 * The first column represents an origin point in a map, the second column a destination and the third is the distance between them
	 * 
	 * @param mapName name of the map
	 * @param map text data
	 */
	public void performMapInterpretation(String mapName, String mapData);
	
}
