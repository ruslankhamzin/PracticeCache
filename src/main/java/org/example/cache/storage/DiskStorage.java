package org.example.cache.storage;


import org.example.cache.exceptions.FileAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
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
        if (size == cacheFiles.listFiles().length) pruning();
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
        V value;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(DIRECTORY + key + FILETYPE)))) {
            value = (V) objectInputStream.readObject();
            File file = new File(DIRECTORY + key + FILETYPE);
            if (file.exists()) {
                file.setLastModified(System.currentTimeMillis());
            }
            LOGGER.info("the file was successfully received");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error in class DiskStorage, method get. Error is related to get object from file or file not exists");
            throw new FileAccessException("Could not read object from " + key + FILETYPE + " in " + DIRECTORY);
        }
        return value;
    }

    private void pruning() {
        List<File> listWithFiles = Arrays.asList(cacheFiles.listFiles().clone());
        Comparator<File> fileComparator = Comparator.comparing(File::lastModified);
        listWithFiles.sort(fileComparator);
        File minLastModified = listWithFiles.get(0);
        minLastModified.delete();
        LOGGER.info("the file was deleted following the LRU strategy");
    }

    @Override
    public void clear() {
        for (File myFile : cacheFiles.listFiles()) {
            if (myFile.isFile()) myFile.delete();
        }
        LOGGER.info("The cache has been cleared");
    }
}
