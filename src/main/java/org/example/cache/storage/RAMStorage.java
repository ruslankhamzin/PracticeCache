package org.example.cache.storage;


import org.example.cache.Cache;

import java.util.HashMap;
import java.util.Map;

public class RAMStorage<T, V> implements CacheStrategy<T, V> {
    private final HashMap<T, V> values;
    private final HashMap<T, Integer> countOfUse;

    public RAMStorage() {
        values = new HashMap<>();
        countOfUse = new HashMap<>();
    }


    @Override
    public void put(T key, V value) {
        if (Cache.getSize() == values.size()) {
            pruning();
        }
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
        Map.Entry<T, Integer> min = null;
        for (Map.Entry<T, Integer> e : countOfUse.entrySet()) {
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
