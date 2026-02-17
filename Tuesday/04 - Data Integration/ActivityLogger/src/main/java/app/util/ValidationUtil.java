package app.util;

import app.exception.ResourceNotFoundException;
import java.util.List;

public final class ValidationUtil {

    // Attributes

    // __________________________________________________________

    private ValidationUtil() {
        // Non instantiable
    }

    // __________________________________________________________

    public static <T> T getFirstOrFail(String wrapperName, String listName, List<T> results, String identifier) {

        // First result
        T first = results.get(0);

        if (results == null) {
            throw new ResourceNotFoundException(
                    listName + " (from " + wrapperName + ".results is null)", identifier
            );
        }

        if (results.isEmpty()) {
            throw new ResourceNotFoundException(
                    listName + " (empty results in " + wrapperName + ")", identifier
            );
        }

        if (first == null) {
            throw new ResourceNotFoundException(
                    listName + " (first element is null in " + wrapperName + ".results)", identifier
            );
        }

        return first;

    }

    // __________________________________________________________

    public static void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }

        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

    // __________________________________________________________

    public static <T> void validateNotEmptyList(List<T> list, String listName) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(listName + " må ikke være tom eller null");
        }
    }

}