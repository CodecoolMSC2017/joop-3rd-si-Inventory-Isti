package com.codecool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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

    private void saveToXml(Product product) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        File file = new File("data.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = null;
        Element rootElement;
        if (file.exists()) {
            doc = docBuilder.parse(file);
            rootElement = doc.getDocumentElement();
        } else {
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("Products");
            doc.appendChild(rootElement);
        }

        Element prod = doc.createElement("Product");
        rootElement.appendChild(prod);
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
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }

    protected void storeProduct(Product product) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        saveToXml(product);
    }

    protected Product createProduct(String type, String name, int price, int size) {
        if (type.equals("cd")) {
            return new CDProduct(name, price, size);
        } else if (type.equals("book")) {
            return new BookProduct(name, price, size);
        }
        return null;
    }

    public List<Product> loadProducts() {
        File file = new File("data.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Document doc = null;
        try {
            doc = docBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        NodeList nodeList = doc.getElementsByTagName("Product");
        ArrayList<Product> productList = new ArrayList<>();
        String type;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            type = element.getAttribute("type");
            if (type.equals("cd")) {
                productList.add(new CDProduct(element.getAttribute("name"),
                        Integer.parseInt(element.getAttribute("price")),
                        Integer.parseInt(element.getAttribute("size"))));
            } else if (type.equals("book")) {
                productList.add(new BookProduct(element.getAttribute("name"),
                        Integer.parseInt(element.getAttribute("price")),
                        Integer.parseInt(element.getAttribute("size"))));
            }
        }
        return productList;
    }

    public void store(Product product) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        storeProduct(product);
    }
}
