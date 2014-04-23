/**
 * 
 */
package com.walmart.deliveryroute.dao.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.repository.MapPointRepository;

/**
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
	@Transactional
	public MapPoint saveMapPoint(MapPoint mapPoint) {
		return mapPointRepository.save(mapPoint);
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#deleteMapPoint(com.walmart.deliveryroute.model.MapPoint)
	 */
	@Override
	@Transactional
	public MapPoint deleteMapPoint(MapPoint mapPoint) {
		mapPointRepository.delete(mapPoint);
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#findMapPointById(java.lang.Long)
	 */
	@Override
	@Transactional
	public MapPoint findMapPointById(Long id) {
		return mapPointRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#findMapPoint(com.walmart.deliveryroute.model.MapPoint)
	 */
	@Override
	@Transactional
	public MapPoint findMapPoint(MapPoint mapPoint) {
		return mapPointRepository.findOne(mapPoint.getNodeId());
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IMapPointDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public MapPoint findMapPointByProperty(String property, Object value) {
		return mapPointRepository.findByPropertyValue(property, value);
	}

}
