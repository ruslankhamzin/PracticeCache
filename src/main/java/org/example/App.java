package org.example;

import org.example.cache.Cache;
import org.example.cache.storage.RAMStorage;

public class App {
    public static void main(String[] args) {
        Cache cache = new Cache<>(new RAMStorage());
    }
}
