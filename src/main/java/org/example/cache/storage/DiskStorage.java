package org.example.cache.storage;


import org.example.cache.exceptions.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class DiskStorage<T, V> implements StorageStrategy<T, V> {
    private static final String DIRECTORY = "cacheFiles\\";
    private static final String FILETYPE = ".txt";
    private static final Logger LOGGER = LoggerFactory.getLogger(DiskStorage.class);
    private final int size;
    private final File cacheFiles;

    public DiskStorage(int size) {
        cacheFiles = new File(DIRECTORY);
        this.size = size;
    }

    @Override
    public void put(T key, V value) throws FileException {
        while (size <= cacheFiles.listFiles().length) pruning();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(DIRECTORY + key + FILETYPE)))) {
            objectOutputStream.writeObject(value);
        } catch (IOException e) {
            LOGGER.error("Error in class DiskStorage, method put. Error is related to write object to file");
            throw new FileException("Could not write object to " + key + FILETYPE + " in " + DIRECTORY);
        }
    }

    @Override
    public V get(T key) throws FileException {
        V value;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(DIRECTORY + key + FILETYPE)))) {
            value = (V) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error in class DiskStorage, method get. Error is related to get object from file");
            throw new FileException("Could not read object from " + key + FILETYPE + " in " + DIRECTORY);
        }
        File file = new File(DIRECTORY + key + FILETYPE);
        if (file.isFile()) {
            file.delete();
        }
        this.put(key, value);
        return value;
    }

    private void pruning() {
        File minLastModified = (cacheFiles.listFiles())[0];
        for (File myFile : cacheFiles.listFiles()) {
            if (myFile.lastModified() < minLastModified.lastModified()) {
                minLastModified = myFile;
            }
        }
        minLastModified.delete();
    }

    @Override
    public void clear() {
        for (File myFile : cacheFiles.listFiles()) {
            if (myFile.isFile()) myFile.delete();
        }
    }
}
