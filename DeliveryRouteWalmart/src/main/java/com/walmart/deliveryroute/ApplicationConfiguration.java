/**
 * 
 */
package com.walmart.deliveryroute;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.dao.graph.MapPointDAOImpl;
import com.walmart.deliveryroute.dao.graph.RouteDAOImpl;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.services.IMapOperationsService;
import com.walmart.deliveryroute.services.impl.GraphOperationsServiceImpl;
import com.walmart.deliveryroute.services.impl.MapManagerServiceImpl;

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
	
	@Bean(name="MapInterpreterServiceBean")
	public IMapManagerService createMapInterpreterService() {
		return new MapManagerServiceImpl();
	}
	
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

}
