/**
 * 
 */
package com.walmart.deliveryroute.test.services;

import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.deliveryroute.model.ShortestPath;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.test.ApplicationContext;

/**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
// @ImportResource("classpath:spring/application-context.xml")
public class GraphOperationsServiceTest extends TestCase {

    @Autowired
    private org.springframework.context.ApplicationContext ctx;
	
	@Autowired
	IMapManagerService managerService;

	// This member is created by Spring Data Neo4J
	@Autowired
	GraphDatabaseService graphDatabaseService;

	@Before
	public void loadMap() throws IOException {
		
		Map<String, GraphRepository> graphRepositories = ctx
				.getBeansOfType(GraphRepository.class);
		for (GraphRepository graphRepository : graphRepositories.values()) {
			graphRepository.deleteAll();
		}

		//example map given by walmart
		String fileData = "A B 10\nB D 15\nA C 20\nC D 30\nB E 50\nD E 30";

		// Test map interpretation and database storage. I avoid using Mockito
		// here to check exactly if database performs well,
		// so more elaborated tests can be performed.
		managerService.performMapInterpretation("map1", fileData);
				
	}
	
	@Test
	public void testShortestPathSearch() {
		ShortestPath shortestPath = managerService.getShortestPath("A", "D", 10, 2.5f);
		
		//check if retrieved object contains same data as expected
		assertNotNull(shortestPath);
		assertEquals(6.25, shortestPath.getTotalCost());
		
	}
	

	
}
