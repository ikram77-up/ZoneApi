package ma.bkam.zoneapi.zoneapi.common.dto;


import ma.bkam.zoneapi.zoneapi.dao.model.GenericEntity;

public abstract class GenericDTO {

    /**
     * The id of category
     */
    Long id;

    /**
     * All entities that implement this generic DTO should define a convertToEntity Method
     * returning the target entity.
     * @return T The target entity.
     */
    public abstract  <T extends GenericEntity>  T convertToEntity();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
