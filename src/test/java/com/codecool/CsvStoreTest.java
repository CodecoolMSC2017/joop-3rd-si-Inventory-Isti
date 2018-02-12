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
        assertEquals(2, store.getAllProduct().size());
        BookProduct book = (BookProduct) store.getAllProduct().get(0);

        assertEquals("valami", book.getName());
        assertEquals(200, book.getPrice());
        assertEquals(12, book.getNumOfPages());
    }

    @Test
    void storeCDProduct() {
        store.storeCDProduct("testCD", 192, 54);
        CDProduct cd = (CDProduct) store.getAllProduct().get(2);

        assertEquals("testCD", cd.getName());
        assertEquals(192, cd.getPrice());
        assertEquals(54, cd.getNumOfTracks());
    }

    @Test
    void storeBookProduct() {
        store.storeBookProduct("testBook", 152, 24);
        BookProduct book = (BookProduct) store.getAllProduct().get(1);

        assertEquals("testBook", book.getName());
        assertEquals(152, book.getPrice());
        assertEquals(24, book.getNumOfPages());
    }
}