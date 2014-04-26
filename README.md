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

Implementation
==============

The implementation is based on a Java EE Maven project which relies upon Jersey, Spring and Neo4J to run.

It contains REST endpoints that receives map inputs from an user and retrieves shortest path between two points (for more info, please check REST Api doc).

After the input map request is received, a file is saved containing a map and an Executor runs a thread to insert the map into the database. Route insertion was splitted into many transactions
since the performance of only one transaction for a resonable number of routes (500.000) was not good. The REST response is assynchronous (but there is not method to 
check if the map was already parsed or not).

Along this input process, database operations are performed using Spring Data Neo4j repositories, being the Nodes inserted prior to the Path between them, to increase performance (not repeat point insertion).
I used Neo4J 1.8 with Spring Data Neo4J 2.0 since I obtained a better performance in map insertion.


The search for the shortest path is made over a Dijkstra algorithm implementation existant in Neo4J. The algorithm performs well in a environment of 10000 nodes with 500k relationships (less than 3 seconds). Neo 4J
also caches the results after the first search is made, which improves performance of later searches.

Assumptions for performance
=============

My tests were based on maps containing up to 10000 nodes, with 500.000 relationship between them.

The tests were made on a 8gb ram I7 machine and in a I5 notebook containing 4gb of memory.

Installation
=============

Requirements

- Java 7 installed
- Apache Tomcat
- Maven installed


To run the system, follow the procedures below:

- Clone the repository
- With maven installed and added to PATH, run "mvn package" in the root folder of DeliveryRouteWalmart.
- Add the following line to your server.xml file of tomcat:

<Context docBase="DeliveryRouteWalmart" path="/DeliveryRouteWalmart" reloadable="true"/>

- After the .war package is created, rename it to DeliveryRouteWalmart.war and copy it into the webapps folder of your tomcat installation.
- run startup.bat located inside /bin folder of your tomcat installation


What could be improved
=============

Some error handling code was not implemented and also Neo4J batch importer may improve performance of map inputs.
