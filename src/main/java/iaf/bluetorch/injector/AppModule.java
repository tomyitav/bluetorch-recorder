package iaf.bluetorch.injector;

import org.apache.commons.configuration2.Configuration;
import org.apache.logging.log4j.Logger;

import iaf.bluetorch.db.config.IMongoDB;
import iaf.bluetorch.db.config.MongoDB;
import iaf.bluetorch.db.service.IDBService;
import iaf.bluetorch.db.service.MongodbGenericPersistence;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Logger.class).toProvider(LoggerProvider.class).in(Singleton.class);
		bind(Configuration.class).toProvider(ConfigurationProvider.class).in(Singleton.class);
		bind(IMongoDB.class).toProvider(MongoProvider.class).in(Singleton.class);
		bind(IDBService.class).to(MongodbGenericPersistence.class);
	}

}
