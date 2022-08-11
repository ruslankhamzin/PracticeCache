package org.example.cache;

import org.example.cache.storage.DiskStorage;
import org.example.cache.storage.RAMStorage;
import org.example.cache.storage.Storage;
import org.example.cache.storage.StorageStrategy;


public class Cache<T, V> {

    private StorageStrategy<T, V> storageStrategy;

    public Cache(Storage storage, int size) {
        switch (storage) {
            case Disk:
                storageStrategy = new DiskStorage<>(size);
                break;
            case Memory:
                storageStrategy = new RAMStorage<>(size);
                break;
        }


    }


    public void put(T key, V value) {
        storageStrategy.put(key, value);
    }


    public Object get(T key) {
        return storageStrategy.get(key);

    }

    public void clear() {
        storageStrategy.clear();
    }

}
