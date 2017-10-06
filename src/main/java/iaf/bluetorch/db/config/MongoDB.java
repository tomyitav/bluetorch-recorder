package iaf.bluetorch.db.config;

import iaf.bluetorch.core.IRecorderConfig;
import iaf.bluetorch.db.entities.BasicEntity;

import org.apache.logging.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

/**
 * MongoDB providing the database connection for main.
 */
@Singleton
public class MongoDB implements IMongoDB {

	private IRecorderConfig config;
	private Logger logger;
	private Datastore datastore;
	
	@Inject 
	public MongoDB(IRecorderConfig configuration, Logger logger) {
		this.logger = logger;
		this.config = configuration;
		this.datastore = initializeDatastore();
	}

	private Datastore initializeDatastore() {
		String dbHost = this.config.dbHost();
		int dbPort = this.config.dbPort();
		String dbName = this.config.dbName();
		int socketTimeout = this.config.dbSocketTimeout();
		int connectTimeout = this.config.dbConnectTimeout();
		int maxConnectionIdleTime = this.config.dbMaxConnectionIdleTime();
		
		MongoClientOptions mongoOptions = MongoClientOptions.builder()
		.socketTimeout(socketTimeout) // Wait 1m for a query to finish, https://jira.mongodb.org/browse/JAVA-1076
		.connectTimeout(connectTimeout) // Try the initial connection for 15s, http://blog.mongolab.com/2013/10/do-you-want-a-timeout/
		.maxConnectionIdleTime(maxConnectionIdleTime) // Keep idle connections for 10m, so we discard failed connections quickly
		.readPreference(ReadPreference.primaryPreferred()) // Read from the primary, if not available use a secondary
		.build();
	    MongoClient mongoClient;
	    mongoClient = new MongoClient(new ServerAddress(dbHost, dbPort), mongoOptions);
	
	//    mongoClient.setWriteConcern(WriteConcern.SAFE);
	    Datastore datastore = new Morphia().mapPackage(BasicEntity.class.getPackage().getName())
		.createDatastore(mongoClient, dbName);
	    datastore.ensureIndexes();
	    datastore.ensureCaps();
	    logger.info("Connection to database '" + dbHost + ":" + dbPort + "/" + dbPort + "' initialized");
	    return datastore;
	}

	public Datastore getDatabase() {
		return datastore;
	}
}