package org.example.cache.storage;


import org.example.cache.exceptions.FileAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

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
    public void put(T key, V value) throws FileAccessException {
        pruning();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(DIRECTORY + key + FILETYPE)))) {
            objectOutputStream.writeObject(value);
            LOGGER.info("the file was successfully written. Key: " + key + "Value: " + value);
        } catch (IOException e) {
            LOGGER.error("Error in class DiskStorage, method put. Error is related to write object to file");
            throw new FileAccessException("Could not write object to " + key + FILETYPE + " in " + DIRECTORY);
        }
    }

    @Override
    public V get(T key) throws FileAccessException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(DIRECTORY + key + FILETYPE)))) {
            V value = (V) objectInputStream.readObject();
            File file = new File(DIRECTORY + key + FILETYPE);
            if (file.exists()) {
                file.setLastModified(System.currentTimeMillis());
            }
            LOGGER.info("the file was successfully received");
            return value;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error in class DiskStorage, method get. Error is related to get object from file or file not exists");
            throw new FileAccessException("Could not read object from " + key + FILETYPE + " in " + DIRECTORY);
        }
    }

    private void pruning() {
        if (cacheFiles.exists() && cacheFiles.listFiles() != null && size <= cacheFiles.listFiles().length) {
            List<File> listWithFiles = Arrays.asList(cacheFiles.listFiles());
            listWithFiles.sort(((o1, o2) -> Long.compare(o2.lastModified(), o1.lastModified())));
            for (int i = size - 1; i < listWithFiles.size(); i++) {
                File minLastModified = listWithFiles.get(i);
                minLastModified.delete();
            }
            LOGGER.info("the file was deleted following the LRU strategy");
        }
    }

    @Override
    public void clear() {
        if (cacheFiles.exists() && cacheFiles.listFiles() != null) {
            for (File myFile : cacheFiles.listFiles()) {
                if (myFile.isFile()) myFile.delete();
            }
            LOGGER.info("The cache has been cleared");
        }
    }
}
