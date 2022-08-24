package org.example.cache.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class Key implements Serializable {
    private final String title;
    private final LocalDateTime dateOfCreated;
    private final UUID id;

    public Key(final String title, final LocalDateTime dateOfCreated, final UUID id) {
        this.title = title;
        this.dateOfCreated = dateOfCreated;
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Key key = (Key) o;
        return Objects.equals(title, key.title)
                && Objects.equals(dateOfCreated, key.dateOfCreated)
                && Objects.equals(id, key.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, dateOfCreated, id);
    }

    @Override
    public String toString() {
        return title + " " + id;
    }
}
