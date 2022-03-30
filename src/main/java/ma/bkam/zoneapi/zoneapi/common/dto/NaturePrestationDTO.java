package ma.bkam.zoneapi.zoneapi.common.dto;

import ma.bkam.zoneapi.zoneapi.dao.model.NaturePrestationEntity;

public class NaturePrestationDTO extends GenericDTO {

    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public  NaturePrestationEntity convertToEntity() {
        NaturePrestationEntity prestationEntity=new NaturePrestationEntity();
        prestationEntity.setId(this.id);
        prestationEntity.setLibelle(this.libelle);
        return  prestationEntity;
    }
}
