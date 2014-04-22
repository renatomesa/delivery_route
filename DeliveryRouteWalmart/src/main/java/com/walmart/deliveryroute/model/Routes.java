/**
 * 
 */
package com.walmart.deliveryroute.model;

import java.util.List;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class Routes {

	private String origin;
	private List<Route> routes;
	/**
	 * @param origin
	 * @param routes
	 */
	public Routes(String origin, List<Route> routes) {
		super();
		this.origin = origin;
		this.routes = routes;
	}
	public String getOrigin() {
		return origin;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	
	
	
}
