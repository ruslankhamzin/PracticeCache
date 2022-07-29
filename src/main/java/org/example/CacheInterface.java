package org.example;

public interface CacheInterface {
    void put(String key, String value);

    Object get(String key);

    void remove(String key);

    void clear();

    void cacheOnDisk();

    void fromDiskToCache();
}
