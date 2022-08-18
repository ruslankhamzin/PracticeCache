package org.example;

import org.example.cache.Cache;
import org.example.cache.exceptions.FileAccessException;
import org.example.cache.models.Key;
import org.example.cache.storage.StorageType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MemoryStorageTest {

    @Test
    public void TestGetFromMemory() {
        Cache<Key, Integer> cache = new Cache<>(StorageType.MEMORY, 2);
        Key key1 = new Key("First", LocalDateTime.now(), UUID.randomUUID());
        Key key2 = new Key("Second", LocalDateTime.now(), UUID.randomUUID());
        Key key3 = new Key("Third", LocalDateTime.now(), UUID.randomUUID());
        try {
            cache.put(key1, 1);
            cache.put(key2, 2);
            cache.put(key3, 3);
            assertEquals(2, cache.get(key2));
            assertEquals(3, cache.get(key3));
            assertNull(cache.get(key1));
        } catch (FileAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestClearFromMemory() {
        Cache<Key, Integer> cache = new Cache<>(StorageType.MEMORY, 2);
        Key key1 = new Key("First", LocalDateTime.now(), UUID.randomUUID());
        Key key2 = new Key("First", LocalDateTime.now(), UUID.randomUUID());
        try {
            cache.put(key1, 1);
            cache.put(key2, 2);
            cache.clear();
            assertNull(cache.get(key1));
            assertNull(cache.get(key2));
        } catch (FileAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestStrategyForMemory() {
        Cache<Key, Integer> cache = new Cache<>(StorageType.MEMORY, 2);
        Key key1 = new Key("First", LocalDateTime.now(), UUID.randomUUID());
        Key key2 = new Key("First", LocalDateTime.now(), UUID.randomUUID());
        Key key3 = new Key("First", LocalDateTime.now(), UUID.randomUUID());
        try {
            cache.put(key1, 1);
            cache.put(key2, 2);
            cache.get(key1);
            cache.put(key3, 3);
            assertEquals(3, cache.get(key3));
            assertEquals(1, cache.get(key1));
            assertNull(cache.get(key2));
        } catch (FileAccessException e) {
            e.printStackTrace();
        }
    }

}
