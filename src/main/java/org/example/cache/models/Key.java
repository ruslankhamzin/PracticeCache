package org.example.cache.models;

import java.rmi.server.UID;
import java.time.LocalDateTime;

public final class Key {
    private final String title;
    private final LocalDateTime dateOfCreated;
    private final UID id;

    public Key(String title, LocalDateTime dateOfCreated, UID id) {
        this.title = title;
        this.dateOfCreated = dateOfCreated;
        this.id = id;
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
