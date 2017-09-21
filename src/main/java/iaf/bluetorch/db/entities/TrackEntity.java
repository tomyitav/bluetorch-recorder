package iaf.bluetorch.db.entities;

public class TrackEntity extends BasicEntity {
	
	private Integer randId;
	
	public TrackEntity(Integer randId) {
		super();
		this.randId = randId;
	}

	public Integer getRandId() {
		return randId;
	}

	public TrackEntity setRandId(Integer randId) {
		this.randId = randId;
		return this;
	}

	@Override
	public String toString() {
		return "TrackEntity [randId=" + randId + ", id=" + id
				+ ", creationDate=" + creationDate + ", lastChange="
				+ lastChange + "]";
	}

}
