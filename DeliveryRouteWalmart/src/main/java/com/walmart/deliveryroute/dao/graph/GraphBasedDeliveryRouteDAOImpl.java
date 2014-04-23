/**
 * 
 *//*
package com.walmart.deliveryroute.dao.graph;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.neo4j.cypher.ExecutionEngine;
import org.neo4j.cypher.ExecutionResult;
import org.neo4j.graphalgo.CommonEvaluators;
import org.neo4j.graphalgo.CostEvaluator;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipExpander;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.kernel.Traversal;
import org.neo4j.kernel.impl.traversal.TraversalDescriptionImpl;
import org.neo4j.kernel.impl.util.StringLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.ShortestPath;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.repository.MapPointRepository;
import com.walmart.deliveryroute.repository.RouteRepository;

*//**
 * A database access object implementation for routes built upon a graph database system
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 *//*
public class GraphBasedDeliveryRouteDAOImpl{

	
	public GraphBasedDeliveryRouteDAOImpl() {
	}
	

	
    public ShortestPath findShortestPath( String origin, String destination )
    {
    	MapPoint startNode = mapPointRepository.findByPropertyValue("name", origin);
    	MapPoint endNode = mapPointRepository.findByPropertyValue("name", destination);
    	
        Node start = template.getNode( startNode.getNodeId() );
        Node end = template.getNode( endNode.getNodeId() );
        WeightedPath path = dijkstraPathFinder.findSinglePath( start, end );

        List<MapPoint> points = new LinkedList<MapPoint>();
        List<Route> routes = new LinkedList<Route>();
        
        Iterable<Node> nodes = path.nodes();
        Iterable<Relationship> relationships = path.relationships();
        for (Node node : nodes) {
			MapPoint mapPoint = new MapPoint((String) node.getProperty("name"));
			points.add(mapPoint);
		}
        
        for (Relationship relationship : relationships) {
			Route route = new Route(null, null, (Integer) relationship.getProperty("distance"), (String) relationship.getProperty("mapName")) ;
			routes.add(route);
		}
        
        ShortestPath returnPath = new ShortestPath(points, routes, path.weight());
        
        return returnPath;
        
    }

	 (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IDeliveryRouteDAO#insertRouteListIntoDatabase(java.util.List)
	 
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

	 (non-Javadoc)
	 * @see com.walmart.deliveryroute.dao.IDeliveryRouteDAO#queryShortestPath(java.lang.String, java.lang.String)
	 
	public void queryShortestPath(String origin, String destination) {
		//findShortestPath(origin, destination);
		findShortestPath(origin, destination);
		
	}
	
	
	
    private static final String COST = "cost";
    private static final RelationshipExpander expander;
    private static final CostEvaluator<Double> costEvaluator;
    private static final PathFinder<WeightedPath> dijkstraPathFinder;

    public enum MyDijkstraTypes implements RelationshipType
    {
        GOES_TO
    }
    
    static
    {
        // set up path finder
        expander = Traversal.expanderForTypes(
                Route.Type.GOES_TO, Direction.OUTGOING );
        costEvaluator = CommonEvaluators.doubleCostEvaluator( "distance" );
        dijkstraPathFinder = GraphAlgoFactory.dijkstra( expander, costEvaluator );
    }

    *//**
     * Find the path.
     *//*
    private void runDijkstraPathFinder(String origin, String destination)
    {
    	
    	MapPoint startNode = mapPointRepository.findByPropertyValue("name", origin);
    	MapPoint endNode = mapPointRepository.findByPropertyValue("name", destination);
    	    	
        Node start = template.getNode( startNode.getNodeId() );
        Node end = template.getNode( endNode.getNodeId() );
        WeightedPath path = dijkstraPathFinder.findSinglePath( start, end );
        for ( Node node : path.nodes() )
        {
        	System.out.println( node.getProperty( "name" ) );
            
            
        }
    }

}
*/