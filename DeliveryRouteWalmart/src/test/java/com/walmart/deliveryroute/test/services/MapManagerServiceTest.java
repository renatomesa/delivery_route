/**
 * 
 */
package com.walmart.deliveryroute.test.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.AssertThrows;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.walmart.deliveryroute.dao.IMapPointDAO;
import com.walmart.deliveryroute.dao.IRouteDAO;
import com.walmart.deliveryroute.dao.impl.MapPointDAOImpl;
import com.walmart.deliveryroute.dao.impl.RouteDAOImpl;
import com.walmart.deliveryroute.exception.IllegalMapRepresentationException;
import com.walmart.deliveryroute.model.MapPoint;
import com.walmart.deliveryroute.repository.MapPointRepository;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.services.IMapOperationsService;
import com.walmart.deliveryroute.services.impl.GraphOperationsServiceImpl;
import com.walmart.deliveryroute.test.ApplicationContext;
import com.walmart.mapinterpreter.MapInterpreter;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContext.class)
//@ImportResource("classpath:spring/application-context.xml")
public class MapManagerServiceTest {

	@Autowired IMapManagerService managerService;
	
	//This member is created by Spring Data Neo4J
	@Autowired GraphDatabaseService graphDatabaseService;
	
	@Before
	public void loadMap() throws IOException {	
		String fileData = loadMapFromFile("./src/test/resources/maps/map-10nodes.txt");

		//Test map interpretation and database storage. I avoid using Mockito here to check exactly if database performs well,
		//so more elaborated tests can be performed.
		managerService.performMapInterpretation("map1", fileData);
	}
	
	@After
	public void shutdown() {
		graphDatabaseService.shutdown();
	}
	
	@Test(expected=IllegalMapRepresentationException.class)
	public void testErrorOnMapInterpretation() throws IOException {
		String fileData = loadMapFromFile("./src/test/resources/maps/map-10nodes-error.txt");
		MapInterpreter.interpretateMap("map1", fileData);
	}
	
	/**
	 * This method simply loads a map file from resources folder, simulating the input via web-service.
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private String loadMapFromFile(String filename) throws IOException {
		
		File file = new File(filename);
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
				
		StringBuilder builder = new StringBuilder();
				
		String line = bufferedReader.readLine();
		
		while(line!= null) {
			builder.append(line + "\n");
			line = bufferedReader.readLine();
		}
		
		bufferedReader.close();
		
		return builder.toString();
		
	}
	
}
