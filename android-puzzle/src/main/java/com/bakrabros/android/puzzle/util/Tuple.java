package com.bakrabros.android.puzzle.util;

/**
 * @author BakraBros
 */
public class Tuple<T1, T2> {

    public T1 _1;

    public T2 _2;

    /**
     * Constructs a tuple of two elements.
     *
     * @param t1 the 1st element
     * @param t2 the 2nd element
     */
    public Tuple(T1 t1, T2 t2) {
        this._1 = t1;
        this._2 = t2;
    }

    public static <T1, T2> Tuple<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple<>(t1, t2);
    }
}
