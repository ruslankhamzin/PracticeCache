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
    private final File cacheFiles;
    private final int size;

    public DiskStorage(final int size) {
        cacheFiles = new File(DIRECTORY);
        this.size = size;
    }

    @Override
    public void put(final T key, final V value) throws FileAccessException {
        pruning();
        putFileOnDisk(key, value);
    }

    private void pruning() {
        if (cacheFiles.listFiles() != null) {
            List<File> listWithFiles = Arrays.asList(cacheFiles.listFiles());
            listWithFiles.sort(((o1, o2) -> Long.compare(o2.lastModified(), o1.lastModified())));
            for (int i = size - 1; i < listWithFiles.size(); i++) {
                File minLastModified = listWithFiles.get(i);
                removeFile(minLastModified);
            }
            LOGGER.info("the files was deleted following the LRU strategy. ");
        }
    }

    private void removeFile(final File fileToDelete) {
        if (fileToDelete.isFile()) {
            fileToDelete.delete();
            LOGGER.info(fileToDelete.getName() + " was deleted.");
        }
    }

    private void putFileOnDisk(final T key, final V value) throws FileAccessException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(DIRECTORY + key + FILETYPE)))) {
            objectOutputStream.writeObject(value);
            LOGGER.info("the file was successfully written. Key: " + key + " Value: " + value);
        } catch (IOException e) {
            LOGGER.error("Error in class DiskStorage, method put. Error is related to write object to file. ");
            throw new FileAccessException("Could not write object to " + key + FILETYPE + " in " + DIRECTORY);
        }
    }

    @Override
    public V get(final T key) throws FileAccessException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(DIRECTORY + key + FILETYPE)))) {
            V value = (V) objectInputStream.readObject();
            File fileWithValue = new File(DIRECTORY + key + FILETYPE);
            if (fileWithValue.exists()) {
                fileWithValue.setLastModified(System.currentTimeMillis());
            }
            LOGGER.info("value from" + key + FILETYPE + " was successfully received. ");
            return value;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error in class DiskStorage, method get. Error is related to get object from file or file not exists. ");
            throw new FileAccessException("Could not read object from " + key + FILETYPE + " in " + DIRECTORY);
        }
    }

    @Override
    public void clear() {
        if (cacheFiles.listFiles() != null) {
            for (File cacheFile : cacheFiles.listFiles()) {
                removeFile(cacheFile);
            }
            LOGGER.info("The cache has been cleared. ");
        }
    }
}
