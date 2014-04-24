/**
 * 
 */
package com.walmart.deliveryroute.dao;

import com.walmart.deliveryroute.model.MapPoint;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IMapPointDAO {

	public MapPoint saveMapPoint(MapPoint mapPoint);
	
	public MapPoint deleteMapPoint(MapPoint mapPoint);
		
	public MapPoint findMapPointById(Long id);
	
	public MapPoint findMapPoint(MapPoint mapPoint);
	
	public MapPoint findMapPointByProperty(String property, Object value);
}
