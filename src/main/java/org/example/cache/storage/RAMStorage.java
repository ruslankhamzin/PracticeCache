package org.example.cache.storage;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class RAMStorage<T, V> implements StorageStrategy<T, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RAMStorage.class);
    private final LinkedHashMap<T, V> values;

    public RAMStorage(int size) {
        values = new LinkedHashMap<T, V>(size, .75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                LOGGER.info("Remove element following the LRU strategy");
                return size < values.size();
            }
        };
    }

    @Override
    public void put(T key, V value) {
        values.put(key, value);
        LOGGER.info("The element was successfully save in cache. Key: " + key + "Value: " + value);
    }

    @Override
    public V get(T key) {
        return values.get(key);
    }

    @Override
    public void clear() {
        values.clear();
        LOGGER.info("The cache has been cleared");
    }
}
