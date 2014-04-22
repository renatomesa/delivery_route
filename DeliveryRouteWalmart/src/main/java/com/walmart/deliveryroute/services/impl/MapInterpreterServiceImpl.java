/**
 * 
 */
package com.walmart.deliveryroute.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.walmart.deliveryroute.dao.IDeliveryRouteDAO;
import com.walmart.deliveryroute.model.Route;
import com.walmart.deliveryroute.services.IMapInterpreterService;
import com.walmart.mapinterpreter.MapInterpreter;

/**
 * This class implements {@link IMapInterpreterService} and it is responsible for interpreting a map input,
 * creating a data structure
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public class MapInterpreterServiceImpl implements IMapInterpreterService {

	@Autowired
	IDeliveryRouteDAO dao;
	
	public MapInterpreterServiceImpl() {
		
	}

	/* (non-Javadoc)
	 * @see com.walmart.deliveryroute.services.IMapInterpreterService#test()
	 */
	@Override
	public void performMapInterpretation(String name, String input) {
		// TODO Auto-generated method stub
		List<Route> allRoutes =  MapInterpreter.interpretateMap(name, input);
		
		//dao.queryDatabase();
		
		dao.insertRouteListIntoDatabase(allRoutes);
		//need to store Map...
	}
	
}
