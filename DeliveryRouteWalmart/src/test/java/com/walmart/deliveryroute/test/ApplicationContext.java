/**
 * 
 */
package com.walmart.deliveryroute.test;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.dao.impl.MapPointDAOImpl;
import com.walmart.deliveryroute.dao.impl.RouteDAOImpl;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.services.IMapOperationsService;
import com.walmart.deliveryroute.services.impl.GraphOperationsServiceImpl;
import com.walmart.deliveryroute.services.impl.MapManagerServiceImpl;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@Configuration
@EnableNeo4jRepositories
@ImportResource("classpath:spring/application-context-tests.xml")
public class ApplicationContext {
	
	@Bean(name="MapOperationsDaoBean")
	public IMapOperationsService getMapOperationsService(){
		return new GraphOperationsServiceImpl();
	}
	
	@Bean(name="RouteDAOBean")
	public IRouteDAO getRouteDao(){
		return new RouteDAOImpl();
	}

	@Bean(name="MapPointDAOBean")
	public IMapPointDAO getMapPointDao(){
		return new MapPointDAOImpl();
	}
	
	@Bean(name="MapInterpreterServiceBean")
	public IMapManagerService createMapInterpreterService() {
		return new MapManagerServiceImpl();
	}
	
}