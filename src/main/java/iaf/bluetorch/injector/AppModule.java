package iaf.bluetorch.injector;

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
		bind(IMongoDB.class).to(MongoDB.class);
		bind(IDBService.class).to(MongodbGenericPersistence.class);
	}

}
