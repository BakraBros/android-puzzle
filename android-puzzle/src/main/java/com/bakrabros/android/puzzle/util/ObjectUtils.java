package com.bakrabros.android.puzzle.util;

/**
 * @author BakraBros
 */
public class ObjectUtils {

    public static <T> T requireNonNull(T object) {
        if (object == null) {
            throw new NullPointerException();
        }

        return object;
    }

    public static <T> T requireNonNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }

        return object;
    }
}
