/**
 * 
 */
package com.walmart.deliveryroute.model;

import java.util.List;

/**
 * POJO which represents a JSON response prototype to be retrieved via REST
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class RouteCostResponse {

	private String origin;
	List<MapPoint> destinationsVisited;
	private double distance;
	private double totalCost;
	
	
	
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
