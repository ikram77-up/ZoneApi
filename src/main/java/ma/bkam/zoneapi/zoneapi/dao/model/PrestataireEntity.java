package ma.bkam.zoneapi.zoneapi.dao.model;


import ma.bkam.zoneapi.zoneapi.common.dto.PrestatireDTO;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "PRESTATIRE")
public class PrestataireEntity extends GenericEntity{

    @Id
    @SequenceGenerator(
            name = "PRESTATIRE_GENERATOR",
            sequenceName = "PRESTATIRE_SEQ",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRESTATIRE_GENERATOR")
    @Override
    public Long getId() {
        return super.getId();
    }


@NonNull
private String libelle;

    private NaturePrestationEntity prestationEntity;

    @OneToOne
    @JoinColumn(name = "idPrestation", referencedColumnName = "id")
    public NaturePrestationEntity getPrestationEntity() {
        return prestationEntity;
    }

    public void setPrestationEntity(NaturePrestationEntity prestationEntity) {
        this.prestationEntity = prestationEntity;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public PrestatireDTO convertToDto() {
        PrestatireDTO prestataire=new PrestatireDTO();
        prestataire.setId(this.id);
        prestataire.setLibelle(this.libelle);
        prestataire.setPrestationDTO(Objects.nonNull(this.prestationEntity)? prestationEntity.convertToDto() : null);
        return prestataire;
    }
}
