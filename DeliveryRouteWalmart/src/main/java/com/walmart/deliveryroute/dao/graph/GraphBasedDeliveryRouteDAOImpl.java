/**
 * 
 */
package com.walmart.deliveryroute.dao.graph;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.util.StringLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.deliveryroute.dao.IDeliveryRouteDAO;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.repository.MapPointRepository;
import com.walmart.deliveryroute.repository.RouteRepository;

/**
 * A database access object implementation for routes built upon a graph database system
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class GraphBasedDeliveryRouteDAOImpl implements IDeliveryRouteDAO {

	//@Autowired
	GraphDatabaseService databaseService;
	
	private ExecutionEngine engine;
	
	private StringLogger stringLogger = StringLogger.logger(new File("log.txt"));
	
	@Autowired
	private Neo4jTemplate template;
	
	@Autowired
	private MapPointRepository mapPointRepository;
	
	@Autowired
	private RouteRepository routeRepository;
	
	public GraphBasedDeliveryRouteDAOImpl() {
		
		this.databaseService = databaseService;
		
	}
	
	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IDeliveryRouteDAO#createDatabase(java.lang.String)
	 */
	@Override
	public void createDatabase(String name) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IDeliveryRouteDAO#updateDatabase()
	 */
	@Override
	public void updateDatabase() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IDeliveryRouteDAO#deleteFromDatabase()
	 */
	@Override
	public void deleteFromDatabase() {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IDeliveryRouteDAO#insertIntoDatabase()
	 */
	@Override
	@Transactional
	public void insertIntoDatabase() {

		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IDeliveryRouteDAO#queryDatabase()
	 */
	@Override
	@Transactional
	public void queryDatabase() {
		// TODO Auto-generated method stub
		//Just for testing
		template.save(new MapPoint("A"));
		template.save(new MapPoint("B"));
		template.save(new MapPoint("C"));
		template.save(new MapPoint("D"));
		
		 GraphRepository<MapPoint> movieRepository =
                 template.repositoryFor(MapPoint.class);
		 
		 EndResult<MapPoint> points = mapPointRepository.findAll();
				
		 for (MapPoint mapPoint : points) {
			System.out.println(mapPoint.getName());
		}
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IDeliveryRouteDAO#insertRouteListIntoDatabase(java.util.List)
	 */
	@Override
	@Transactional
	public void insertRouteListIntoDatabase(List<Route> routes) {
		for (Route route : routes) {
			MapPoint origin = null;
			MapPoint destination = null;
			origin = mapPointRepository.findByPropertyValue("name", route.getOrigin().getName());
			if(origin == null) {
				origin = mapPointRepository.save(route.getOrigin());
			}
			
			destination = mapPointRepository.findByPropertyValue("name", route.getDestination().getName());
			if(destination == null) {
				destination = mapPointRepository.save(route.getDestination());
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("distance", route.getDistance());
			map.put("mapName", route.getMapName());
			
			Route routeCreated = template.createRelationshipBetween(origin, destination, Route.class,"GOES_TO", false);
			
			routeCreated.setDistance(route.getDistance());
			routeCreated.setMapName(route.getMapName());
			template.save(routeCreated);
		}
		
	}

}
