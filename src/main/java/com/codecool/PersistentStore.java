package com.codecool;

import java.util.List;

public class PersistentStore extends Store {

    public List<Product> getAllProduct() {
        return null;
    }

    public void storeCDProduct(String name, int price, int tracks) {
        CDProduct cd = new CDProduct(name, price, tracks);
        super.store(cd);
    }

    public void storeBookProduct(String name, int price, int pages) {
        BookProduct book = new BookProduct(name, price, pages);
        super.store(book);
    }
}
