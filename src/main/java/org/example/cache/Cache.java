package org.example.cache;

import org.example.cache.exceptions.FileAccessException;
import org.example.cache.storage.DiskStorage;
import org.example.cache.storage.RAMStorage;
import org.example.cache.storage.StorageStrategy;
import org.example.cache.storage.StorageType;

public class Cache<T, V> {
    private final StorageStrategy<T, V> storageStrategy;

    public Cache(StorageType storageType, int size) {
        if (storageType == StorageType.DISK) {
            storageStrategy = new DiskStorage<>(size);
        } else {
            storageStrategy = new RAMStorage<>(size);
        }
    }

    public void put(T key, V value) throws FileAccessException {
        storageStrategy.put(key, value);
    }

    public Object get(T key) throws FileAccessException {
        return storageStrategy.get(key);

    }

    public void clear() {
        storageStrategy.clear();
    }
}
