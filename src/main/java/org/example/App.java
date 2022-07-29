package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write a size of cache: ");
            int size = Integer.parseInt(reader.readLine());
            System.out.println("Choose a repository (1-Disk,2-Memory): ");
            int repo = Integer.parseInt(reader.readLine());
            boolean onDisk = false;
            if (repo == 1) {
                onDisk = true;
            }
            RealizationCache myCache = new RealizationCache(onDisk, size);


        while (true) {
            System.out.println("Write a key(write delete to clear cache):");
            String key = reader.readLine();
            if (key.equals("delete")) {
                myCache.clear();
                continue;
            }
            if (myCache.get(key) == null) {
                System.out.println("Write a value: ");
                String value = reader.readLine();
                myCache.put(key, value);
                //System.out.println(myCache.map1.toString() + " " + myCache.map.toString());
            } else {
                System.out.println("Key: " + key + " Value: " + myCache.get(key).toString());
            }
        }
        } catch (IOException e) {
        System.out.println("Ошибка ввода-вывода");
    }
    }
}
