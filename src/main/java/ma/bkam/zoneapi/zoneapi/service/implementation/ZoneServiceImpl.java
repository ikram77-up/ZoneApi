package ma.bkam.zoneapi.zoneapi.service.implementation;

import ma.bkam.zoneapi.zoneapi.common.dto.ZoneDTO;
import ma.bkam.zoneapi.zoneapi.common.utils.MessagesCodes;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.dao.model.ZoneEntity;
import ma.bkam.zoneapi.zoneapi.dao.repository.ZoneRepository;
import ma.bkam.zoneapi.zoneapi.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository repository;

    @Override
    public ZoneDTO add(ZoneDTO zone) {
        if(zone != null && zone.getVilles()!=null){
            return   repository.save(zone.convertToEntity()).convertToDto();
        }
        throw  Utilities.raiseError(MessagesCodes.BODY_REQUIRED);
    }

    @Override
    public List<ZoneDTO> getAll() {

        List<ZoneDTO> zones =new ArrayList<>();

       repository.findAll().forEach(villeEntity ->
            zones.add(villeEntity.convertToDto())
        );
        return zones;
    }

    @Override
    public ZoneDTO update(ZoneDTO zone) {
        if(zone!=null && zone.getVilles()!=null) {
            long id = zone.getId();
            Optional<ZoneEntity> theExisted = repository.findById(id);
            if(theExisted.isPresent()) {
                theExisted.get().setId(zone.getId());
                theExisted.get().setLibelle(zone.getLibelle());
                theExisted.get().setAbrev(zone.getAbrev());
                theExisted.get().setVilles(Utilities.isNullOrEmpty(zone.getVilles())?null :zone.convertToEntity().getVilles());
                return repository.save(zone.convertToEntity()).convertToDto();
            }
            throw Utilities.raiseError(MessagesCodes.NOT_FOUND);

        }
        throw Utilities.raiseError(MessagesCodes.BODY_REQUIRED);
    }

    @Override
    public Boolean deleteById(Optional<Long> id) {
        Optional<ZoneEntity> theExisted;
        if (id.isPresent()) {
            theExisted = repository.findById(id.get());
        }
        else{
            throw Utilities.raiseError(MessagesCodes.ID_ERROR);
        }
        if(theExisted.isPresent()){
            repository.deleteById(theExisted.get().getId());
            return true;
        }else{

            throw Utilities.raiseError(MessagesCodes.NOT_FOUND);
        }

    }

    @Override
    public Optional<ZoneDTO> getByID(long id) {
        Optional<ZoneEntity> theExisted=repository.findById(id);

        if(theExisted.isPresent()){
            return theExisted.map(ZoneEntity::convertToDto);
        }
        throw Utilities.raiseError(MessagesCodes.NOT_FOUND);

    }
}
