package org.example.cache.storage;

public interface StorageStrategy<T, V> {

    void put(T key, V value);

    V get(T key);

    void clear();
}
