/**
 * 
 */
package com.walmart.deliveryroute.services;

import java.io.File;
import java.io.IOException;

import com.walmart.deliveryroute.model.ShortestPath;

/**
 * Interface that defines methods regarding how a service responsible for interpretating map inputs should behave
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IMapManagerService {

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
	
	/**
	 * Parses a {@link File} which should contain in the first line a Name and later the folllowing structure:
	 * Line 1: A B 30
	 * Line 2: B C 15
	 * Line 3: A C 10
	 * 
	 *The first column represents an origin point in a map, the second column a destination and the third is the distance between them
	 * @param file A {@link File} object pointing to the location of the map file
	 * @throws IOException
	 */
	public void performMapInterpretation(File file) throws IOException;
	
	/**
	 * This method returns a {@link ShortestPath} object containing information about a found shortest path between
	 * an origin and a destination.
	 * @param origin Name of the origin point
	 * @param destination Name of the destination point
	 * @param autonomy Autonomy 
	 * @param fuelCost
	 * @return
	 */
	public ShortestPath getShortestPath(String origin, String destination, float autonomy, float fuelCost);
	
}
