package com.grupo2.desafiospring.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final Map<Class<?>, AtomicLong> idCounter = new HashMap<>();

    private IdGenerator() {

    }

    public static Long generateIdByClass(Class<?> klass) {
        if (!idCounter.containsKey(klass)) {
            idCounter.put(klass, new AtomicLong());
        }
        return idCounter.get(klass).getAndIncrement();
    }
}
