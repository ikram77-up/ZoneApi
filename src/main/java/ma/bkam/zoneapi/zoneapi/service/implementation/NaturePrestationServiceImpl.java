package ma.bkam.zoneapi.zoneapi.service.implementation;

import ma.bkam.zoneapi.zoneapi.common.dto.NaturePrestationDTO;
import ma.bkam.zoneapi.zoneapi.common.utils.MessagesCodes;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.dao.model.NaturePrestationEntity;
import ma.bkam.zoneapi.zoneapi.dao.repository.NaturePrestationRepository;
import ma.bkam.zoneapi.zoneapi.service.NaturePrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class NaturePrestationServiceImpl implements NaturePrestationService {

    @Autowired
    private NaturePrestationRepository repository;

    @Override
    public NaturePrestationDTO add(NaturePrestationDTO prestation) {
        if(prestation!= null){
            return   repository.save(prestation.convertToEntity()).convertToDto();
        }
        throw  Utilities.raiseError(MessagesCodes.BODY_REQUIRED);
    }



    @Override
    public List<NaturePrestationDTO> getAll() {
        List<NaturePrestationDTO> prestation=new ArrayList<>();

        repository.findAll().forEach(villeEntity ->
            prestation.add(villeEntity.convertToDto())
        );
        return prestation;
    }




    @Override
    public NaturePrestationDTO update(NaturePrestationDTO prestation) {
        if(prestation !=null) {
            long id = prestation.getId();
            Optional<NaturePrestationEntity> theExisted = repository.findById(id);
            if(theExisted.isPresent()) {
                theExisted.get().setId(prestation.getId());
                theExisted.get().setLibelle(prestation.getLibelle());

                return repository.save(prestation.convertToEntity()).convertToDto();
            }
            throw Utilities.raiseError(MessagesCodes.NOT_FOUND);

        }
        throw Utilities.raiseError(MessagesCodes.BODY_REQUIRED);
    }

    @Override
    public Boolean deleteById(Optional<Long> id) {
        Optional<NaturePrestationEntity> theExisted;
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
    public Optional<NaturePrestationDTO> getByID(long id) {
        Optional<NaturePrestationEntity> theExisted=repository.findById(id);

        if(theExisted.isPresent()){
            return theExisted.map(NaturePrestationEntity::convertToDto);
        }
        throw Utilities.raiseError(MessagesCodes.NOT_FOUND);

    }
    }

