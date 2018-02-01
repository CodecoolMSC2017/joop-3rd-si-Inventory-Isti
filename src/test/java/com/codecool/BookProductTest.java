package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookProductTest {

    BookProduct book;

    @BeforeEach
    void setUp() {
        book = new BookProduct("randomBookName", 41, 745);
    }

    @Test
    void getName() {
        assertEquals("randomBookName", book.getName());
    }

    @Test
    void getPrice() {
        assertEquals(41, book.getPrice());
    }

    @Test
    void getNumOfPages() {
        assertEquals(745, book.getNumOfPages());
    }
}