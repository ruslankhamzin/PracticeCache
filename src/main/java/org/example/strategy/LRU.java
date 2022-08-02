package org.example.strategy;

import org.example.interfaces.Cache;
import org.example.interfaces.Repository;

import java.util.HashMap;
import java.util.Map;

public class LRU implements Cache {
    Repository repository;
    int cacheSize = 2;
     HashMap<Object, Integer> mapCountOfUse = new HashMap<>();

    public LRU(Repository repository, int cacheSize) {
        this.repository = repository;
        this.cacheSize = cacheSize;
    }

    public LRU(Repository repository) {
        this.repository = repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public void put(Object key, Object value) {
        if(cacheSize==mapCountOfUse.size()){this.pruning();}
        repository.save(key, value);
        mapCountOfUse.put(key,1);
    }

    @Override
    public Object get(Object key) {
        int value = mapCountOfUse.get(key);
        value++;
        mapCountOfUse.put(key,value);
        return repository.get(key);
    }

    @Override
    public void pruning() {
        Map.Entry<Object, Integer> min = null;
        for (Map.Entry<Object, Integer> e: mapCountOfUse.entrySet()){
            int frequency = e.getValue();
            if(min == null || min.getValue()>=frequency){
                min = e;
            }
        }
        repository.delete(min.getKey());
        mapCountOfUse.remove(min.getKey());

    }

    @Override
    public void clear() {
        repository.clear();
        mapCountOfUse = new HashMap<>();
    }
}
