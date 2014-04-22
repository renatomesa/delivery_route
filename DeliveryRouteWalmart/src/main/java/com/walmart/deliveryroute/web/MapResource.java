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

import com.walmart.deliveryroute.model.RouteCostResponse;
import com.walmart.deliveryroute.services.IMapInterpreterService;

/**
 * REST endpoint of the application.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@Component
@Path("/map")
public class MapResource {

@Autowired
private IMapInterpreterService mapInterpreter;
	
/**
 * Calculate the minimum route to a given destination from a given origin.
 * @param origin
 * @param destination
 * @param autonomy
 * @param costPerLiter
 * @return
 */
@GET
@Path("/calcroute")
@Produces(MediaType.APPLICATION_JSON)
public RouteCostResponse calculateCost(@QueryParam("origin") String origin, @QueryParam("destination") String destination,
		@QueryParam("autonomy") int autonomy, @QueryParam("costPerLiter") float costPerLiter) {
	
	//TODO service implementation
	return new RouteCostResponse(origin, new ArrayList<String>(), autonomy, costPerLiter);
}

/**
 * Receives a map as plain-text, parse it and add to database.
 * @param mapName
 * @param text
 * @return
 */
@POST
@Consumes(MediaType.TEXT_PLAIN)
public Response inputMap(@QueryParam("mapName") String mapName, String text) {
	
	mapInterpreter.performMapInterpretation(mapName, text);
	
	return Response.ok().build();
}

} 