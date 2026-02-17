package app.util;

import java.util.List;

public final class ValidationUtil {

    // Attributes

    // __________________________________________________________

    private ValidationUtil() {
        // Non instantiable
    }

    // __________________________________________________________

    public static <T> T requireFirst(List<T> list, RuntimeException exception) {
        if (list == null || list.isEmpty() || list.get(0) == null) {
            throw exception;
        }
        return list.get(0);
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