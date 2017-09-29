package iaf.bluetorch.trackstore;

import iaf.bluetorch.db.entities.TrackEntity;

public interface ITrackStore extends Iterable<TrackEntity>, Cloneable {
	public void add(TrackEntity entity);
	public void clear();
	public Object clone() throws CloneNotSupportedException;
}
