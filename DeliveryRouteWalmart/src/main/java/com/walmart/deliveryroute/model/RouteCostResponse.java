/**
 * 
 */
package com.walmart.deliveryroute.model;

import java.util.List;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class RouteCostResponse {

	private String origin;
	List<String> destinationsVisited;
	private int distance;
	private float totalCost;
	
	
	
	/**
	 * @param origin
	 * @param destination
	 * @param distance
	 * @param costPerLiter
	 */
	public RouteCostResponse(String origin, List<String> destination, int distance,
			float totalCost) {
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



	public List<String> getDestinationsVisited() {
		return destinationsVisited;
	}



	public void setDestinationsVisited(List<String> destinationsVisited) {
		this.destinationsVisited = destinationsVisited;
	}



	public int getDistance() {
		return distance;
	}



	public void setDistance(int distance) {
		this.distance = distance;
	}



	public float getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	
	
	
}
