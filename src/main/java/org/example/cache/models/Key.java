package org.example.cache.models;

import java.time.LocalDateTime;
import java.util.Random;

public class Key {
    String title;
    LocalDateTime dateOfCreated;
    Long id;

    public Key(String title) {
        this.title = title;
        dateOfCreated = LocalDateTime.now();
        Random random = new Random();
        id = random.nextLong();
    }

    @Override
    public String toString() {
        return "Key{" +
                "title='" + title + '\'' +
                ", dateOfCreated=" + dateOfCreated +
                ", id=" + id +
                '}';
    }
}
