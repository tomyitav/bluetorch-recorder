package iaf.bluetorch.db.entities;
import org.bson.Document;

/**
 * Provide the BaseEntity implementation for all entities:
 *
 * ID, creation and last change date, version, their getters and setters (including @PrePersist),
 * and some abstract methods we'll require in the specific entities.
 */
public abstract class BasicEntity {

  /**
   * We'll only provide getters for these attributes, setting is done in @PrePersist.
   */

  /**
   * No getters and setters required, the version is handled internally.
   */

  public BasicEntity() {
    super();
  }

  
  public Document asDBObject() {
	  return Document.parse(this.getJsonString());
  }

  public abstract String getJsonString();
  public abstract String getCollectionName();
  public abstract Integer getEntityId();

}