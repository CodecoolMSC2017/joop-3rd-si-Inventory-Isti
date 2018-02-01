package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CDProductTest {

    CDProduct cd;

    @BeforeEach
    void setUp() {
        cd = new CDProduct("randomName", 6, 31);
    }

    @Test
    void getName() {
        assertEquals("randomName", cd.getName());
    }

    @Test
    void getPrice() {
        assertEquals(6, cd.getPrice());
    }

    @Test
    void getNumOfTracks() {
        assertEquals(31, cd.getNumOfTracks());
    }

}