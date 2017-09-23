package iaf.bluetorch.injector;

import org.apache.commons.configuration2.Configuration;
import org.apache.logging.log4j.Logger;

import iaf.bluetorch.db.config.IMongoDB;
import iaf.bluetorch.db.config.MongoDB;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class MongoProvider implements Provider<IMongoDB> {
	
	@Inject private Provider<Configuration> configProvider;
	@Inject private Provider<Logger> loggerProvider;

	@Override
	public IMongoDB get() {
		MongoDB db = new MongoDB(configProvider.get(), loggerProvider.get());
		return db;
	}

}
