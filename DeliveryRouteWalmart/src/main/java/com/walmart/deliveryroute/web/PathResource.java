package com.walmart.deliveryroute.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.walmart.deliveryroute.Constants;
import com.walmart.deliveryroute.model.ShortestPath;
import com.walmart.deliveryroute.model.response.RouteCostResponse;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.utils.FuelCostCalculator;

/**
 * REST endpoint of the application.
 * 
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 * 
 */
@Component
@Path(Constants.PATH_RESOURCE_URL)
public class PathResource {

	@Autowired
	private IMapManagerService mapInterpreter;

	/**
	 * REST entry point that returns the minimum route to a given destination
	 * from a given origin.
	 * 
	 * @param origin
	 * @param destination
	 * @param autonomy
	 * @param costPerLiter
	 * @return A {@link RouteCostResponse}, which is later transformed in JSON by jersey
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RouteCostResponse calculateCostJson(@QueryParam("origin") String origin,
			@QueryParam("destination") String destination,
			@QueryParam("autonomy") float autonomy,
			@QueryParam("costPerLiter") float costPerLiter) {

		ShortestPath shortestPath = mapInterpreter.getShortestPath(origin,
				destination, autonomy, costPerLiter);

		double cost = FuelCostCalculator.calculateCost(
				shortestPath.getDistance(), autonomy, costPerLiter);
		shortestPath.setTotalCost(cost);
		return new RouteCostResponse(origin, shortestPath.getPoints(),
				shortestPath.getDistance(), shortestPath.getTotalCost());
	}
}