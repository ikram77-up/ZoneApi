package ma.bkam.zoneapi.zoneapi.common.dto;

import ma.bkam.zoneapi.zoneapi.dao.model.PrestataireEntity;

import java.util.Objects;

public class PrestatireDTO extends GenericDTO {

    private NaturePrestationDTO prestationDTO;

    public NaturePrestationDTO getPrestationDTO() {
        return prestationDTO;
    }

    public void setPrestationDTO(NaturePrestationDTO prestationDTO) {
        this.prestationDTO = prestationDTO;
    }

    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public PrestataireEntity convertToEntity() {
        PrestataireEntity prestataire=new PrestataireEntity();
        prestataire.setId(this.id);
        prestataire.setLibelle(this.libelle);
        prestataire.setPrestationEntity(Objects.nonNull(this.prestationDTO)? this.prestationDTO.convertToEntity(): null);
        return prestataire;
    }
}
