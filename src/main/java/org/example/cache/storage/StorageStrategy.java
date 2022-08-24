package org.example.cache.storage;

import org.example.cache.exceptions.FileAccessException;

public interface StorageStrategy<T, V> {

    void put(T key, V value) throws FileAccessException;

    V get(T key) throws FileAccessException;

    void clear();
}
