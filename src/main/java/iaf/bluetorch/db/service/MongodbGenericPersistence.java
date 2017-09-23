package iaf.bluetorch.db.service;
import iaf.bluetorch.db.config.IMongoDB;
import iaf.bluetorch.db.entities.BasicEntity;

import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The generic Persistence implementation, showing how to do persists, count entities, return a
 * specific one, and generate unique IDs.
 */
@Singleton
public class MongodbGenericPersistence implements IDBService {

	@Inject private Logger logger;
	@Inject private IMongoDB mongoConn;

	public <E extends BasicEntity> ObjectId persist(E entity) {
		logger.debug("Saving entity to db...");
		getDatastore().save(entity);
		return entity.getId();
	}

	public <E extends BasicEntity> long count(Class<E> clazz) {
		if (clazz == null) {
			return 0;
		}

		return getDatastore().find(clazz).count();
	}

	public <E extends BasicEntity> E get(Class<E> clazz, final ObjectId id) {
		if ((clazz == null) || (id == null)) {
			return null;
		}

		return getDatastore().find(clazz).field("id").equal(id).get();
	}

	private Datastore getDatastore() {
		return mongoConn.getDatabase();
	}

}