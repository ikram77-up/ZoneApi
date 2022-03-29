package ma.bkam.zoneapi.zoneapi.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputsException extends BaseZoneApiException {



    public InvalidInputsException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);

    }
}
