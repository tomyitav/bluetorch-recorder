package iaf.bluetorch.db.service;
import iaf.bluetorch.db.config.IMongoDB;
import iaf.bluetorch.db.entities.BasicEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * The generic Persistence implementation, showing how to do persists, count entities, return a
 * specific one, and generate unique IDs.
 */
@Singleton
public class MongodbGenericPersistence implements IDBService {

	private Logger logger;
	private IMongoDB mongoConn;
	
	@Inject
	public MongodbGenericPersistence(Logger logger, IMongoDB mongoConn) {
		this.logger = logger;
		this.mongoConn = mongoConn;
	}

	public <E extends BasicEntity> ObjectId persist(E entity) {
		logger.debug("Saving entity to db...");
		MongoCollection<Document> c = this.getDBConn().getCollection(entity.getCollectionName());
		Document document = entity.asDBObject();
		c.insertOne(document);
//		getDBConn().save(entity);
		return document.getObjectId(document);
	}
	
	public <E extends BasicEntity> Iterable<E> persist(Iterable<E> entities) {
		logger.debug("Saving entity collection to db...");
		ArrayList<E> entitiesAsList = Lists.newArrayList(entities);
		String collectionName = entitiesAsList.get(0).getCollectionName();
		List<Document> documentsList = entitiesAsList.stream().map(e -> e.asDBObject()).collect(Collectors.toList());
		MongoCollection<Document> c = this.getDBConn().getCollection(collectionName);
		c.insertMany(documentsList);
		return null;
	}

	public <E extends BasicEntity> long count(Class<E> clazz) {
		if (clazz == null) {
			return 0;
		}

		return 1;
	}

	public <E extends BasicEntity> E get(Class<E> clazz, final ObjectId id) {
		if ((clazz == null) || (id == null)) {
			return null;
		}

		return null;
	}

	private MongoDatabase getDBConn() {
		return mongoConn.getDatabase();
	}

}