package com.bakrabros.android.puzzle.channel;

import android.util.Log;

import com.bakrabros.android.puzzle.util.Tuple;
import com.bakrabros.android.puzzle.util.java8.Consumer;
import com.bakrabros.android.puzzle.util.java8.Function;
import com.bakrabros.android.puzzle.util.java8.Supplier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author BakraBros
 */
public class Channel {

    private final List<Tuple<BakraEvent, Function>> eventHandlers;

    public Channel() {
        this.eventHandlers = new ArrayList<>();
    }

    private static <I> Function<I, Object> consumerFunction(final Consumer<I> consumer) {
        return new Function<I, Object>() {
            @Override
            public Object apply(I t) {
                consumer.accept(t);
                return null;
            }

            @Override
            public String toString() {
                return consumer.toString();
            }
        };
    }

    private static <O> Function<Object, O> supplierFunction(final Supplier<O> supplier) {
        return new Function<Object, O>() {
            @Override
            public O apply(Object ignored) {
                return supplier.get();
            }

            @Override
            public String toString() {
                return supplier.toString();
            }
        };
    }

    public <I, O> void reply(final BakraEvent<I, O> bakraEvent, final Function<I, O> function) {
        eventHandlers.add(Tuple.of((BakraEvent) bakraEvent, (Function) function));
    }

    public <I, O> void reply(final BakraEvent<I, O> bakraEvent, final Supplier<O> supplier) {
        eventHandlers.add(Tuple.of((BakraEvent) bakraEvent, (Function) supplierFunction(supplier)));
    }

    public <I> void subscribe(final BakraEvent<I, Void> bakraEvent, final Consumer<I> consumer) {
        eventHandlers.add(Tuple.of((BakraEvent) bakraEvent, (Function) consumerFunction(consumer)));
    }

    public void emit(final BakraEvent<Void, Void> bakraEvent) {
        requestAll(bakraEvent);
    }

    public <I> void emit(final BakraEvent<I, Void> bakraEvent, final I payload) {
        requestAll(bakraEvent, payload);
    }

    public <I, O> Iterable<O> requestAll(final BakraEvent<I, O> bakraEvent) {
        return requestAll(bakraEvent, null);
    }

    public <I, O> Iterable<O> requestAll(final BakraEvent<I, O> bakraEvent, final I payload) {
        final List<O> results = new ArrayList<>();

        try {
            for (Tuple<BakraEvent, Function> eventHandler : eventHandlers) {
                if (eventHandler._1 == bakraEvent) {
                    Function<I, O> function = (Function<I, O>) eventHandler._2;
                    results.add(function.apply(payload));
                }
            }
        } catch (Exception e) {
            Log.d("DAFUQ", bakraEvent.getName(), e);
        }

        return results;
    }

    public <I, O> O request(final BakraEvent<I, O> bakraEvent) {
        return request(bakraEvent, null);
    }

    public <I, O> O request(final BakraEvent<I, O> bakraEvent, final I payload) {
        for (Tuple<BakraEvent, Function> eventHandler : eventHandlers) {
            if (eventHandler._1 == bakraEvent) {
                Function<I, O> function = (Function<I, O>) eventHandler._2;
                return function.apply(payload);
            }
        }

        return null;
    }

    public <I, O> void unsubscribe(final BakraEvent<I, O> bakraEvent) {
        final Iterator<Tuple<BakraEvent, Function>> iterator = eventHandlers.iterator();

        while (iterator.hasNext()) {
            final Tuple<BakraEvent, Function> eventHandler = iterator.next();
            if (eventHandler._1 == bakraEvent) {
                iterator.remove();
            }
        }
    }

    public void unsubscribeAll() {
        eventHandlers.clear();
    }
}
