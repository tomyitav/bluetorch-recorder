package iaf.bluetorch.entitystore;

import iaf.bluetorch.db.entities.BasicEntity;

import java.util.HashMap;
import java.util.Iterator;

public class EntityStore implements IEntityStore {
	
	private HashMap<Integer, BasicEntity> entityMap;
	
	public EntityStore() {
		entityMap = new HashMap<>();
	}
	
	private EntityStore(HashMap<Integer, BasicEntity> entityMap) {
		this.entityMap = entityMap;
	}

	@Override
	public Iterator<BasicEntity> iterator() {
		return entityMap.values().iterator();
	}

	@Override
	public void add(BasicEntity entity) {
		entityMap.put(entity.getEntityId(), entity);
	}

	@Override
	public void clear() {
		entityMap.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new EntityStore((HashMap<Integer, BasicEntity>) entityMap.clone());
	}
	
	public int size() {
		return entityMap.size();
	}
	
}
