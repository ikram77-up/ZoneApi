package ma.bkam.zoneapi.zoneapi.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.bkam.zoneapi.zoneapi.common.config.swagger.SwaggerDocConst;
import ma.bkam.zoneapi.zoneapi.common.dto.PrestatireDTO;
import ma.bkam.zoneapi.zoneapi.common.dto.SimpleMessageResponseModel;
import ma.bkam.zoneapi.zoneapi.common.utils.MessagesCodes;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.service.PrestatireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Api(tags = { SwaggerDocConst.PRESTATIRE_CONTROLLER_TAG})
@RequestMapping("/prestataire")
public class PrestataireController {

    public final PrestatireService service;




    public PrestataireController(PrestatireService service) {
        this.service = service;
    }


    @ApiOperation(SwaggerDocConst.CREATE)
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrestatireDTO> create(
            @RequestBody PrestatireDTO prestatire) {
        PrestatireDTO pres=  service.add(prestatire);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pres);
    }


    @ApiOperation(SwaggerDocConst.DELETE)
    @DeleteMapping(value ="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleMessageResponseModel> delete(
            @PathVariable String id) {

        boolean deleted = service.deleteById(Optional.of(Long.valueOf(id)));

        SimpleMessageResponseModel deletedsubthemeMsg = new SimpleMessageResponseModel();

        if (deleted) {
            deletedsubthemeMsg.setMessage(MessagesCodes.DELETED_MESSAGE);
        }


        return ResponseEntity.status(HttpStatus.OK)
                .body(deletedsubthemeMsg);
    }

    @ApiOperation(SwaggerDocConst.GET)
    @GetMapping(value ="/getbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PrestatireDTO>> getByID(
            @PathVariable long id) {
        Optional<PrestatireDTO> result = service.getByID(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @ApiOperation(SwaggerDocConst.LIST)
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PrestatireDTO>> getAll() {

        List<PrestatireDTO> response = service.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


    @ApiOperation(SwaggerDocConst.UPDATE)
    @PutMapping(
            value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrestatireDTO> update(
            @RequestBody PrestatireDTO prestataire) {


        if (Objects.isNull(prestataire.getId())) {
            throw Utilities.raiseError(MessagesCodes.ID_REQUIRED);
        }

        PrestatireDTO newDto = service.update(prestataire);


        return ResponseEntity.status(HttpStatus.OK)
                .body(newDto);
    }
}
