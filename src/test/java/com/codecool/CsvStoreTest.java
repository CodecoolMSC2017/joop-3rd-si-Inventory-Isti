package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvStoreTest {

    private CsvStore store;

    @BeforeEach
    void setUp() {
        store = new CsvStore();
    }

    @Test
    void getAllProduct() {
        assertEquals(store.getAllProduct().size(), 1);
        BookProduct book = (BookProduct) store.getAllProduct().get(0);

        assertEquals(book.getName(), "valami");
        assertEquals(book.getPrice(), 200);
        assertEquals(book.getNumOfPages(), 12);
    }

    @Test
    void storeCDProduct() {
        store.storeCDProduct("testCD", 192, 54);
        CDProduct cd = (CDProduct) store.getAllProduct().get(1);

        assertEquals(cd.getName(), "testCD");
        assertEquals(cd.getPrice(), 192);
        assertEquals(cd.getNumOfTracks(), 54);
    }

    @Test
    void storeBookProduct() {
        store.storeBookProduct("testBook", 152, 24);
        BookProduct book = (BookProduct) store.getAllProduct().get(1);

        assertEquals(book.getName(), "testBook");
        assertEquals(book.getPrice(), 152);
        assertEquals(book.getNumOfPages(), 24);
    }
}