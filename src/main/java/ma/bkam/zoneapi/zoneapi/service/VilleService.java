package ma.bkam.zoneapi.zoneapi.service;

import ma.bkam.zoneapi.zoneapi.common.dto.VilleDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface VilleService {


    VilleDTO add(VilleDTO ville);

    List<VilleDTO> getAll();


    VilleDTO update(VilleDTO ville);


    Boolean deleteById(Optional<Long> id);


    Optional<VilleDTO> getByID(long id);


}
