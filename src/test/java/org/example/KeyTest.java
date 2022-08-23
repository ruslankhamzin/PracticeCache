package org.example;

import org.example.cache.Cache;
import org.example.cache.exceptions.FileAccessException;
import org.example.cache.models.Key;
import org.example.cache.storage.StorageType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class KeyTest {

    @Test
    public void testEqualsMethod() {
        String title = "My key";
        LocalDateTime localDateTime = LocalDateTime.now();
        UUID uuid = UUID.randomUUID();

        Key key1 = new Key(title, localDateTime, uuid);
        Key key2 = new Key(title, localDateTime, uuid);

        assertEquals(key1, key2);
    }

    @Test
    public void testEqualsMethodWithMemoryCache() throws FileAccessException {
        String title = "My key";
        LocalDateTime localDateTime = LocalDateTime.now();
        UUID uuid = UUID.randomUUID();

        Cache<Key, Integer> cache = new Cache<>(StorageType.MEMORY, 2);
        Key key1 = new Key(title, localDateTime, uuid);
        Key key2 = new Key(title, localDateTime, uuid);

        cache.put(key1, 1);
        assertEquals(1, cache.get(key2));

    }

    @Test
    public void testEqualsMethodWithDiskCache() throws FileAccessException {
        String title = "My key";
        LocalDateTime localDateTime = LocalDateTime.now();
        UUID uuid = UUID.randomUUID();

        Cache<Key, Integer> cache = new Cache<>(StorageType.DISK, 2);
        Key key1 = new Key(title, localDateTime, uuid);
        Key key2 = new Key(title, localDateTime, uuid);

        cache.put(key1, 1);
        assertEquals(1, cache.get(key2));
    }

    @Test
    public void testHashCodeMethod() {
        String title = "My key";
        LocalDateTime localDateTime = LocalDateTime.now();
        UUID uuid = UUID.randomUUID();

        Key key1 = new Key(title, localDateTime, uuid);
        Key key2 = new Key(title, localDateTime, uuid);

        assertEquals(key1.hashCode(), key2.hashCode());
    }

    @Test
    public void testToStringMethod() {
        String title = "My key";
        LocalDateTime localDateTime = LocalDateTime.now();
        UUID uuid = UUID.randomUUID();

        Key key1 = new Key(title, localDateTime, uuid);

        assertEquals(title + " " + uuid, key1.toString());
    }
}
