package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    void Can_decrease_quality_by_1_when_quality_is_greater_than_0() {
        // Given
        items[0].quality = 1;

        // When
        sut.updateQuality();

        // Then
        assertEquals(0, sut.items[0].quality);
    }

    @Test
    void Can_not_change_a_positive_quality_when_name_is_sulfuras() {
        // Given
        items[0].quality = 1;
        items[0].name = "Sulfuras, Hand of Ragnaros";

        // When
        sut.updateQuality();

        // Then
        assertEquals(1, sut.items[0].quality);
    }

    @Test
    void Can_increase_quality_of_aged_brie_by_1() {
        // Given
        items[0].name = "Aged Brie";

        // When
        sut.updateQuality();

        // Then
        assertEquals(1, sut.items[0].quality);
    }

    @Test
    void Can_not_increase_quality_of_aged_brie_when_quality_already_50() {
        // Given
        items[0].quality = 50;
        items[0].name = "Aged Brie";

        // When
        sut.updateQuality();

        // Then
        assertEquals(50, sut.items[0].quality);
    }

    @Test
    void Can_increase_quality_of_backstage_pass_by_1_when_sell_in_over_10() {
        // Given
        items[0].name = "Backstage passes to a TAFKAL80ETC concert";

        // When
        sut.updateQuality();

        // Then
        assertEquals(1, sut.items[0].quality);
    }

    @Test
    void Can_increase_quality_of_backstage_pass_by_2_when_sell_in_between_5_and_11_exclusive() {
        // Given
        items[0].name = "Backstage passes to a TAFKAL80ETC concert";
        items[0].sellIn = 6;

        // When
        sut.updateQuality();

        // Then
        assertEquals(2, sut.items[0].quality);
    }

    @Test
    void Can_increase_quality_of_backstage_pass_by_3_when_sell_in_below_6() {
        // Given
        items[0].name = "Backstage passes to a TAFKAL80ETC concert";
        items[0].sellIn = 5;

        // When
        sut.updateQuality();

        // Then
        assertEquals(3, sut.items[0].quality);
    }

    @ParameterizedTest
    @CsvSource({"5,48", "5,49", "6,49"})
    void Can_increase_quality_of_backstage_pass_to_a_maximum_of_50(
        final int sellIn,
        final int quality
    ) {
        // Given
        items[0].name = "Backstage passes to a TAFKAL80ETC concert";
        items[0].sellIn = sellIn;
        items[0].quality = quality;

        // When
        sut.updateQuality();

        // Then
        assertEquals(50, sut.items[0].quality);
    }

    @Test
    void Can_decrease_sell_in_by_1() {
        // Given
        items[0].sellIn = 1;

        // When
        sut.updateQuality();

        // Then
        assertEquals(0, sut.items[0].sellIn);
    }

    @Test
    void Can_not_decrease_sell_in_when_name_is_sulfuras() {
        // Given
        items[0].name = "Sulfuras, Hand of Ragnaros";
        items[0].sellIn = 1;

        // When
        sut.updateQuality();

        // Then
        assertEquals(1, sut.items[0].sellIn);
    }

    @Test
    void Can_decrease_quality_by_2_when_sell_in_is_0_or_less() {
        // Given
        items[0].quality = 10;
        items[0].sellIn = 0;

        // When
        sut.updateQuality();

        // Then
        assertEquals(8, sut.items[0].quality);
    }
}
