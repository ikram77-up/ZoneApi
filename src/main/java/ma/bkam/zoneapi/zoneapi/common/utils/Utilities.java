package ma.bkam.zoneapi.zoneapi.common.utils;

import ma.bkam.zoneapi.zoneapi.common.exceptions.InvalidInputsException;

import java.util.List;
import java.util.Set;

public class Utilities {
    private Utilities() {}


    public static boolean isNullOrEmpty(String input) {
        return !(input != null && !input.trim().isEmpty());
    }


    public static boolean isNullOrEmpty(List<?> list) {
        return !(list != null && !list.isEmpty());
    }


    public static boolean isNullOrEmpty(Set<?> list) {
        return !(list != null && !list.isEmpty());
    }


    public static boolean isOverflow(String input, int maxLength) {
        return !(!isNullOrEmpty(input) && input.length() <= maxLength);
    }

    public static InvalidInputsException raiseError(String message) {
        return new InvalidInputsException(message);
    }



}
