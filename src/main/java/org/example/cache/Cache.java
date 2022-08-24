package org.example.cache;

import org.example.cache.exceptions.FileAccessException;
import org.example.cache.storage.DiskStorage;
import org.example.cache.storage.RAMStorage;
import org.example.cache.storage.StorageStrategy;
import org.example.cache.storage.StorageType;

public class Cache<T, V> {
    private StorageStrategy<T, V> storageStrategy;

    public Cache(final StorageType storageType, final int size) {
        switch (storageType) {
            case DISK:
                storageStrategy = new DiskStorage<>(size);
                break;
            case MEMORY:
                storageStrategy = new RAMStorage<>(size);
                break;
        }
    }

    public void put(final T key, final V value) throws FileAccessException {
        storageStrategy.put(key, value);
    }

    public Object get(final T key) throws FileAccessException {
        return storageStrategy.get(key);

    }

    public void clear() {
        storageStrategy.clear();
    }
}
