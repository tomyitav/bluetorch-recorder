package iaf.bluetorch.db.entities;

public class TrackEntity extends BasicEntity {
	
	private Integer id;
	private Integer val;
	
	public TrackEntity(Integer id, Integer val) {
		this.id = id;
		this.val = val;
	}
	
	@Override
	public Integer getEntityId() {
		return id;
	}
	public Integer getVal() {
		return val;
	}
	
	@Override
	public String toString() {
		return "TrackEntity [id=" + id + ", val=" + val + "]";
	}
	
}
