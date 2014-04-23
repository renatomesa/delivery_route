/**
 * 
 */
package com.walmart.deliveryroute.services;

import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.ShortestPath;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IMapOperationsService {

	public ShortestPath findShortestPath(MapPoint origin, MapPoint destination);
	
}
