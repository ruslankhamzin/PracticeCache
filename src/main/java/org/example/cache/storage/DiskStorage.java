package org.example.cache.storage;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DiskStorage<T, V> implements CacheStrategy<T, V> {

    @Override
    public void put(T key, V value) {
        try {
            FileOutputStream fos = new FileOutputStream(key + ".txt");
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            ois.writeObject(value);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object get(T key) {
        return null;
    }

    @Override
    public void pruning() {

    }


    @Override
    public void clear() {

    }
}
