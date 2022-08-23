package org.example;

import org.example.cache.Cache;
import org.example.cache.exceptions.FileAccessException;
import org.example.cache.storage.StorageType;


public class App {
    public static void main(String[] args) throws FileAccessException {
        Cache<Integer, Integer> cache = new Cache<>(StorageType.DISK, 2);
        cache.put(1,1);
    }
}

