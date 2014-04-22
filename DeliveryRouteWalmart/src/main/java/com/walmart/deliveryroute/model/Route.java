package com.walmart.deliveryroute.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * This class implements a POJO responsible for mapping a path from an origin point to a destination one according with a distance in kilometers
 * associated to them.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@RelationshipEntity(type="GOES_TO")
public class Route {

	@GraphId
	private Long nodeId;
	
	@StartNode
	private MapPoint origin;
	@EndNode
	private MapPoint destination;
	private String mapName;
	private int distance;
	
	/**
	 * Creates a new Route
	 * @param origin Origin Point
	 * @param destination Destination Point
	 * @param distance Distance in Kilometers
	 */
	public Route(MapPoint origin, MapPoint destination, int distance, String mapName) {
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

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}		
}
