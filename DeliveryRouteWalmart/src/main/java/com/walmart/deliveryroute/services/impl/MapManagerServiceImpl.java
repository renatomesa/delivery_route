/**
 * 
 */
package com.walmart.deliveryroute.services.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.model.ShortestPath;
import com.walmart.deliveryroute.services.IMapOperationsService;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.mapinterpreter.MapInterpreter;

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
	
	public MapManagerServiceImpl() {
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.services.IMapInterpreterService#test()
	 */
	@Override
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
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("distance", route.getDistance());
			map.put("mapName", route.getMapName());
			
			routeDao.createRoute(origin, destination, route.getDistance(), route.getMapName());
			
/*			routeCreated.setDistance(route.getDistance());
			routeCreated.setMapName(route.getMapName());
			template.save(routeCreated);*/
		}
		//need to store Map...
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
	
}
