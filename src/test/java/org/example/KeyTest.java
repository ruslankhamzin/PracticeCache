package org.example;

import org.example.cache.models.Key;
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

        assertEquals(title + localDateTime + uuid, key1.toString());
    }
}
