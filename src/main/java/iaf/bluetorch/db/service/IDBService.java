package iaf.bluetorch.db.service;

import org.bson.types.ObjectId;

import iaf.bluetorch.db.entities.BasicEntity;

public interface IDBService {
	
	public <E extends BasicEntity> ObjectId persist(E entity);
	
	public <E extends BasicEntity> Iterable<E> persist(Iterable<E> entity);

	public <E extends BasicEntity> E get(Class<E> clazz, final ObjectId id);
	
	public <E extends BasicEntity> long count(Class<E> clazz);
}
