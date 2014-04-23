/**
 * 
 */
package com.walmart.deliveryroute.model;

import java.util.List;

/**
 * POJO class which is responsible for representing a list of Nodes and a List of relationship between them.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class ShortestPath {

	private List<MapPoint> points;
	private List<Route> routes;
	private double distance;
	private double totalCost;
	
	/**
	 * Creates a new instance based on a list of points and one list of routes.
	 * @param origin
	 * @param routes
	 */
	public ShortestPath(List<MapPoint> points, List<Route> routes, double distance) {
		this.points = points;
		this.routes = routes;
		this.distance = distance;
	}
	
	
	
	/**
	 * Default Constructor
	 */
	public ShortestPath() {
	}



	public List<MapPoint> getPoints() {
		return points;
	}



	public void setPoints(List<MapPoint> points) {
		this.points = points;
	}

	

	public List<Route> getRoutes() {
		return routes;
	}



	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}



	public double getDistance() {
		return distance;
	}



	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double calculateTotalCost(float autonomy, float cost) {
		double costPerKilometer = cost / autonomy;
		totalCost = distance * costPerKilometer;
		return totalCost;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
}
