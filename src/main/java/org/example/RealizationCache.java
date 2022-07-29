package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RealizationCache implements CacheInterface {
    private String secondLevelCacheFileName;
    private String secondLevelCacheMetadataFileName;
    private HashMap<String, String> mapWithKeysAndValues;
    private HashMap<String, Integer> mapWithKeysAndCountOfUse;
    private boolean onDisk;
    private int cacheSize;

    public RealizationCache(boolean onDisk, int cacheSize) {
        this.onDisk = onDisk;
        this.cacheSize = cacheSize;
        secondLevelCacheFileName = "FileWithKeysAndValues.txt";
        secondLevelCacheMetadataFileName = "FileWithKeysAndCountOfUse.txt";
        mapWithKeysAndValues = new HashMap<>();
        mapWithKeysAndCountOfUse = new HashMap<>();
    }


    public String getSecondLevelCacheFileName() {
        return secondLevelCacheFileName;
    }

    public void setSecondLevelCacheFileName(String secondLevelCacheFileName) {
        this.secondLevelCacheFileName = secondLevelCacheFileName;
    }

    public String getSecondLevelCacheMetadataFileName() {
        return secondLevelCacheMetadataFileName;
    }

    public void setSecondLevelCacheMetadataFileName(String secondLevelCacheMetadataFileName) {
        this.secondLevelCacheMetadataFileName = secondLevelCacheMetadataFileName;
    }

    public HashMap<String, String> getMapWithKeysAndValues() {
        return mapWithKeysAndValues;
    }

    public void setMapWithKeysAndValues(HashMap<String, String> mapWithKeysAndValues) {
        this.mapWithKeysAndValues = mapWithKeysAndValues;
    }

    public HashMap<String, Integer> getMapWithKeysAndCountOfUse() {
        return mapWithKeysAndCountOfUse;
    }

    public void setMapWithKeysAndCountOfUse(HashMap<String, Integer> mapWithKeysAndCountOfUse) {
        this.mapWithKeysAndCountOfUse = mapWithKeysAndCountOfUse;
    }

    public boolean isOnDisk() {
        return onDisk;
    }

    public void setOnDisk(boolean onDisk) {
        this.onDisk = onDisk;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public void put(String key, String value) {
        while (mapWithKeysAndValues.size() >= this.cacheSize) {
            this.pruning();
        }
        mapWithKeysAndValues.put(key, value);
        mapWithKeysAndCountOfUse.put(key, 1);
    }

    @Override
    public Object get(String key) {
        if(onDisk) { fromDiskToCache(); }
        boolean res3 = mapWithKeysAndCountOfUse.containsKey(key);
        if (res3) {
            int value = mapWithKeysAndCountOfUse.get(key);
            value++;
            mapWithKeysAndCountOfUse.put(key, value);
            if (onDisk) {
                cacheOnDisk();
            }
            return mapWithKeysAndValues.get(key);
        }
        return null;

    }

    @Override
    public void remove(String key) {
        mapWithKeysAndValues.remove(key);
        mapWithKeysAndCountOfUse.remove(key);
    }

    @Override
    public void clear() {
        mapWithKeysAndCountOfUse = new HashMap<>();
        mapWithKeysAndValues = new HashMap<>();
        if (onDisk) {
            File fileWithMapWithKeysAndValues = new File(secondLevelCacheFileName);
            File fileWithMapWithKeysAndCountOfUse = new File(secondLevelCacheMetadataFileName);
            fileWithMapWithKeysAndCountOfUse.delete();
            fileWithMapWithKeysAndValues.delete();
        }
    }

    @Override
    public void cacheOnDisk() {

    }

    @Override
    public void fromDiskToCache() {

    }


    public void pruning() {
        Map.Entry<String,Integer> min = null;
        for(Map.Entry<String, Integer> e: mapWithKeysAndCountOfUse.entrySet()){
            int frequency = e.getValue();
            if(min == null || min.getValue()>=frequency){
                min = e;
            }
        }
        mapWithKeysAndCountOfUse.remove(min.getKey());
        mapWithKeysAndValues.remove(min.getKey());
    }
}

