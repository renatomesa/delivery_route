/**
 * 
 */
package com.walmart.deliveryroute.test.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.ws.rs.core.Response;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walmart.deliveryroute.exception.IllegalMapRepresentationException;
import com.walmart.deliveryroute.model.MapInterpretationContainer;
import com.walmart.deliveryroute.services.IMapManagerService;
import com.walmart.deliveryroute.test.ApplicationContext;
import com.walmart.deliveryroute.utils.MapFileUtils;
import com.walmart.deliveryroute.utils.MapInterpreter;
import com.walmart.deliveryroute.web.MapResource;

/**
 * Test class which test main features of the system, which are: map creation
 * and shortest path search
 * 
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@SuppressWarnings("rawtypes")
public class MapManagerServiceTest extends TestCase {

    @Autowired
    private org.springframework.context.ApplicationContext ctx;
	
	@Autowired
	IMapManagerService managerService;

	// This member is created by Spring Data Neo4J
	@Autowired
	GraphDatabaseService graphDatabaseService;
	
	@Autowired
	MapResource mapResource;

	@Before
	public void loadMap() throws IOException {
		
		Map<String, GraphRepository> graphRepositories = ctx
				.getBeansOfType(GraphRepository.class);
		for (GraphRepository graphRepository : graphRepositories.values()) {
			graphRepository.deleteAll();
		}

		String fileData = loadMapFromFile("./src/test/resources/maps/map-10nodes.txt");

		// Test map interpretation and database storage. I avoid using Mockito
		// here to check exactly if database performs well,
		// so more elaborated tests can be performed.
		MapInterpretationContainer container = managerService.performMapInterpretation("map1", fileData);
		managerService.insertMap(container);
	}

	@Test(expected = IllegalMapRepresentationException.class)
	public void testErrorOnMapInterpretation() throws IOException {
		String fileData = loadMapFromFile("./src/test/resources/maps/map-10nodes-error.txt");
		MapInterpreter.interpretateMap("map1", fileData);
	}
	
	@Test
	public void testMapInterpreter() throws IOException {
		String fileData = loadMapFromFile("./src/test/resources/maps/map-10nodes.txt");
		assertNotNull(MapInterpreter.interpretateMap("map1", fileData));
	}

	@Test
	public void testSaveMapStreamAndInterprete() throws IOException {
		File f = MapFileUtils.saveMapStream("name", "A B 10");
		MapInterpreter.interpretateMap(f);
		
		if(f!= null) {
			f.delete();
		}
	}
	
	@Test
	public void testMapInterpretationAsFile() throws IOException {
		File f = new File("./src/test/resources/maps/map-500kRelationships.txt");
		MapInterpreter.interpretateMap(f);
	}
	
	@Test
	public void testMapInsertionWebResource() throws IOException {
		String fileData = loadMapFromFile("./src/test/resources/maps/map-10nodes.txt");
		Response response = mapResource.inputMap("testMap", fileData);
		//assertEquals(response.isStatus(),true);
	}
	
	/**
	 * This method simply loads a map file from resources folder, simulating the
	 * input via web-service.
	 * 
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

		while (line != null) {
			builder.append(line + "\n");
			line = bufferedReader.readLine();
		}

		bufferedReader.close();

		return builder.toString();

	}

}
