delivery_route
==============

Wallmart admission test.

This repository contains the source code of the admission test for walmart.com for Renato Mesa.

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
