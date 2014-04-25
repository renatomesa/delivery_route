/**
 * 
 */
package com.walmart.deliveryroute.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.repository.MapPointRepository;

/**
 * This implementation uses as bases Graph databases and requires {@link Autowired} objects for {@link MapPointRepository} and {@link Neo4jTemplate} using
 * Spring framework to perform {@link Route} operations.
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
	public void createRoute(MapPoint origin, MapPoint destination,
			double distance, String mapName) {
		Route route = template.createRelationshipBetween(origin, destination, Route.class, Route.RELATIONSHIP_TYPE, true);
		route.setDistance(distance);
		route.setMapName(mapName);
		template.save(route);
	}
	
	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IRouteDAO#deleteRoute(com.walmart.deliveryroute.model.Route)
	 */
	@Override
	public void deleteRoute(Route route) {
		routeRepository.deleteRelationshipBetween(route.getOrigin(), route.getDestination(), Route.RELATIONSHIP_TYPE);
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IRouteDAO#findRoute(com.walmart.deliveryroute.model.Route)
	 */
	@Override
	public Route findRoute(Route route) {
		return routeRepository.getRelationshipBetween(route.getOrigin(), route.getDestination(), Route.class, Route.RELATIONSHIP_TYPE);
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IRouteDAO#findRouteByPoints(com.walmart.deliveryroute.model.MapPoint, com.walmart.deliveryroute.model.MapPoint)
	 */
	@Override
	public Route findRouteByPoints(MapPoint origin, MapPoint destination) {
		return routeRepository.getRelationshipBetween(origin, destination, Route.class, Route.RELATIONSHIP_TYPE);
	}
}
