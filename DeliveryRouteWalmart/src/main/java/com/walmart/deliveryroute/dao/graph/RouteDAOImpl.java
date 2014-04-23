/**
 * 
 */
package com.walmart.deliveryroute.dao.graph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.repository.MapPointRepository;
import com.walmart.deliveryroute.repository.RouteRepository;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class RouteDAOImpl implements IRouteDAO {
	
	@Autowired
	private MapPointRepository routeRepository;

	@Autowired
	private Neo4jTemplate template;
	
	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IRouteDAO#createRoute(com.walmart.deliveryroute.model.MapPoint, com.walmart.deliveryroute.model.MapPoint, int, java.lang.String)
	 */
	@Override
	@Transactional
	public void createRoute(MapPoint origin, MapPoint destination,
			double distance, String mapName) {
		Route route = routeRepository.createRelationshipBetween(origin, destination, Route.class, Route.RELATIONSHIP_TYPE);
		route.setDistance(distance);
		route.setMapName(mapName);
		template.save(route);
	}
	
	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IRouteDAO#deleteRoute(com.walmart.deliveryroute.model.Route)
	 */
	@Override
	@Transactional
	public void deleteRoute(Route route) {
		routeRepository.deleteRelationshipBetween(route.getOrigin(), route.getDestination(), Route.RELATIONSHIP_TYPE);
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IRouteDAO#findRoute(com.walmart.deliveryroute.model.Route)
	 */
	@Override
	@Transactional
	public Route findRoute(Route route) {
		return routeRepository.getRelationshipBetween(route.getOrigin(), route.getDestination(), Route.class, Route.RELATIONSHIP_TYPE);
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IRouteDAO#findRouteByPoints(com.walmart.deliveryroute.model.MapPoint, com.walmart.deliveryroute.model.MapPoint)
	 */
	@Override
	@Transactional
	public Route findRouteByPoints(MapPoint origin, MapPoint destination) {
		return routeRepository.getRelationshipBetween(origin, destination, Route.class, Route.RELATIONSHIP_TYPE);
	}
}
