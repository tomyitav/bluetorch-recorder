# bluetorch-recorder #

This project is intended to periodically store entities to MongoDB, using:

 - Akka
 - Morphia as mongo ODM
 - Guice for DI

### Project structure ###

* runnable package - for interacting with actors
* actors - for handling asynchronous operations
* db package - interacting with MongoDB


### How do I get set up? ###

* Git clone project
* mvn clean install
* Create configuration file, config.properties. Example can be found in test directory - RecorderConfigTest
* Create environment variable - LOCAL_CONFIG_DIR that contains path to config file.
* run main class
