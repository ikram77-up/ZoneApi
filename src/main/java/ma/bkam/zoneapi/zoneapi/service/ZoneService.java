package ma.bkam.zoneapi.zoneapi.service;

import ma.bkam.zoneapi.zoneapi.common.dto.VilleDTO;
import ma.bkam.zoneapi.zoneapi.common.dto.ZoneDTO;

import java.util.List;
import java.util.Optional;

public interface ZoneService {


    ZoneDTO add(ZoneDTO zone);

    List<ZoneDTO> getAll();


    ZoneDTO update(ZoneDTO zone);


    Boolean deleteById(Optional<Long> id);


    Optional<ZoneDTO> getByID(long id);


}
