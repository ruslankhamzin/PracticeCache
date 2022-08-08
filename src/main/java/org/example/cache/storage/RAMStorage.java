package org.example.cache.storage;


import java.util.HashMap;
import java.util.Map;

public class RAMStorage<T, V> implements CacheStrategy<T, V> {
    private final HashMap<Object, Object> values;
    private final HashMap<Object, Integer> countOfUse;

    public RAMStorage() {
        values = new HashMap<>();
        countOfUse = new HashMap<>();
    }


    @Override
    public void put(T key, V value) {
        values.put(key, value);
        countOfUse.put(key, 1);
    }

    @Override
    public Object get(T key) {
        int value = countOfUse.get(key);
        value++;
        countOfUse.put(key, value);
        return values.get(key);

    }

    @Override
    public void pruning() {
        Map.Entry<Object, Integer> min = null;
        for (Map.Entry<Object, Integer> e : countOfUse.entrySet()) {
            int frequency = e.getValue();
            if (min == null || min.getValue() >= frequency) {
                min = e;
            }
        }
        countOfUse.remove(min.getKey());
        values.remove(min.getKey());
    }


    @Override
    public void clear() {
        countOfUse.clear();
        values.clear();
    }
}
