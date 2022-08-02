package org.example.repository;

import org.example.interfaces.Cache;
import org.example.interfaces.Repository;

import java.util.HashMap;

public class Disk implements Repository {


    @Override
    public void save(Object key, Object value) {

    }

    @Override
    public void delete(Object key) {

    }

    @Override
    public Object get(Object key) {

        return key;
    }





    @Override
    public void clear() {

    }
}
