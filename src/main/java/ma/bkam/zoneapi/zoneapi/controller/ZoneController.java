package ma.bkam.zoneapi.zoneapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.bkam.zoneapi.zoneapi.common.config.swagger.SwaggerDocConst;
import ma.bkam.zoneapi.zoneapi.common.dto.SimpleMessageResponseModel;
import ma.bkam.zoneapi.zoneapi.common.dto.ZoneDTO;
import ma.bkam.zoneapi.zoneapi.common.utils.MessagesCodes;
import ma.bkam.zoneapi.zoneapi.common.utils.Utilities;
import ma.bkam.zoneapi.zoneapi.service.ZoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Api(tags = { SwaggerDocConst.ZONE_CONTROLLER_TAG})
@RequestMapping("/zone")
public class ZoneController {

    public final ZoneService service;


    public ZoneController(ZoneService service) {
        this.service = service;
    }


    @ApiOperation(SwaggerDocConst.CREATE)
    @PostMapping(
            value = "/add")
    public ResponseEntity<ZoneDTO> create(
            @RequestBody ZoneDTO zone) {
        var x = service.add(zone);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(zone);
    }


    @ApiOperation(SwaggerDocConst.DELETE)
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping(value = "/getbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Optional<ZoneDTO>> getByID(
            @PathVariable long id) {
        var result = service.getByID(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @ApiOperation(SwaggerDocConst.LIST)
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ZoneDTO>> getAll() {

        var response = service.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


    @ApiOperation(SwaggerDocConst.UPDATE)
    @PutMapping(
            value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZoneDTO> update(
            @RequestBody ZoneDTO zoneDto) {


        if (Objects.isNull(zoneDto.getId())) {
            throw Utilities.raiseError(MessagesCodes.ID_REQUIRED);
        }

        var newDto = service.update(zoneDto);


        return ResponseEntity.status(HttpStatus.OK)
                .body(newDto);
    }
}
