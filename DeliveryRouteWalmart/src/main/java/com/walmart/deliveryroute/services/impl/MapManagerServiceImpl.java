/**
 * 
 */
package com.walmart.deliveryroute.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.model.MapInterpretationContainer;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.model.ShortestPath;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.services.IMapOperationsService;
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
	
	
	ExecutorService executorService;

	public MapManagerServiceImpl() {
		this.executorService = Executors.newFixedThreadPool(5);
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.services.IMapInterpreterService#test()
	 */
	@Override
	@Transactional
	public void performMapInterpretation(String name, String input) {		
		MapInterpretationContainer result = MapInterpreter.interpretateMap(name, input);
		createMapStructure(result);
	}
	
	@Override
	@Transactional
	public void performMapInterpretation(File f) throws IOException {
		MapInterpretationContainer result = MapInterpreter.interpretateMap(f);
		System.out.println("Starting creating data into Database");
		createMapStructure(result);
		System.out.println("Finished creating data into Database");
		
		
	}	
	
	private void createMapStructure(MapInterpretationContainer mapRecords) {
		//Firstly all map points are inserted
		Map<String, MapPoint> nodes = mapRecords.getMapPoints();
		Set<String> nodesSet = nodes.keySet();
		
		System.out.println("Map Points to be Created");
		
		for (String string : nodesSet) {
			MapPoint mapPoint = nodes.get(string);
			//create or replace
			MapPoint createdMapPoint = mapPointDao.saveMapPoint(mapPoint);
			mapPoint.setNodeId(createdMapPoint.getNodeId());
		}
		
		System.out.println("Created Map Points");
		
		// Then later the routes are added between them
		List<Route> allRoutes =  mapRecords.getRouteList();		

		for (Route route : allRoutes) {
			MapPoint origin = null;
			MapPoint destination = null;
			origin = nodes.get(route.getOrigin().getName());
			
			destination = nodes.get(route.getDestination().getName());

			//There is no requirement regarding redundant path. Anyway, if there is a duplicate relationship which covers the same nodes, both are considered valid
			routeDao.createRoute(origin, destination, route.getDistance(), route.getMapName());
		}
		
		System.out.println("Relationships Created");
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
		return returnedPath;
	}
		
}
