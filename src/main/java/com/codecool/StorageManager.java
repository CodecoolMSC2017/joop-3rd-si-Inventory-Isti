package com.codecool;

import java.util.List;

public class StorageManager {

    private PersistentStore storage;

    public void addStorage(StorageCapable storage) {
        this.storage = (PersistentStore) storage;
    }

    public void addCDProduct(String name, int price, int tracks) {
        CDProduct cd = new CDProduct(name, price, tracks);
        storage.store(cd);
    }

    public void addBookProduct(String name, int price, int pages) {
        BookProduct book = new BookProduct(name, price, pages);
        storage.store(book);
    }

    public String listProducts() {
        return "";
    }

    public int getTotalProductPrice() {
        return 0;
    }
}
