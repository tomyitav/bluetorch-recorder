package iaf.bluetorch.injector;

import org.apache.logging.log4j.Logger;

import iaf.bluetorch.db.service.IDBService;
import iaf.bluetorch.db.service.MongodbGenericPersistence;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDBService.class).to(MongodbGenericPersistence.class);
		bind(Logger.class).toProvider(LoggerProvider.class).in(Singleton.class);
	}

}
