package iaf.bluetorch.db.service;
import iaf.bluetorch.db.config.MongoDB;
import iaf.bluetorch.db.entities.BasicEntity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

/**
 * The generic Persistence implementation, showing how to do persists, count entities, return a
 * specific one, and generate unique IDs.
 */
public class MongodbGenericPersistence implements IDBService {

  private final Datastore mongoDatastore;

  public MongodbGenericPersistence() {
    mongoDatastore = MongoDB.instance().getDatabase();
  }

  public <E extends BasicEntity> ObjectId persist(E entity) {
    mongoDatastore.save(entity);
    return entity.getId();
  }

  public <E extends BasicEntity> long count(Class<E> clazz) {
    if (clazz == null) {
      return 0;
    }

    return mongoDatastore.find(clazz).count();
  }

  public <E extends BasicEntity> E get(Class<E> clazz, final ObjectId id) {
    if ((clazz == null) || (id == null)) {
      return null;
    }

    return mongoDatastore.find(clazz).field("id").equal(id).get();
  }

}