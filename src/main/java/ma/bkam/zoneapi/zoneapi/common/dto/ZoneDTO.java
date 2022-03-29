package ma.bkam.zoneapi.zoneapi.common.dto;


import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.dao.model.ZoneEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZoneDTO extends GenericDTO {

    private String abrev;
    private String libelle;

    private List<VilleDTO> villes;

    public List<VilleDTO> getVilles() {
        return villes;
    }

    public void setVilles(List<VilleDTO> villes) {
        this.villes = villes;
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

    public ZoneEntity convertToEntity() {
        var zone= new ZoneEntity();
        zone.setId(this.id);
        zone.setAbrev(this.abrev);
        zone.setLibelle(this.libelle);
        zone.setVilles(Utilities.isNullOrEmpty(this.villes)? new ArrayList<>(): this.villes.stream().map(VilleDTO::convertToEntity).collect(Collectors.toList()));
        return zone;

    }



}