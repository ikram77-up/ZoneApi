package ma.bkam.zoneapi.zoneapi.dao.model;

import com.sun.istack.NotNull;
import ma.bkam.zoneapi.zoneapi.common.dto.NaturePrestationDTO;

import javax.persistence.*;

@Entity(name = "PRESTATION")
public class NaturePrestationEntity extends GenericEntity{

    @NotNull
    private String libelle;

    @Id
    @SequenceGenerator(
            name = "PRESTATION_GENERATOR",
            sequenceName = "PRESTATION_SEQ",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRESTATION_GENERATOR")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public NaturePrestationDTO convertToDto() {
        NaturePrestationDTO prestationDTO=new NaturePrestationDTO();
        prestationDTO.setId(this.id);
        prestationDTO.setLibelle(this.libelle);
        return  prestationDTO;
    }
}
