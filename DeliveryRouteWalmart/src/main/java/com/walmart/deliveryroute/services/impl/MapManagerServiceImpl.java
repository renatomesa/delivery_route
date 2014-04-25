/**
 * 
 */
package com.walmart.deliveryroute.services.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.model.ShortestPath;
import com.walmart.deliveryroute.services.IMapOperationsService;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.utils.MapInterpreter;

/**
 * This class implements {@link IMapManagerService} and it is responsible for interpreting a map input,
 * creating a data structure
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class MapManagerServiceImpl implements IMapManagerService {

	@Autowired
	IRouteDAO routeDao;
	
	@Autowired
	IMapPointDAO mapPointDao;
	
	@Autowired
	IMapOperationsService mapOperationsService;
	
	@Autowired
	Neo4jTemplate template;
	
	public MapManagerServiceImpl() {
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.services.IMapInterpreterService#test()
	 */
	@Override
	@Transactional
	public void performMapInterpretation(String name, String input) {
		// TODO Auto-generated method stub
		List<Route> allRoutes =  MapInterpreter.interpretateMap(name, input);		
		for (Route route : allRoutes) {
			MapPoint origin = null;
			MapPoint destination = null;
			origin = mapPointDao.findMapPointByProperty("name", route.getOrigin().getName());
			if(origin == null) {
				origin = mapPointDao.saveMapPoint(route.getOrigin());
			}
			
			destination = mapPointDao.findMapPointByProperty("name", route.getDestination().getName());
			if(destination == null) {
				destination = mapPointDao.saveMapPoint(route.getDestination());
			}
			
			//There is no requirement regarding redundant path. Anyway, if there is a duplicate relationship which covers the same nodes, both are considered valid
			routeDao.createRoute(origin, destination, route.getDistance(), route.getMapName());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.services.IMapInterpreterService#getShortestPath(java.lang.String, java.lang.String, float, float)
	 */
	@Override
	public ShortestPath getShortestPath(String origin, String destination,
			float autonomy, float fuelCost) {
		
    	MapPoint startNode = mapPointDao.findMapPointByProperty("name", origin);
    	MapPoint endNode = mapPointDao.findMapPointByProperty("name", destination);

    	
    	
    	ShortestPath returnedPath = mapOperationsService.findShortestPath(startNode, endNode);
		returnedPath.calculateTotalCost(autonomy, fuelCost);
		return returnedPath;
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.services.IMapManagerService#performParallaleMapInterpretation(java.lang.String, java.lang.String)
	 */
	@Override
	public void performParallaleMapInterpretation(String name, String input) {
		// TODO Auto-generated method stub
		
	}
	
}
