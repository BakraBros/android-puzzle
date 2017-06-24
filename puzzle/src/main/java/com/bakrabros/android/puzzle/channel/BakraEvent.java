package com.bakrabros.android.puzzle.channel;

/**
 * @author BakraBros
 */
public class BakraEvent<I, O> {

    private String name;

    public BakraEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
