package org.example.cache.storage;


import java.util.LinkedHashMap;
import java.util.Map;

public class RAMStorage<T, V> implements StorageStrategy<T, V> {
    private final LinkedHashMap<T, V> values;

    public RAMStorage(int size) {
        values = new LinkedHashMap<T, V>(size) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size < values.size();
            }
        };
    }

    @Override
    public void put(T key, V value) {
        values.put(key, value);
    }

    @Override
    public V get(T key) {
        V value = values.get(key);
        values.remove(key);
        values.put(key, value);
        return value;
    }

    @Override
    public void clear() {
        values.clear();
    }
}
