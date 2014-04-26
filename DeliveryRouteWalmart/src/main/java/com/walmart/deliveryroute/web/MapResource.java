package com.walmart.deliveryroute.web;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.walmart.deliveryroute.Constants;
import com.walmart.deliveryroute.model.response.JsonResponse;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.services.impl.MapProcessor;

/**
 * REST endpoint of the application.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@Component
@Path(Constants.MAP_RESOURCE_URL)
public class MapResource {

@Autowired
private IMapManagerService mapManager;
	
@Autowired
private MapProcessor mapProcessor;

/**
 * Receives a map as plain-text, parse it and add to database.
 * @param mapName Name of the map
 * @param data Map data
 * @return
 * @throws IOException 
 */
@POST
@Consumes(MediaType.TEXT_PLAIN)
public Response inputMap(@QueryParam("mapName") String mapName, String data) throws IOException {

	//This service is responsible for storing file data and starting a thread to interpretate it.
	mapProcessor.processMap(mapName, data);
	
	return Response.ok(new JsonResponse(Constants.MAP_INSERTION_RESPONSE_SUCCESS_MESSAGE), MediaType.APPLICATION_JSON).build();
}


/**
 * Receives a map as plain-text, parse it and add to database.
 * @param mapName Name of the map
 * @param data Map data
 * @return
 *//*
@POST
@Consumes(MediaType.TEXT_PLAIN)
public Response inputMap(@QueryParam("mapName") String mapName, String data) {
	
	mapInterpreter.performMapInterpretation(mapName, data);
	return Response.ok().build();
}*/

} 