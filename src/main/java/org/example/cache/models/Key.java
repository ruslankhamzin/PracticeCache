package org.example.cache.models;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.Objects;

public final class Key {
    private final String title;
    private final LocalDateTime dateOfCreated;
    private final UID id;

    public Key(String title, LocalDateTime dateOfCreated, UID id) {
        this.title = title;
        this.dateOfCreated = dateOfCreated;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateOfCreated() {
        return dateOfCreated;
    }

    public UID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return Objects.equals(title, key.title) &&
                Objects.equals(dateOfCreated, key.dateOfCreated) &&
                Objects.equals(id, key.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, dateOfCreated, id);
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
