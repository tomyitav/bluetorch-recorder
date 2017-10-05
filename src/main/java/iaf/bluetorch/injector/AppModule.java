package iaf.bluetorch.injector;

import iaf.bluetorch.core.IRecorderConfig;
import iaf.bluetorch.core.RecorderConfig;
import iaf.bluetorch.db.config.IMongoDB;
import iaf.bluetorch.db.service.IDBService;
import iaf.bluetorch.db.service.MongodbGenericPersistence;
import iaf.bluetorch.entitystore.EntityStore;
import iaf.bluetorch.entitystore.IEntityStore;

import org.apache.commons.configuration2.Configuration;
import org.apache.logging.log4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Logger.class).toProvider(LoggerProvider.class).in(Singleton.class);
		bind(Configuration.class).toProvider(ConfigurationProvider.class).in(Singleton.class);
		bind(IMongoDB.class).toProvider(MongoProvider.class).in(Singleton.class);
		bind(IDBService.class).to(MongodbGenericPersistence.class);
		bind(IEntityStore.class).to(EntityStore.class);
		bind(IRecorderConfig.class).to(RecorderConfig.class);
	}

}
