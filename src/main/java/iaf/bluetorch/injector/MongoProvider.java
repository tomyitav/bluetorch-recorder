package iaf.bluetorch.injector;

import iaf.bluetorch.db.config.IMongoDB;
import iaf.bluetorch.db.config.MongoDB;

import com.google.inject.Provider;

public class MongoProvider implements Provider<IMongoDB> {

	@Override
	public IMongoDB get() {
		MongoDB db = AppInjector.instance().getInjector().getInstance(MongoDB.class);
		db.initializeDatastore();
		return db;
	}

}
