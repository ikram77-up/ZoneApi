package ma.bkam.zoneapi.zoneapi.dao.model;

import ma.bkam.zoneapi.zoneapi.common.dto.GenericDTO;
import org.springframework.lang.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericEntity {


    /**
     * The id of category
     */
    Long id;

    /**
     * All entities that implement this generic entity should define a convertDto Method
     * returning the target AbstractCategoryDto.
     * @return T The target DTO
     */
    public abstract  <T extends GenericDTO> T convertToDto();

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
