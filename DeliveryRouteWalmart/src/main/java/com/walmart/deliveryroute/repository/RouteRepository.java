/**
 * 
 */
package com.walmart.deliveryroute.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.walmart.deliveryroute.model.Route;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface RouteRepository extends GraphRepository<Route>{

}
