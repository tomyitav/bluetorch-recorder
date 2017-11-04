package iaf.bluetorch.db.service;

import iaf.bluetorch.db.entities.BasicEntity;

public interface IDBService {
	
	public <E extends BasicEntity> void persist(E entity);
	
	public <E extends BasicEntity> void persist(Iterable<E> entity);
}
