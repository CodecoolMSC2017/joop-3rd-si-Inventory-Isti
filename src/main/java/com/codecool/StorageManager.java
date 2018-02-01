package com.codecool;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class StorageManager {

    private PersistentStore storage;

    public void addStorage(StorageCapable storage) {
        this.storage = (PersistentStore) storage;
    }

    public void addCDProduct(String name, int price, int tracks) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        CDProduct cd = new CDProduct(name, price, tracks);
        storage.store(cd);
    }

    public void addBookProduct(String name, int price, int pages) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        BookProduct book = new BookProduct(name, price, pages);
        storage.store(book);
    }

    public String listProducts() {
        StringBuilder result = new StringBuilder();
        List<Product> products = storage.getAllProduct();

        for (Product product : products) {
            result.append("\nName: " + product.getName() + "\n");
            result.append("Price: " + product.getPrice() + "\n");
        }
        return result.toString();
    }

    public int getTotalProductPrice() {
        List<Product> products = storage.getAllProduct();
        int sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }
}
