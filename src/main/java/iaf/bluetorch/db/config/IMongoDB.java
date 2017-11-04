package iaf.bluetorch.db.config;

import com.mongodb.client.MongoDatabase;

public interface IMongoDB {
	public MongoDatabase getDatabase();
}
