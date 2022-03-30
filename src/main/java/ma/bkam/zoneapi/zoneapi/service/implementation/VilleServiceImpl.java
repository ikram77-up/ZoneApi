package ma.bkam.zoneapi.zoneapi.service.implementation;

import ma.bkam.zoneapi.zoneapi.common.dto.VilleDTO;
import ma.bkam.zoneapi.zoneapi.common.utils.MessagesCodes;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.dao.model.VilleEntity;
import ma.bkam.zoneapi.zoneapi.dao.model.ZoneEntity;
import ma.bkam.zoneapi.zoneapi.dao.repository.VilleRepository;
import ma.bkam.zoneapi.zoneapi.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VilleServiceImpl implements VilleService {

    @Autowired
   private  VilleRepository villeRepository;

    @Override
    public VilleDTO add(VilleDTO ville) {
        if(ville!= null){
          return   villeRepository.save(ville.convertToEntity()).convertToDto();
        }
        throw  Utilities.raiseError(MessagesCodes.BODY_REQUIRED);
    }

    @Override
    public List<VilleDTO> getAll() {

        List<VilleDTO> villes=new ArrayList<VilleDTO>();

        villeRepository.findAll().forEach(villeEntity -> {
            villes.add(villeEntity.convertToDto());
        });
        return villes;
    }

    @Override
    public VilleDTO update(VilleDTO ville) {
        if(ville !=null) {
            long id = ville.getId();
            Optional<VilleEntity> theExisted = villeRepository.findById(id);
            if(theExisted.isPresent()) {
                theExisted.get().setId(ville.getId());
                theExisted.get().setLibelle(ville.getLibelle());
                theExisted.get().setAbrev(ville.getAbrev());
                return villeRepository.save(ville.convertToEntity()).convertToDto();
            }
            throw Utilities.raiseError(MessagesCodes.NOT_FOUND);

        }
        throw Utilities.raiseError(MessagesCodes.BODY_REQUIRED);
    }

    @Override
    public Boolean deleteById(Optional<Long> id) {
        Optional<VilleEntity> theExisted = Optional.empty();
        if (id.isPresent()) {
        theExisted = villeRepository.findById(id.get());
    }
        else{
            throw Utilities.raiseError(MessagesCodes.ID_ERROR);
        }
        if(theExisted.isPresent()){
            villeRepository.deleteById(theExisted.get().getId());
            return true;
        }else{

            throw Utilities.raiseError(MessagesCodes.NOT_FOUND);
        }

    }

    @Override
    public Optional<VilleDTO> getByID(long id) {
        Optional<VilleEntity> theExisted=villeRepository.findById(id);

        if(theExisted.isPresent()){
            return theExisted.map(VilleEntity::convertToDto);
        }
        throw Utilities.raiseError(MessagesCodes.NOT_FOUND);

    }
}
