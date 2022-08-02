package org.example;


import org.example.repository.Memory;
import org.example.strategy.LRU;

public class App {
    public static void main(String[] args) {
        LRU LRUCache = new LRU(new Memory());
        LRUCache.put(1,1);
        LRUCache.get(1);
        LRUCache.put(2,2);
        LRUCache.put(3,3);
        System.out.println(Memory.mapCache.toString());
        LRUCache.put(4,4);
        LRUCache.get(4);
        LRUCache.get(4);
        LRUCache.put(5,5);
        System.out.println(Memory.mapCache.toString());
    }
}
