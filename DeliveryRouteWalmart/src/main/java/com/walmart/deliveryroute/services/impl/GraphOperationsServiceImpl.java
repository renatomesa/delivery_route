/**
 * 
 */
package com.walmart.deliveryroute.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphalgo.CommonEvaluators;
import org.neo4j.graphalgo.CostEvaluator;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PathExpander;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipExpander;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.kernel.Traversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.model.ShortestPath;
import com.walmart.deliveryroute.services.IMapOperationsService;

/**
 * This {@link IMapOperationsService} implementation provides methods to perform operations over a map into a Graph.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class GraphOperationsServiceImpl implements IMapOperationsService {

    private final PathExpander expander;
    private final CostEvaluator<Double> costEvaluator;
    private final PathFinder<WeightedPath> dijkstraPathFinder;

    {
    	//Initialize search engine
    	expander = Traversal.pathExpanderForTypes(Route.Type.GOES_TO, Direction.OUTGOING);
        costEvaluator = CommonEvaluators.doubleCostEvaluator( "distance" );
        dijkstraPathFinder = GraphAlgoFactory.dijkstra( expander, costEvaluator );
    }
	
	@Autowired
	Neo4jTemplate template;
	
	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.services.IGraphOperationsService#findShortestPathUsingDjikstraAlgorithm()
	 */
	@Override
	public ShortestPath findShortestPath(MapPoint startNode, MapPoint endNode) {
     	
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
			Route route = new Route(null, null, (Double) relationship.getProperty("distance"), (String) relationship.getProperty("mapName")) ;
			routes.add(route);
		}
        
        ShortestPath returnPath = new ShortestPath(points, routes, path.weight());
        
        return returnPath;
	}

}
