package org.example;

import org.example.cache.Cache;
import org.example.cache.exceptions.FileAccessException;
import org.example.cache.storage.StorageType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiskStorageTest {

    @Test(expected = FileAccessException.class)
    public void TestGetFromDiskWithException() throws FileAccessException {
        Cache<String, Integer> cache = new Cache<>(StorageType.DISK, 2);
            cache.put("First", 1);
            cache.put("Second", 2);
            cache.put("Third", 3);
            assertEquals(3, cache.get("Third"));
            assertEquals(2, cache.get("Second"));
            cache.get("First");
    }

    @Test
    public void TestGetFromDisk() throws FileAccessException {
        Cache<String, Integer> cache = new Cache<>(StorageType.DISK, 2);
        cache.put("Second", 2);
        cache.put("Third", 3);
        assertEquals(3, cache.get("Third"));
        assertEquals(2, cache.get("Second"));
    }

    @Test(expected = FileAccessException.class)
    public void TestClearFromDisk() throws FileAccessException {
        Cache<String, Integer> cache = new Cache<>(StorageType.DISK, 2);
        cache.put("First", 1);
        cache.put("Second", 2);
        cache.put("Third", 3);
        cache.clear();
        assertEquals(3, cache.get("Third"));
    }

    @Test(expected = FileAccessException.class)
    public void TestStrategyFromDisk() throws FileAccessException {
        Cache<String, Integer> cache = new Cache<>(StorageType.DISK, 2);
        cache.put("First", 1);
        cache.put("Second", 2);
        cache.get("First");
        cache.put("Third", 3);
        assertEquals(3, cache.get("Third"));
        assertEquals(1, cache.get("First"));
        cache.get("Second");

    }
}
