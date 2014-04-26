/**
 * 
 */
package com.walmart.deliveryroute.model.response;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.walmart.deliveryroute.model.MapPoint;

/**
 * POJO which represents a JSON response prototype to be retrieved via REST
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class RouteCostResponse {

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private String origin;
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	List<MapPoint> destinationsVisited;
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Double distance;
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	private Double totalCost;
	
	
	
	/**
	 * @param origin
	 * @param destination
	 * @param distance
	 * @param costPerLiter
	 */
	public RouteCostResponse(String origin, List<MapPoint> destination, double distance,
			double totalCost) {
		this.origin = origin;
		this.destinationsVisited = destination;
		this.distance = distance;
		this.totalCost = totalCost;
	}



	public String getOrigin() {
		return origin;
	}



	public void setOrigin(String origin) {
		this.origin = origin;
	}



	public List<MapPoint> getDestinationsVisited() {
		return destinationsVisited;
	}



	public void setDestinationsVisited(List<MapPoint> destinationsVisited) {
		this.destinationsVisited = destinationsVisited;
	}



	public double getDistance() {
		return distance;
	}



	public void setDistance(double distance) {
		this.distance = distance;
	}



	public double getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	
	
	
}
