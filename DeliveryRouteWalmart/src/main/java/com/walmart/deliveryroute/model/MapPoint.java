/**
 * 
 */
package com.walmart.deliveryroute.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * POJO which represents a single Node, which can be used as an origin or destination
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@NodeEntity
public class MapPoint {

	@GraphId
	@JsonIgnore
	private Long nodeId;
	@Indexed(unique=true) 
	private String name;
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param name
	 */
	public MapPoint(String name) {
		super();
		this.name = name;
	}
	
	public MapPoint(){
		super();
	}
	
	
}
