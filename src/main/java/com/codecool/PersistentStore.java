package com.codecool;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class PersistentStore extends Store {

    public List<Product> getAllProduct() {
        return super.loadProducts();
    }

    public void storeCDProduct(String name, int price, int tracks) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        Product cd = createProduct("cd", name, price, tracks);
        super.store(cd);
    }

    public void storeBookProduct(String name, int price, int pages) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        Product book = createProduct("book", name, price, pages);
        super.store(book);
    }
}
