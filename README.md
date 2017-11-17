# bluetorch-recorder #

This project is intended to periodically store entities to MongoDB, using:

 - Akka
 - mongo-java-driver
 - Guice for DI

### Project structure ###

* runnable package - for interacting with actors
* actors - for handling asynchronous operations
* db package - interacting with MongoDB


### How do I get set up? ###

* Git clone project
* Create configuration file, config.properties. Example can be found in test directory - RecorderConfigTest.java
* Create environment variable - LOCAL_CONFIG_DIR that contains path to config file.
* Open command line in project directory
* mvn clean 
* mvn install
* run jar: java -jar target\bluetorch-recorder-0.0.1-SNAPSHOT-jar-with-dependencies.jar
