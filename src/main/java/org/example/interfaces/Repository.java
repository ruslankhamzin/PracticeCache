package org.example.interfaces;

public interface Repository {
    void save(Object key, Object value);

    void delete(Object key);

    Object get(Object key);

    void clear();
}
