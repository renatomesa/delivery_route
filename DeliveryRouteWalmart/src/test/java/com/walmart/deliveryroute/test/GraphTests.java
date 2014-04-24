/**
 * 
 *//*
package com.walmart.deliveryroute.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.CRUDRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

*//**
 * @author renatomesa@gmail.com (Renato Vicari Mesa)
 *
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GraphTests {

    @Configuration
    @EnableNeo4jRepositories
    static class TestConfig extends Neo4jConfiguration {

        TestConfig() throws ClassNotFoundException {
           setBasePackage("org.springframework.data.neo4j.repository","org.springframework.data.neo4j.model");
        }

        @Bean
        GraphDatabaseService graphDatabaseService() {
            return new TestGraphDatabaseFactory().newImpermanentDatabase();
        }

    }
	
    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Autowired
    private Neo4jTemplate template;
	
	@Before
	public void setUp() throws Exception {
	    Neo4jHelper.cleanDb(graphDatabaseService, false);

	    CRUDRepository<Ingredient> ingredientRepository = template.repositoryFor(Ingredient.class);

	    Transaction tx = graphDatabaseService.beginTx();
	    try {
	        chocolate = ingredientRepository.save(new Ingredient("chocolate"));
	        fish = ingredientRepository.save(new Ingredient("fish"));
	        spice = ingredientRepository.save(new Ingredient("spice x"));
	        oliveOil = ingredientRepository.save(new Ingredient("olive oil"));
	        pear = ingredientRepository.save(new Ingredient("pear"));
	        nakedChef = template.repositoryFor(CookBook.class).save(new CookBook("Naked Chef"));
	        baking101 = template.repositoryFor(CookBook.class).save(new CookBook("baking101"));
	        recipeRepository.save(new Recipe("Hugh", "Bouillabaisse", fish, null, null));
	        recipeRepository.save(new Recipe("Hugh", "pear frangipane", pear, null, baking101));
	        recipeRepository.save(new Recipe("The Colonel", "fried chicken", null, spice, null));
	        recipeRepository.save(new Recipe("Jamie", "pesto", oliveOil, null, nakedChef));
	        focaccia = recipeRepository.save(new Recipe("Hugh", "focaccia", oliveOil, null, baking101));

	        chocolateFudgeCake = recipeRepository.save(new Recipe("Nigella", "Chocolate Fudge cake", chocolate, null, null));
	        whiteChocolateSquares = recipeRepository.save(new Recipe("Heston", "White Chocolate squares", chocolate, null, null));

	        dish = dishRepository.save(new Dish(100));
	        tx.success();
	    } finally {
	        tx.close();
	    }
	    tx = graphDatabaseService.beginTx();
	    try {
	        for (Node node : graphDatabaseService.getAllNodes()) {
	            System.out.println("node = " + node);
	        }
	        tx.success();
	    } finally {
	        tx.close();
	    }

	    transaction = graphDatabaseService.beginTx();
	}
	
	@After
	public void tearDown() throws Exception {
	    if (transaction!=null) {
	        transaction.success();
	        transaction.close();
	        transaction = null;
	    }
	}
	
}




*/