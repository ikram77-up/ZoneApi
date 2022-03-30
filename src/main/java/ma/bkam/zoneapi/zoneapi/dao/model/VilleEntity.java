package ma.bkam.zoneapi.zoneapi.dao.model;

import ma.bkam.zoneapi.zoneapi.common.dto.VilleDTO;
import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity(name = "VILLE")
public class VilleEntity extends GenericEntity{


@NonNull
private String abrev;
    @NonNull
    private String libelle;

    @Id
    @SequenceGenerator(
            name = "VILLE_GENERATOR",
            sequenceName = "VILLE_SEQ",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VILLE_GENERATOR")
    @Override
    public Long getId() {
        return super.getId();
    }


    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public VilleDTO convertToDto() {
        VilleDTO ville=new VilleDTO();
        ville.setId(this.id);
        ville.setAbrev(this.abrev);
        ville.setLibelle(this.libelle);
        return ville;
    }
    }

