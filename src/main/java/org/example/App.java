package org.example;

import org.example.cache.Cache;
import org.example.cache.exceptions.FileException;
import org.example.cache.storage.StorageType;


public class App {
    public static void main(String[] args) {
        Cache<Integer, Integer> cache = new Cache<>(StorageType.DISK, 2);
        try {
            cache.put(1, 1);
            cache.put(2, 2);
            cache.get(1);
            cache.put(3, 3);
            cache.put(4, 4);
            System.out.println(cache.get(1));
        } catch (FileException e) {
            e.printStackTrace();
        }


    }
}

