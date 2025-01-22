package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void Does_not_change_quality_if_quality_is_zero() {
        // Given
        final String itemName = "someString";
        final int itemSellIn = 1000;
        final int itemQuality = 0;
        final Item[] items = new Item[] {
            new Item(itemName, itemSellIn, itemQuality)
        };
        GildedRose app = new GildedRose(items);

        // When
        app.updateQuality();

        // Then
        assertEquals(0, app.items[0].quality);
    }

}
