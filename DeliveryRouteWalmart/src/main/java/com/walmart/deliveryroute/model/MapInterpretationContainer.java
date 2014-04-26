package com.walmart.deliveryroute.model;

import java.util.List;
import java.util.Map;

public class MapInterpretationContainer {

	private List<Route> routeList;
	private Map<String, MapPoint> mapPoints;
	public List<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}
	public Map<String, MapPoint> getMapPoints() {
		return mapPoints;
	}
	public void setMapPoints(Map<String, MapPoint> mapPoints) {
		this.mapPoints = mapPoints;
	}
	public MapInterpretationContainer(List<Route> routeList,
			Map<String, MapPoint> mapPoints) {
		super();
		this.routeList = routeList;
		this.mapPoints = mapPoints;
	}
		
}
