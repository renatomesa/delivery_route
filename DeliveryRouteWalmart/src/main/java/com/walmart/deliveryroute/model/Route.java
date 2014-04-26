package com.walmart.deliveryroute.model;

import org.neo4j.graphdb.RelationshipType;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * This class implements a POJO responsible for mapping a path from an origin point to a destination one according with a distance in kilometers
 * associated to them.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@RelationshipEntity(type=Route.RELATIONSHIP_TYPE)
public class Route {

	public static final String RELATIONSHIP_TYPE = "GOES_TO";
	
	/**
	 * This enum represents a {@link RelationshipType} demanded for neo4j database operations
	 * @author renatomesa@gmail.com (Renato Vicari Mesa)
	 *
	 */
    public enum Type implements RelationshipType
    {
        GOES_TO
    }
    
	
	@GraphId
	private Long nodeId;
	
	@StartNode
	private MapPoint origin;
	@EndNode
	private MapPoint destination;
	private String mapName;
	private double distance;
	
	/**
	 * Creates a new Route
	 * @param origin Origin Point
	 * @param destination Destination Point
	 * @param distance Distance in Kilometers
	 */
	public Route(MapPoint origin, MapPoint destination, double distance, String mapName) {
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
		this.mapName = mapName;
	}

	public Route() {}
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public MapPoint getOrigin() {
		return origin;
	}

	public void setOrigin(MapPoint origin) {
		this.origin = origin;
	}

	public MapPoint getDestination() {
		return destination;
	}

	public void setDestination(MapPoint destination) {
		this.destination = destination;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}		
}
