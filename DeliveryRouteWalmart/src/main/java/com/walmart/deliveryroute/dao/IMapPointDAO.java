/**
 * 
 */
package com.walmart.deliveryroute.dao;

import com.walmart.deliveryroute.model.MapPoint;

/**
 * Defines an API for {@link MapPoint} database access object
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IMapPointDAO {

	/**
	 * Create or update an existent {@link MapPoint} object. It needs to be called within a transaction context.
	 * @param mapPoint
	 * @return created {@link MapPoint}
	 */
	public MapPoint saveMapPoint(MapPoint mapPoint);
	
	/**
	 * Delete an existent {@link MapPoint} object. It needs to be called within a transaction context.
	 * @param mapPoint
	 * @return deleted {@link MapPoint}
	 */
	public MapPoint deleteMapPoint(MapPoint mapPoint);
		
	/**
	 * Find a map point by its database identifier
	 * @param id
	 * @return {@link MapPoint} found
	 */
	public MapPoint findMapPointById(Long id);
	
	/**
	 * Finds a map point with a given map point object
	 * @param mapPoint
	 * @return {@link MapPoint} found
	 */
	public MapPoint findMapPoint(MapPoint mapPoint);
	
	/**
	 * Finds a map point by one of its properties
	 * @param property
	 * @param value
	 * @return {@link MapPoint} found
	 */
	public MapPoint findMapPointByProperty(String property, Object value);
}
