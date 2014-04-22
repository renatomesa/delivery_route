/**
 * 
 */
package com.walmart.deliveryroute.dao;

import java.util.List;

import com.walmart.deliveryroute.model.Route;

/**
 * Defines how a {@link Route} database access object should behave
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface IDeliveryRouteDAO {

	public void createDatabase(String name);
	
	public void updateDatabase();
	
	public void deleteFromDatabase();
	
	public void insertIntoDatabase();
	
	public void queryDatabase();
	
	/**
	 * Insert the given routes to the database. 
	 * @param routes Represented by a {@link List} of {@link Route} objects
	 */
	public void insertRouteListIntoDatabase(List<Route> routes);
	
}
