/**
 * 
 */
package com.walmart.deliveryroute.services;

import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.ShortestPath;

/**
 * This interface provides methods which defines the behavior of Services responsible for handling a map.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IMapOperationsService {

	/**
	 * Findes the shortest path of a map between two {@link MapPoint} objects
	 * @param origin 
	 * @param destination
	 * @return A {@link ShortestPath} object, which contains information about the path which was found by the service.
	 */
	public ShortestPath findShortestPath(MapPoint origin, MapPoint destination);
	
}
