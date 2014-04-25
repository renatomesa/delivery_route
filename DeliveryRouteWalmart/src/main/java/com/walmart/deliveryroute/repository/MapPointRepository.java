/**
 * 
 */
package com.walmart.deliveryroute.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.walmart.deliveryroute.model.MapPoint;

/**
 * This inteface extends String Data Neo4J {@link GraphRepository} in order to provide basic operations with data in the graph database for {@link MapPoint} objects.  Requires Spring usage.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface MapPointRepository extends GraphRepository<MapPoint>,  RelationshipOperationsRepository<MapPoint>{

}
