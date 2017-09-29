package iaf.bluetorch.trackstore;

import iaf.bluetorch.db.entities.TrackEntity;

import java.util.HashMap;
import java.util.Iterator;

public class TrackStore implements ITrackStore {
	
	private HashMap<Integer, TrackEntity> trackMap;
	
	public TrackStore() {
		trackMap = new HashMap<>();
	}
	
	private TrackStore(HashMap<Integer, TrackEntity> trackMap) {
		this.trackMap = trackMap;
	}

	@Override
	public Iterator<TrackEntity> iterator() {
		return trackMap.values().iterator();
	}

	@Override
	public void add(TrackEntity entity) {
		trackMap.put(entity.getTrackId(), entity);
	}

	@Override
	public void clear() {
		trackMap.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new TrackStore((HashMap<Integer, TrackEntity>) trackMap.clone());
	}
	
}
