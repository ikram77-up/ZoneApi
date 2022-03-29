package ma.bkam.zoneapi.zoneapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.bkam.zoneapi.zoneapi.common.config.swagger.SwaggerDocConst;
import ma.bkam.zoneapi.zoneapi.common.dto.SimpleMessageResponseModel;
import ma.bkam.zoneapi.zoneapi.common.dto.VilleDTO;
import ma.bkam.zoneapi.zoneapi.common.utils.MessagesCodes;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.service.VilleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Api(tags = { SwaggerDocConst.VILLE_CONTROLLER_TAG})
@RequestMapping("/ville")
public class VilleController {


    public final VilleService service;


    public VilleController(VilleService service) {
        this.service = service;
    }


    @ApiOperation(SwaggerDocConst.CREATE)
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VilleDTO> create(
            @RequestBody VilleDTO ville) {
        service.add(ville);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ville);
    }


    @ApiOperation(SwaggerDocConst.DELETE)
    @DeleteMapping(value ="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleMessageResponseModel> delete(
            @PathVariable String id) {

        boolean deleted = service.deleteById(Optional.of(Long.valueOf(id)));

        var deletedsubthemeMsg = new SimpleMessageResponseModel();

        if (deleted) {
            deletedsubthemeMsg.setMessage(MessagesCodes.DELETED_VILLE_MESSAGE);
        }


        return ResponseEntity.status(HttpStatus.OK)
                .body(deletedsubthemeMsg);
    }

    @ApiOperation(SwaggerDocConst.GET)
    @GetMapping(value ="/getbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Optional<VilleDTO>> getByID(
            @PathVariable long id) {
        var result = service.getByID(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @ApiOperation(SwaggerDocConst.LIST)
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VilleDTO>> getAll() {

        var response = service.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


    @ApiOperation(SwaggerDocConst.UPDATE)
    @PutMapping(
            value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VilleDTO> update(
            @RequestBody VilleDTO villeDTO) {


        if (Objects.isNull(villeDTO.getId())) {
            throw Utilities.raiseError(MessagesCodes.ID_REQUIRED);
        }

        var newDto = service.update(villeDTO);


        return ResponseEntity.status(HttpStatus.OK)
                .body(newDto);
    }

}
