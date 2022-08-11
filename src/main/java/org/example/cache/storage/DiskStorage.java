package org.example.cache.storage;


import java.io.*;
import java.util.Objects;

public class DiskStorage<T, V> implements StorageStrategy<T, V> {
    private final String directory = "C:\\Users\\79199\\Downloads\\PracticeCache\\cachefiles\\";
    private final int size;

    public DiskStorage(int size) {
        this.size = size;
    }

    @Override
    public void put(T key, V value) {
        while (size <= Objects.requireNonNull(new File(directory).listFiles()).length) {
            pruning();
        }
        try (FileOutputStream fos = new FileOutputStream(directory + key + ".txt"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(value);

        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");
        }

    }

    @Override
    public V get(T key) {
        V value = null;
        try (FileInputStream fis = new FileInputStream(directory + key + ".txt"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            value = (V) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при получении данных");
        }
        File file = new File(directory + key + ".txt");
        if (file.isFile()) {
            file.delete();
        }
        try (FileOutputStream fos = new FileOutputStream(directory + key + ".txt"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(value);

        } catch (IOException e) {
            System.out.println("Ошибка при перезаписи файла");
        }
        return value;
    }

    @Override
    public void pruning() {
        File minLastModified = (Objects.requireNonNull(new File(directory).listFiles())[0]);
        for (File myFile : Objects.requireNonNull(new File(directory).listFiles())) {
            if (myFile.lastModified() < minLastModified.lastModified()) {
                minLastModified = myFile;
            }
        }
        minLastModified.delete();

    }


    @Override
    public void clear() {
        for (File myFile : Objects.requireNonNull(new File(directory).listFiles()))
            if (myFile.isFile()) myFile.delete();
    }
}
