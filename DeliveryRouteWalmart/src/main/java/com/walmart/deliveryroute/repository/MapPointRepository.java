/**
 * 
 */
package com.walmart.deliveryroute.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.walmart.deliveryroute.model.MapPoint;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface MapPointRepository extends GraphRepository<MapPoint>{

}
