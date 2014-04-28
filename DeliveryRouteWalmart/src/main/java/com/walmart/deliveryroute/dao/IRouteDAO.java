/**
 * 
 */
package com.walmart.deliveryroute.dao;

import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;

/**
 * Defines an API for {@link Route} database access object
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IRouteDAO {

	/**
	 * Create a new {@link Route} between two {@link MapPoint} objects. Should be called within a transaction environment.
	 * @param origin {@link MapPoint} from where the path starts
	 * @param destination {@link MapPoint} to where the path ends
	 * @param distance Distance between the points
	 * @param mapName A map name
	 */
	public void createRoute(MapPoint origin, MapPoint destination, double distance, String mapName);
	
	/**
	 * Delete a {@link Route}
	 * @param route
	 */
	public void deleteRoute(Route route);
		
	/**
	 * Find a {@link Route} by a given object
	 * @param route
	 * @return {@link Route} found
	 */
	public Route findRoute(Route route);
	
	/**
	 * Find a {@link Route} by the pair of {@link MapPoint} which represents origin and destination
	 * @param origin {@link MapPoint} represents the origin
	 * @param destination {@link} represents the destination
	 * @return {@link Route} found
	 */
	public Route findRouteByPoints(MapPoint origin, MapPoint destination);
	
}
