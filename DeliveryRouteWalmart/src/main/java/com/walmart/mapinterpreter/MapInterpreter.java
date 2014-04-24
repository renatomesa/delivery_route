/**
 * 
 */
package com.walmart.mapinterpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.walmart.deliveryroute.exception.IllegalMapRepresentationException;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;

/**
 * This class exposes static methods for map interpretation. This class should be later generic.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class MapInterpreter {
		
	/**
	 * Parses a map.
	 * @param mapName
	 * @param map
	 * @return A {@link List} of {@link Route} objects, which would be later added to the database
	 */
	public static List<Route> interpretateMap(String mapName, String map) {
		List<Route> routeList = new ArrayList<Route>();
		
		String[] rows = map.split("\n");
		for (String row : rows) {
			
			//maybe change to another tokenizer method, like {@link Scanner}
			StringTokenizer tokens = new StringTokenizer(row, " ");
			if(tokens.countTokens() != 3) {
				//Flow should not proceed since it is not a valid map
				throw new IllegalMapRepresentationException();
			}						
			
			String origin = tokens.nextToken();
			String destination = tokens.nextToken();
			int distance = Integer.parseInt(tokens.nextToken());
						
			Route newRoute = new Route(new MapPoint(origin), new MapPoint(destination), distance, mapName);
			routeList.add(newRoute);
		}
		
		return routeList;
	}
	
}
