package iaf.bluetorch.db.entities;

public class TrackEntity extends BasicEntity {
	
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
	public String getJsonString() {
		return "{" + "trackId: " + this.trackId + "," + "val: " + this.val + "}";
	}

	@Override
	public String getCollectionName() {
		return "TrackEntity";
	}
	
}
