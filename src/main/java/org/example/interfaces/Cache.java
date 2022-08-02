package org.example.interfaces;

public interface Cache {
    void put(Object key, Object value);

    Object get(Object key);

    void pruning();

    void clear();

}
