package org.example.cache.storage;

import org.example.cache.exceptions.FileException;


public interface StorageStrategy<T, V> {

    void put(T key, V value) throws FileException;

    V get(T key) throws FileException;

    void clear();
}
