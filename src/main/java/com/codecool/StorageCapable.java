package com.codecool;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface StorageCapable {

    public List<Product> getAllProduct();

    public void storeCDProduct(String name, int price, int tracks) throws ParserConfigurationException, TransformerException, SAXException, IOException;

    public void storeBookProduct(String name, int price, int pages) throws ParserConfigurationException, TransformerException, SAXException, IOException;
}
