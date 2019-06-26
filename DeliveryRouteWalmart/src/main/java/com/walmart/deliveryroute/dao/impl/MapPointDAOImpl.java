/**
 * 
 */
package com.walmart.deliveryroute.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.repository.MapPointRepository;

/**
 * This implementation uses as bases Graph databases and requires {@link Autowired} objects for {@link MapPointRepository} and {@link Neo4jTemplate} using
 * Spring framework to perform {@link MapPoint} database operations.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class MapPointDAOImpl implements IMapPointDAO {

	@Autowired
	MapPointRepository mapPointRepository;
	
	@Autowired
	Neo4jTemplate template;
	
	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#saveMapPoint(com.walmart.deliveryroute.model.MapPoint)
	 */
	@Override
	public MapPoint saveMapPoint(MapPoint mapPoint) {
		//return mapPointRepository.save(mapPoint);
		return template.save(mapPoint);
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#deleteMapPoint(com.walmart.deliveryroute.model.MapPoint)
	 */
	@Override
	public MapPoint deleteMapPoint(MapPoint mapPoint) {
		//mapPointRepository.delete(mapPoint);
		template.delete(mapPoint);
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#findMapPointById(java.lang.Long)
	 */
	@Override
	public MapPoint findMapPointById(Long id) {
		return mapPointRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#findMapPoint(com.walmart.deliveryroute.model.MapPoint)
	 */
	@Override
	public MapPoint findMapPoint(MapPoint mapPoint) {
		return mapPointRepository.findOne(mapPoint.getNodeId());
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public MapPoint findMapPointByProperty(String property, Object value) {
		return mapPointRepository.findBySchemaPropertyValue(property, value);
	}	
}
