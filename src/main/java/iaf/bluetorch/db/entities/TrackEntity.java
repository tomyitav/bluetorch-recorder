package iaf.bluetorch.db.entities;

import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

public class TrackEntity extends BasicEntity {
	
	@Indexed(options = @IndexOptions(name = "idInd"))
	private Integer trackId;
	private Integer val;
	
	public TrackEntity(Integer id, Integer val) {
		this.trackId = id;
		this.val = val;
	}
	
	@Override
	public Integer getEntityId() {
		return trackId;
	}
	public Integer getVal() {
		return val;
	}
	
	@Override
	public String toString() {
		return "TrackEntity [id=" + trackId + ", val=" + val + "]";
	}
	
}
