/**
 * 
 */
package com.walmart.deliveryroute.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.walmart.deliveryroute.model.MapInterpretationContainer;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;
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
	 * @return 
	 */
	public MapInterpretationContainer performMapInterpretation(String mapName, String mapData);
	
	/**
	 * Parses a {@link File} which should contain in the first line a Name and later the folllowing structure:
	 * Line 1: A B 30
	 * Line 2: B C 15
	 * Line 3: A C 10
	 * 
	 *The first column represents an origin point in a map, the second column a destination and the third is the distance between them
	 * @param file A {@link File} object pointing to the location of the map file
	 * @return 
	 * @throws IOException
	 */
	public MapInterpretationContainer performMapInterpretation(File file) throws IOException;
	
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

	/**
	 * Insert map points.
	 * @param mapPoints
	 */
	void insertMapPoints(Map<String, MapPoint> mapPoints);

	/**
	 * Insert routes list. Should not be called for a huge list.
	 * @param mapPoints
	 * @param routes
	 */
	void insertRoutes(Map<String, MapPoint> mapPoints, List<Route> routes);

	/**
	 * This method is used only for test purposes, for now.
	 * @param mapContainer
	 */
	void insertMap(MapInterpretationContainer mapContainer);
	
}
