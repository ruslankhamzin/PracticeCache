package org.example;

import org.example.cache.Cache;
import org.example.cache.storage.StorageType;

public class App {
    public static void main(String[] args) {
        Cache<Integer, String> cache = new Cache<>(StorageType.DISK, 2);
        cache.put(1, "1");
        cache.put(2, "2");
        cache.get(1);
        cache.put(3, "3");
        System.out.println(cache.get(3));

    }
}

