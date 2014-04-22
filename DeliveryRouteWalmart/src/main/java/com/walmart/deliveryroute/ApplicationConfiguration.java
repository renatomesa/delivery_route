/**
 * 
 */
package com.walmart.deliveryroute;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.walmart.deliveryroute.dao.IDeliveryRouteDAO;
import com.walmart.deliveryroute.dao.graph.GraphBasedDeliveryRouteDAOImpl;
import com.walmart.deliveryroute.services.IMapInterpreterService;
import com.walmart.deliveryroute.services.impl.MapInterpreterServiceImpl;

/**
 * Configuration class for Spring
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
@ImportResource("classpath:spring/application-context.xml")
public class ApplicationConfiguration{

	GraphDatabaseService db;
	
	@Bean(name="MapInterpreterServiceBean")
	public IMapInterpreterService createMapInterpreterService() {
		return new MapInterpreterServiceImpl();
	}
			
	@Bean(name="DatabaseDAOBean")
	public IDeliveryRouteDAO getDao(){
		return new GraphBasedDeliveryRouteDAOImpl();
	}
}
