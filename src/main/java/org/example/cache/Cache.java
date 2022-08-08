package org.example.cache;

import org.example.cache.storage.CacheStrategy;


public class Cache<T, V> {
    private static int size;
    private final CacheStrategy<T, V> cacheStrategy;

    public Cache(CacheStrategy<T, V> cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        size = 2;
    }

    public Cache(CacheStrategy<T, V> cacheStrategy, int size) {
        Cache.size = size;
        this.cacheStrategy = cacheStrategy;
    }

    public static int getSize() {
        return size;
    }

    public void put(T key, V value) {
        cacheStrategy.put(key, value);
    }


    public Object get(T key) {
        return cacheStrategy.get(key);

    }

    public void clear() {
        cacheStrategy.clear();
    }

}
