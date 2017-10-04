package iaf.bluetorch.db.entities;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

/**
 * Provide the BaseEntity implementation for all entities:
 *
 * ID, creation and last change date, version, their getters and setters (including @PrePersist),
 * and some abstract methods we'll require in the specific entities.
 */
public abstract class BasicEntity {

  @Id
  protected ObjectId id;

  /**
   * We'll only provide getters for these attributes, setting is done in @PrePersist.
   */
  @Indexed(options = @IndexOptions(name = "try", expireAfterSeconds = 0))
  protected Date creationDate;
  protected Date lastChange;

  /**
   * No getters and setters required, the version is handled internally.
   */
  @Version
  private long version;

  public BasicEntity() {
    super();
  }

  public ObjectId getId() {
    return id;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public Date getLastChange() {
    return lastChange;
  }

  @PrePersist
  public void prePersist() {
    this.creationDate = (creationDate == null) ? new Date() : creationDate;
    this.lastChange = (lastChange == null) ? creationDate : new Date();
  }

  public abstract String toString();

}