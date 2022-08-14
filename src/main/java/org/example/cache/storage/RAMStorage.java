package org.example.cache.storage;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RAMStorage<T, V> implements StorageStrategy<T, V> {
    private final LinkedHashMap<T, V> values;
    private final int size;

    public RAMStorage(int size) {
        this.size = size;
        values = new LinkedHashMap<>();
    }


    @Override
    public void put(T key, V value) {
        while (size <= values.size()) pruning();
        values.put(key, value);
    }


    @Override
    public V get(T key) {
        V value = values.get(key);
        values.remove(key);
        values.put(key, value);
        return values.get(key);
    }


    private void pruning() {
        List<T> keys = new ArrayList<>(values.keySet());
        values.remove(keys.get(0));
    }


    @Override
    public void clear() {
        values.clear();
    }
}
