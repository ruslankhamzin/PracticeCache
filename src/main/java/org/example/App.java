package org.example;

import org.example.cache.Cache;
import org.example.cache.exceptions.FileAccessException;
import org.example.cache.storage.StorageType;


public class App {
    public static void main(String[] args) {
        Cache<Integer, Integer> cache = new Cache<>(StorageType.DISK, 2);
        try {
            cache.clear();
            cache.put(1, 1);
            cache.put(2, 2);
            cache.get(1);
            cache.put(3, 3);
            System.out.println(cache.get(1));
        } catch (FileAccessException e) {
            e.printStackTrace();
        }


    }
}

