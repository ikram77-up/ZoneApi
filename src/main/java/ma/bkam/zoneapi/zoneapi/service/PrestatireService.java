package ma.bkam.zoneapi.zoneapi.service;

import ma.bkam.zoneapi.zoneapi.common.dto.PrestatireDTO;

import java.util.List;
import java.util.Optional;

public interface PrestatireService {

    PrestatireDTO add(PrestatireDTO prestatire);

    List<PrestatireDTO> getAll();


    PrestatireDTO update(PrestatireDTO prestatire);


    Boolean deleteById(Optional<Long> id);


    Optional<PrestatireDTO> getByID(long id);
}
