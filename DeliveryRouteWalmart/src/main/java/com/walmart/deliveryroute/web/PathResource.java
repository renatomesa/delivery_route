package com.walmart.deliveryroute.web;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.walmart.deliveryroute.Constants;
import com.walmart.deliveryroute.model.RouteCostResponse;
import com.walmart.deliveryroute.model.ShortestPath;
import com.walmart.deliveryroute.services.IMapManagerService;

/**
 * REST endpoint of the application.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@Component
@Path(Constants.PATH_RESOURCE_URL)
public class PathResource {

@Autowired
private IMapManagerService mapInterpreter;
	
/**
 * REST entry point that returns the minimum route to a given destination from a given origin.
 * @param origin
 * @param destination
 * @param autonomy
 * @param costPerLiter
 * @return
 */
@GET
@Produces(MediaType.APPLICATION_JSON)
public RouteCostResponse calculateCost(@QueryParam("origin") String origin, @QueryParam("destination") String destination,
		@QueryParam("autonomy") float autonomy, @QueryParam("costPerLiter") float costPerLiter) {
	
	ShortestPath shortestPath = mapInterpreter.getShortestPath(origin, destination, autonomy, costPerLiter);
	
	return new RouteCostResponse(origin, shortestPath.getPoints(), shortestPath.getDistance(), shortestPath.getTotalCost());
}

} 