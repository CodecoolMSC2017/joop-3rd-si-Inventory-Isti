package com.codecool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

public abstract class Store implements StorageCapable {

    private void saveToXml(Product product) {
        File file = new File("src/java/com/codecool/data.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Document doc = null;
        if (file.exists()) {
            try {
                doc = docBuilder.parse(file);
            } catch (SAXException e) {
                e.printStackTrace();
                System.exit(-1);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
            Element rootElement = doc.getDocumentElement();
        } else {
            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Products");
            doc.appendChild(rootElement);
        }

        Element prod = doc.createElement("Product");
        prod.setAttribute("name", product.getName());
        prod.setAttribute("price", Integer.toString(product.getPrice()));
        if (product instanceof CDProduct) {
            prod.setAttribute("size", Integer.toString(((CDProduct) product).getNumOfTracks()));
            prod.setAttribute("type", "cd");
        } else if (product instanceof BookProduct) {
            prod.setAttribute("size", Integer.toString(((BookProduct) product).getNumOfPages()));
            prod.setAttribute("type", "book");
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/java/com/codecool/data.xml"));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    protected void storeProduct(Product product) {
        saveToXml(product);
    }

    protected void createProduct(String type, String name, int price, int size) {

    }

    public List<Product> loadProducts() {
        return new ArrayList<>();
    }

    public void store(Product product) {
        storeProduct(product);
    }
}
