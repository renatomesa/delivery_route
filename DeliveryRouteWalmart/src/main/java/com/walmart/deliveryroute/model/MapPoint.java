/**
 * 
 */
package com.walmart.deliveryroute.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * POJO which represents a single Node
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
