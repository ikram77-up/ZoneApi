package ma.bkam.zoneapi.zoneapi.common.dto;

import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.dao.model.VilleEntity;


public class VilleDTO extends GenericDTO{



    private String abrev;
    private String libelle;


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
    public VilleEntity convertToEntity() {
        var ville=new VilleEntity();
        ville.setId(this.id);
        ville.setAbrev(this.abrev);
        ville.setLibelle(this.libelle);
        return ville;
    }
}
