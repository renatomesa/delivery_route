/**
 * 
 */
package com.walmart.deliveryroute.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.dao.impl.MapPointDAOImpl;
import com.walmart.deliveryroute.dao.impl.RouteDAOImpl;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.services.IMapOperationsService;
import com.walmart.deliveryroute.services.impl.GraphOperationsServiceImpl;
import com.walmart.deliveryroute.services.impl.MapManagerServiceImpl;
import com.walmart.deliveryroute.services.impl.MapProcessor;
import com.walmart.deliveryroute.web.MapResource;

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
	
	@Bean(name="MapResourceBean")
	public MapResource createMapResource() {
		return new MapResource();
	}
	
	@Bean(name="TestTransactionalBean")
	public MapProcessor testTransactional() {
		return new MapProcessor();
	}
	
}