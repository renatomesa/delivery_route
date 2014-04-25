/**
 * 
 */
package com.walmart.deliveryroute.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.model.Route;

/**
 *  This interface extends String Data Neo4J {@link GraphRepository} in order to provide basic operations with data in the graph database for {@link Route} objects. Requires Spring usage due dependecy injection.
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
public interface RouteRepository {

}
