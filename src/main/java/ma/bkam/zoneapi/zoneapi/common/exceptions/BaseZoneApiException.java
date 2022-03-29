package ma.bkam.zoneapi.zoneapi.common.exceptions;

public class BaseZoneApiException extends RuntimeException {

    private final int status;
    private final String message;


    public BaseZoneApiException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
