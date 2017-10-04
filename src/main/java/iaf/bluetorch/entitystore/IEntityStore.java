package iaf.bluetorch.entitystore;

import iaf.bluetorch.db.entities.BasicEntity;

public interface IEntityStore extends Iterable<BasicEntity>, Cloneable {
	public void add(BasicEntity entity);
	public void clear();
	public Object clone() throws CloneNotSupportedException;
}
