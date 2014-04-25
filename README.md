delivery_route
==============

Wallmart admission test.

This repository contains the source code of the admission test for walmart.com for Renato Mesa.


Goal
==============
The main goal of the project is to store map structures represented by the following information:

1-Origin 	2-Destination 	3-Distance

A 			B 				10

C 			F  				40

...

Campinas 	Barueri			90

SaoPaulo	Campinas		100

By analyzing the structure of the map it is possible to conclude that this is a problem based on a graph structure
where a shortest weighted path should be calculated returned for the user when he/she queries an origin point, a final destination
and some data regarding fuel consumption.

My first goal was to check if a Graph Database is viable and how could I achieve an implementation using it. My first (and current)
choice is Neo4j embedded database and spent some time studying how they work and doing some examples. I believe that using a database
designed for relationship is better than a relation one in some aspects:

1- Recent technology (I will learn during project implementation)

2- Designed for relationship of data

3- Contains a Djikstra algorithm implementation

4- Performance is better when amount of data is increased (according to reports)

My intention is to make the database operational retrieving some queries results and, after that, implement mechanisms that can improve
performance and responsiviness of the system. At last, a web interface to demonstrate system operation.

For now, the system is a Maven project implemented in Java, using Spring and Jersey to implement REST servlet and manage depency injection of the services. Spring
is also used with Neo4j for transaction control and database operations. 

Implementation Details
==============

As mentioned previously, this implementation is based on a Jersey Servlet with Spring CDI and exposes REST Apis using JAX-RS.

The map files, once received, are stored in a text file which also contains the map name. Once the file is saved, the REST resource returns 200 OK response to the user informing that the map will be processed. Just before the 200 OK response, MapProcessor service is called and, at there, maps are processed within an ExecutorService. By default, I kept the executor service containing only one thread

Since it is a graph problem, it runs an Embedded Graph Database (Neo4J) in version 1.8.1 with Spring Data Neo4J (SDN) plugin, which offers Transactional, repository, etc. It may be not the best way to obtain quick responses from database, but I believe that the abstraction offered by the plugin is  worth when thinking about maintenance and scalability. However, latest version of Neo4J with SDN plugin presented performance issues when importing a map when compared to the chosen version, and that's the reason why I decided to keep using Neo4J 1.8.1.

As mentioned previously, Neo4J offers support to Dijkstra algorithm and also for Cypher queries, which can enable much more performance than a relational database. Even though Cypher is not used in this project, it is a powerful tool to administrate a graph database.

While processing the map, the file is read and MapPoints objects and Route objects are created. MapPoints are firstly added to the database (during the develipment I created a simple cache to avoid duplications in this step and gained 40% of performance) and then Route, which are the relationship entities are added.

Just after that, the map is commited to database and the search engine will recognize the recently added structures.

What is missing
==============
- Error handling for some cases were not implemented
- I could not implement and test a Batch importer for the Map files, but this is an alternative to gain performance in map creation.
- There is no mechanism for the user to identify if a map is already processed or if it is processing.




