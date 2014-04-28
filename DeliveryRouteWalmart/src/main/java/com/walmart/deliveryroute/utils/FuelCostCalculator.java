package com.walmart.deliveryroute.utils;

/**
 * Class which contains static methods for calculations of fuel cost
 * @author Renato
 *
 */
public class FuelCostCalculator {

	/**
	 * Calculates the fuel cost of the shortest path
	 * @param distance
	 * @param autonomy
	 * @param cost
	 * @return
	 */
	public static double calculateCost(double distance, float autonomy, float cost) {
		return distance * (cost / autonomy);
	}
	
}
