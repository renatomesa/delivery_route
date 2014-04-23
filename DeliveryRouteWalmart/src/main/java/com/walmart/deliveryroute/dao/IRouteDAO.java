/**
 * 
 */
package com.walmart.deliveryroute.dao;

import java.util.List;

import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;

/**
 * Defines how a {@link Route} database access object should behave
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IRouteDAO {

	public void createRoute(MapPoint origin, MapPoint destination, double distance, String mapName);
	
	public void deleteRoute(Route route);
		
	public Route findRoute(Route route);
	
	public Route findRouteByPoints(MapPoint origin, MapPoint destination);
	
}
