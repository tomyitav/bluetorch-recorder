package iaf.bluetorch.injector;

import iaf.bluetorch.db.service.IDBService;
import iaf.bluetorch.db.service.MongodbGenericPersistence;

import com.google.inject.AbstractModule;

public class DBModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IDBService.class).to(MongodbGenericPersistence.class);
	}

}
