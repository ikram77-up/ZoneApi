package ma.bkam.zoneapi.zoneapi.service.implementation;


import ma.bkam.zoneapi.zoneapi.common.dto.PrestatireDTO;
import ma.bkam.zoneapi.zoneapi.common.utils.MessagesCodes;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.dao.model.NaturePrestationEntity;
import ma.bkam.zoneapi.zoneapi.dao.model.PrestataireEntity;
import ma.bkam.zoneapi.zoneapi.dao.repository.PrestataireRepository;
import ma.bkam.zoneapi.zoneapi.service.PrestatireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PrestatireServiceImpl   implements PrestatireService {

    @Autowired
    private PrestataireRepository repository;

    @Override
    public PrestatireDTO add(PrestatireDTO prestatire) {
        if(prestatire!= null && prestatire.getPrestationDTO()!=null){
            return   repository.save(prestatire.convertToEntity()).convertToDto();
        }
        throw  Utilities.raiseError(MessagesCodes.BODY_REQUIRED);
    }

    @Override
    public List<PrestatireDTO> getAll() {
        List<PrestatireDTO> prestatire=new ArrayList<>();

        repository.findAll().forEach(villeEntity ->
            prestatire.add(villeEntity.convertToDto())
        );
        return prestatire;
    }

    @Override
    public PrestatireDTO update(PrestatireDTO prestatire) {
        if(prestatire !=null) {
            long id = prestatire.getId();
            Optional<PrestataireEntity> theExisted = repository.findById(id);
            if(theExisted.isPresent()) {
                theExisted.get().setId(prestatire.getId());
                theExisted.get().setLibelle(prestatire.getLibelle());
                theExisted.get().setPrestationEntity(Objects.nonNull(prestatire.getPrestationDTO())? prestatire.getPrestationDTO().convertToEntity():new NaturePrestationEntity());
                return repository.save(prestatire.convertToEntity()).convertToDto();
            }
            throw Utilities.raiseError(MessagesCodes.NOT_FOUND);

        }
        throw Utilities.raiseError(MessagesCodes.BODY_REQUIRED);
    }

    @Override
    public Boolean deleteById(Optional<Long> id) {
        Optional<PrestataireEntity> theExisted;
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
    public Optional<PrestatireDTO> getByID(long id) {
        Optional<PrestataireEntity> theExisted=repository.findById(id);

        if(theExisted.isPresent()){
            return theExisted.map(PrestataireEntity::convertToDto);
        }
        throw Utilities.raiseError(MessagesCodes.NOT_FOUND);

    }
    }

