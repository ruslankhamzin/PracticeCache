package org.example.cache.storage;


import java.util.LinkedHashMap;
import java.util.Map;

public class RAMStorage<T, V> implements StorageStrategy<T, V> {
    private final int size;
    public LinkedHashMap<T, V> values;

    public RAMStorage(int size) {
        this.size = size;
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

    /*private void pruning() {
        List<T> keys = new ArrayList<>(values.keySet());
        values.remove(keys.get(0));
    }*/

    @Override
    public void clear() {
        values.clear();
    }
}
