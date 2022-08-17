package org.example;

import org.example.cache.Cache;
import org.example.cache.exceptions.FileException;
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
        Key key2 = new Key("First", LocalDateTime.now(), UUID.randomUUID());
        Key key3 = new Key("First", LocalDateTime.now(), UUID.randomUUID());
        try {
            cache.put(key1, 1);
            cache.put(key2, 2);
            cache.put(key3, 3);
            assertEquals(3, cache.get(key3));
            assertEquals(2, cache.get(key2));
            assertNull(cache.get(key1));
        } catch (FileException e) {
            e.printStackTrace();
        }

    }
}
