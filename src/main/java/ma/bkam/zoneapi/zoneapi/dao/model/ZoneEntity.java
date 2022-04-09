package ma.bkam.zoneapi.zoneapi.dao.model;

import ma.bkam.zoneapi.zoneapi.common.dto.ZoneDTO;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "ZONE")
public class ZoneEntity extends GenericEntity{

    @NonNull
    private String abrev;
    @NonNull
    private String libelle;


    private List<VilleEntity> villes;


    @OneToMany(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "idZone", referencedColumnName = "id")
    public List<VilleEntity> getVilles() {
        return villes;
    }


    public void setVilles(List<VilleEntity> villes) {
        this.villes = villes;
    }

    @Id
    @SequenceGenerator(
            name = "ZONE_GENERATOR",
            sequenceName = "ZONE_SEQ",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ZONE_GENERATOR")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
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
    public ZoneDTO convertToDto() {
        ZoneDTO zone=new ZoneDTO();
       zone.setId(this.id);
       zone.setAbrev(this.abrev);
       zone.setLibelle(this.libelle);
        zone.setVilles(Utilities.isNullOrEmpty(this.villes)?
                new ArrayList<>() : this.villes.stream()
                .map(VilleEntity::convertToDto).collect(Collectors.toList()));
        return zone;
    }



}
