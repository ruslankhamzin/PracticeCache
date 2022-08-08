package org.example.cache.storage;

public interface CacheStrategy<T, V> {

    void put(T key, V value);

    Object get(T key);

    void pruning();

    void clear();
}
