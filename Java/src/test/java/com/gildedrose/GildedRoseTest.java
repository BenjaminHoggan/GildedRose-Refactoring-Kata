package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private GildedRose sut;
    private Item[] items;

    @BeforeEach
    void setUp() {
        final String itemName = "someString";
        final int itemSellIn = 1000;
        final int itemQuality = 0;

        items = new Item[] {
            new Item(itemName, itemSellIn, itemQuality)
        };

        sut = new GildedRose(items);
    }

    @Test
    void foo() {
        // When
        sut.updateQuality();

        // Then
        assertEquals("someString", sut.items[0].name);
    }

    @Test
    void Does_not_change_quality_if_quality_is_zero() {
        // Given
        // When
        sut.updateQuality();

        // Then
        assertEquals(0, sut.items[0].quality);
    }

}
