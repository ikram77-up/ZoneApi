package ma.bkam.zoneapi.zoneapi.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.bkam.zoneapi.zoneapi.common.config.swagger.SwaggerDocConst;
import ma.bkam.zoneapi.zoneapi.common.dto.NaturePrestationDTO;
import ma.bkam.zoneapi.zoneapi.common.dto.SimpleMessageResponseModel;
import ma.bkam.zoneapi.zoneapi.common.utils.MessagesCodes;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.service.NaturePrestationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Api(tags = { SwaggerDocConst.NATURE_PRESTATION_CONTROLLER_TAG})
@RequestMapping("/naturePrestation")
public class NaturePrestationController {


    public final NaturePrestationService service;


    public NaturePrestationController(NaturePrestationService service) {
        this.service = service;
    }


    @ApiOperation(SwaggerDocConst.CREATE)
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NaturePrestationDTO> create(
            @RequestBody NaturePrestationDTO prestation) {
      NaturePrestationDTO pres=  service.add(prestation);
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
    public ResponseEntity<Optional<NaturePrestationDTO>> getByID(
            @PathVariable long id) {
        Optional<NaturePrestationDTO> result = service.getByID(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @ApiOperation(SwaggerDocConst.LIST)
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NaturePrestationDTO>> getAll() {

        List<NaturePrestationDTO> response = service.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


    @ApiOperation(SwaggerDocConst.UPDATE)
    @PutMapping(
            value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NaturePrestationDTO> update(
            @RequestBody NaturePrestationDTO prestation) {


        if (Objects.isNull(prestation.getId())) {
            throw Utilities.raiseError(MessagesCodes.ID_REQUIRED);
        }

        NaturePrestationDTO newDto = service.update(prestation);


        return ResponseEntity.status(HttpStatus.OK)
                .body(newDto);
    }



}
