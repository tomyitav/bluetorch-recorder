package iaf.bluetorch.db.config;

import iaf.bluetorch.db.entities.BasicEntity;

import org.apache.commons.configuration2.Configuration;
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

	public static final String DB_HOST = "127.0.0.1";
	public static final int DB_PORT = 27017;
	public static final String DB_NAME = "track_history";

	@Inject private Configuration config;
	private Datastore datastore;

	public void initializeDatastore() {
		String dbHost = this.config.getString("database.host");
		System.out.println("InJJJJJJJEEEECCCCCTTTTTEEEEDDDDD" + dbHost);
		MongoClientOptions mongoOptions = MongoClientOptions.builder()
		.socketTimeout(60000) // Wait 1m for a query to finish, https://jira.mongodb.org/browse/JAVA-1076
		.connectTimeout(15000) // Try the initial connection for 15s, http://blog.mongolab.com/2013/10/do-you-want-a-timeout/
		.maxConnectionIdleTime(600000) // Keep idle connections for 10m, so we discard failed connections quickly
		.readPreference(ReadPreference.primaryPreferred()) // Read from the primary, if not available use a secondary
		.build();
	    MongoClient mongoClient;
	    mongoClient = new MongoClient(new ServerAddress(DB_HOST, DB_PORT), mongoOptions);
	
	//    mongoClient.setWriteConcern(WriteConcern.SAFE);
	    this.datastore = new Morphia().mapPackage(BasicEntity.class.getPackage().getName())
		.createDatastore(mongoClient, DB_NAME);
	    this.datastore.ensureIndexes();
	    this.datastore.ensureCaps();
	    System.out.println("Connection to database '" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "' initialized");
	}

	// Creating the mongo connection is expensive - (re)use a singleton for performance reasons.
	// Both the underlying Java driver and Datastore are thread safe.
	public Datastore getDatabase() {
		return datastore;
	}
}