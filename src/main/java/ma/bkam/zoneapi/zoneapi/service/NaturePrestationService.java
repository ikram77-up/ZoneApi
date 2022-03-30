package ma.bkam.zoneapi.zoneapi.service;

import ma.bkam.zoneapi.zoneapi.common.dto.NaturePrestationDTO;

import java.util.List;
import java.util.Optional;

public interface NaturePrestationService {



    NaturePrestationDTO add(NaturePrestationDTO prestation);

    List<NaturePrestationDTO> getAll();


    NaturePrestationDTO update(NaturePrestationDTO prestation);


    Boolean deleteById(Optional<Long> id);


    Optional<NaturePrestationDTO> getByID(long id);
}
