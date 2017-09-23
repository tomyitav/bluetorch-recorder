package iaf.bluetorch.injector;

import org.apache.commons.configuration2.Configuration;

import iaf.bluetorch.db.config.IMongoDB;
import iaf.bluetorch.db.config.MongoDB;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class MongoProvider implements Provider<IMongoDB> {
	
	@Inject private Provider<Configuration> configProvider;

	@Override
	public IMongoDB get() {
		MongoDB db = new MongoDB(configProvider.get());
		return db;
	}

}
