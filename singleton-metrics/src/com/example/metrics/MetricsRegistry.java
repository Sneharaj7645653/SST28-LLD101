package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * INTENTION: Global metrics registry (should be a Singleton).
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Constructor is public -> anyone can create instances.
 * - getInstance() is lazy but NOT thread-safe -> can create multiple instances.
 * - Reflection can call the constructor to create more instances.
 * - Serialization can create a new instance when deserialized.
 *
 * TODO (student):
 *  1) Make it a proper lazy, thread-safe singleton (private ctor)
 *  2) Block reflection-based multiple construction
 *  3) Preserve singleton on serialization (readResolve)
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    private MetricsRegistry() {
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException("Use getInstance()");
        }
    }

    private static class Holder {
        private static final MetricsRegistry INSTANCE =
                new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    @Serial
    private Object readResolve() {
        return getInstance();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}




/**
 * 1. Removed the unsafe INSTANCE field
 *
 * Before:
 *
 * private static MetricsRegistry INSTANCE;
 * This was:
 *
 * Not volatile
 *
 * Not thread-safe
 *
 * Used in racy lazy initialization
 *
 *
 * 2. Constructor changed from public to private
 *
 * Before:
 *
 * public MetricsRegistry()
 *
 * Anyone could create new objects.
 *
 * Now:
 *
 * private MetricsRegistry()
 *
 *
 * 3. Added reflection protection inside constructor
 * if (Holder.INSTANCE != null) {
 *     throw new IllegalStateException("Use getInstance()");
 * }
 *
 * If reflection tries to create a second instance after the singleton is initialized, it throws an exception.
 *
 * This blocks multiple instances created via reflection (after initialization).
 *
 *4. Replaced racy lazy init with Holder pattern (thread-safe lazy initialization)
 *
 * Added:
 *
 * private static class Holder {
 *     private static final MetricsRegistry INSTANCE =
 *             new MetricsRegistry();
 * }
 *
 * And changed getInstance() to:
 *
 * public static MetricsRegistry getInstance() {
 *     return Holder.INSTANCE;
 * }
 *
 * Why this works:
 *
 * The inner class is not loaded until getInstance() is called.
 *
 * Class loading in Java is thread-safe.
 *
 * Ensures lazy initialization.
 *
 * Ensures only one instance is created.
 *
 * No synchronization needed.
 *
 * This removes the race condition.
 *
 * 5. Implemented readResolve() to preserve singleton during deserialization
 * @Serial
 * private Object readResolve() {
 *     return getInstance();
 * }
 *
 * Without this, deserialization would create a new instance.
 *
 * With this method:
 *
 * The deserialized object is replaced with the existing singleton.
 *
 * Singleton property is preserved.
 *
 * 6. Prevented cloning
 * @Override
 * protected Object clone() throws CloneNotSupportedException {
 *     throw new CloneNotSupportedException();
 * }
 *
 * This prevents creating a second instance via cloning.
 *
 * Summary of What Was Fixed
 *
 * Constructor made private.
 *
 * Removed racy lazy initialization.
 *
 * Implemented thread-safe lazy initialization using Holder pattern.
 *
 * Added protection against reflection.
 *
 * Preserved singleton during serialization.
 *
 * Blocked cloning-based duplication.
 *
 * The class is now a proper lazy, thread-safe Singleton.
 *
 *
 *
 * */