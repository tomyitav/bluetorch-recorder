package iaf.bluetorch.db.config;

import org.mongodb.morphia.Datastore;

public interface IMongoDB {
	public Datastore getDatabase();
}
