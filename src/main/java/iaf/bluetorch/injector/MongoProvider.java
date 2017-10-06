package iaf.bluetorch.injector;

import iaf.bluetorch.core.IRecorderConfig;
import iaf.bluetorch.db.config.IMongoDB;
import iaf.bluetorch.db.config.MongoDB;

import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class MongoProvider implements Provider<IMongoDB> {
	
	@Inject private IRecorderConfig config;
	@Inject private Provider<Logger> loggerProvider;

	@Override
	public IMongoDB get() {
		MongoDB db = new MongoDB(config, loggerProvider.get());
		return db;
	}

}
