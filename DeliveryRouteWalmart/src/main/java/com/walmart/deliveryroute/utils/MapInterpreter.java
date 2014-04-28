/**
 * 
 */
package com.walmart.deliveryroute.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.walmart.deliveryroute.exception.IllegalMapRepresentationException;
import com.walmart.deliveryroute.model.MapInterpretationContainer;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;

/**
 * This class exposes static methods for map interpretation. This class should
 * be later generic.
 * 
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 * 
 */
public class MapInterpreter {

	public static MapInterpretationContainer interpretateMap(File f)
			throws IOException {
		FileReader fileReader = new FileReader(f);
		BufferedReader buf = new BufferedReader(fileReader);

		String line = buf.readLine();
		String mapName = line;

		List<Route> subRouteList = new ArrayList<Route>();

		Map<String, MapPoint> mapPoints = new HashMap<String, MapPoint>();
		line = buf.readLine();
		
		List<List<Route>> routeList = new ArrayList<List<Route>>();
		
		int countRoutesBuffer = 0;
		
		while (line != null) {
			if(++countRoutesBuffer >10000) {
				routeList.add(subRouteList);
				subRouteList = new ArrayList<Route>();
				countRoutesBuffer = 0;
			}
			
			lineParser(line, mapPoints, subRouteList, mapName);
			line = buf.readLine();
		}

		if(countRoutesBuffer > 0) {
			routeList.add(subRouteList);
		}
		
		buf.close();
		
		return new MapInterpretationContainer(routeList, mapPoints);
	}

	/**
	 * Utility class responsible for parsing a text input, retrieving
	 * {@link Route} objects
	 * 
	 * @param mapName
	 * @param map
	 * @return A {@link List} of {@link Route} objects, which would be later
	 *         added to the database
	 */
	public static MapInterpretationContainer interpretateMap(String mapName,
			String map) {
		List<Route> subRouteList = new ArrayList<Route>();

		List<List<Route>> routeList = new ArrayList<List<Route>>();
		
		
		Map<String, MapPoint> mapPoints = new HashMap<String, MapPoint>();

		String[] rows = map.split("\n");
		for (String row : rows) {
			lineParser(row, mapPoints, subRouteList, mapName);
		}
		
		routeList.add(subRouteList);
		
		return new MapInterpretationContainer(routeList, mapPoints);
	}

	/**
	 * Parses a line and add to given data structures
	 * @param row
	 * @param mapPoints
	 * @param routeList
	 * @param mapName
	 */
	private static void lineParser(String row, Map<String,MapPoint> mapPoints, List<Route> routeList, String mapName) {

		// maybe change to another tokenizer method, like {@link Scanner}
		StringTokenizer tokens = new StringTokenizer(row, " ");
		if (tokens.countTokens() != 3) {
			// Flow should not proceed since it is not a valid map
			throw new IllegalMapRepresentationException();
		}

		String origin = tokens.nextToken();
		String destination = tokens.nextToken();

		if (origin.equals(destination)) {
			throw new IllegalMapRepresentationException();
		}

		MapPoint originMapPoint = mapPoints.get(origin);
		if (originMapPoint == null) {
			originMapPoint = new MapPoint(origin);
			mapPoints.put(origin, originMapPoint);
		}

		MapPoint destinationMapPoint = mapPoints.get(destination);
		if (destinationMapPoint == null) {
			destinationMapPoint = new MapPoint(destination);
			mapPoints.put(destination, destinationMapPoint);
		}
		int distance = Integer.parseInt(tokens.nextToken());

		Route newRoute = new Route(originMapPoint, destinationMapPoint,
				distance, mapName);
		routeList.add(newRoute);
	}
	
}
