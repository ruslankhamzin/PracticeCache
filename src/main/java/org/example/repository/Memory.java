package org.example.repository;

import org.example.interfaces.Repository;

import java.util.HashMap;

public class Memory implements Repository {
    public static HashMap<Object, Object> mapCache = new HashMap<>();


    @Override
    public void save(Object key, Object value) {
        mapCache.put(key, value);
    }

    @Override
    public void delete(Object key) {
        mapCache.remove(key);
    }

    @Override
    public Object get(Object key) {
        return mapCache.get(key);
    }

    @Override
    public void clear() {
        mapCache = new HashMap<>();
    }
}
