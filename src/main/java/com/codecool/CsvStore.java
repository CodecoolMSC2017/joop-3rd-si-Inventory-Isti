package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvStore implements StorageCapable {

    private List<Product> products = readCsvFile();

    private List<Product> readCsvFile() {
        List<Product> products = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/products.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] lineParts = line.split(",");
                if (lineParts[3].equals("book")) {
                    products.add(new BookProduct(lineParts[0], Integer.parseInt(lineParts[1]), Integer.parseInt(lineParts[2])));
                } else if (lineParts[3].equals("cd")) {
                    products.add(new CDProduct(lineParts[0], Integer.parseInt(lineParts[1]), Integer.parseInt(lineParts[2])));
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getAllProduct() {
        return products;
    }

    public void storeCDProduct(String name, int price, int tracks) {
        CDProduct cd = new CDProduct(name, price, tracks);
        products.add(cd);
        saveToCsvFile(cd);
    }

    public void storeBookProduct(String name, int price, int pages) {
        BookProduct book = new BookProduct(name, price, pages);
        products.add(book);
        saveToCsvFile(book);
    }

    private void saveToCsvFile(Product product) {
        StringBuilder sb = new StringBuilder(product.getName() + ",");
        sb.append(product.getPrice() + ",");
        if (product instanceof CDProduct) {
            CDProduct cd = (CDProduct) product;
            sb.append(cd.getNumOfTracks() + ",");
            sb.append("cd\n");
        } else if (product instanceof BookProduct) {
            BookProduct book = (BookProduct) product;
            sb.append(book.getNumOfPages() + ",");
            sb.append("book\n");
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/products.csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
