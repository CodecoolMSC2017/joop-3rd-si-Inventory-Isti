package com.codecool;

import java.util.Scanner;

public class Main {

    private static Scanner userInput = new Scanner(System.in);
    private static StorageManager store = new StorageManager();

    public static void main(String[] args) {
        store.addStorage(new PersistentStore());
        while (true) {
            System.out.println("Commands: :add :list :exit");
            String input = userInput.nextLine().toLowerCase();
            if (input.equals(":exit")) {
                break;
            } else if (input.equals(":add")) {
                add();
            } else if (input.equals(":list")) {
                list();
            }
        }
    }

    private static void add() {
        System.out.println("Type (cd or book): ");
        String type = userInput.nextLine().toLowerCase();
        System.out.println("Name: ");
        String name = userInput.nextLine();
        System.out.println("Price: ");
        int price = Integer.parseInt(userInput.nextLine());
        System.out.println("Size: ");
        int size = Integer.parseInt(userInput.nextLine());
        try {
            if (type.equals("cd")) {
                store.addCDProduct(name, price, size);
            } else if (type.equals("book")) {
                store.addBookProduct(name, price, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void list() {
        System.out.println(store.listProducts());
    }
}
