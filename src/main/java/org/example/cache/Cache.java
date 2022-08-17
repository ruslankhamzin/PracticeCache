package org.example.cache;

import org.example.cache.exceptions.FileException;
import org.example.cache.storage.DiskStorage;
import org.example.cache.storage.RAMStorage;
import org.example.cache.storage.StorageStrategy;
import org.example.cache.storage.StorageType;

public class Cache<T, V> {
    private StorageStrategy<T, V> storageStrategy;

    public Cache(StorageType storageType, int size) {
        switch (storageType) {
            case DISK:
                storageStrategy = new DiskStorage<>(size);
                break;
            case MEMORY:
                storageStrategy = new RAMStorage<>(size);
                break;
        }
    }

    public void put(T key, V value) throws FileException {
        storageStrategy.put(key, value);
    }

    public Object get(T key) throws FileException {
        return storageStrategy.get(key);

    }

    public void clear() {
        storageStrategy.clear();
    }
}
