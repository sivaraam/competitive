package org.sivaraam.cassidoo.oct27_2025;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScareCrowGreedyPlacementTest {

    @Test
    void returnsCorrectPositionsForFieldWithGaps() {
        var field = new int[]{1, 0, 1, 1, 0, 1};
        var k = 3;
        var expected = List.of(1, 4);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void returnsCorrectPositionsForFieldWithAllOnes() {
        var field = new int[]{1, 1, 1, 1, 1};
        var k = 1;
        var expected = List.of(0, 1, 2, 3, 4);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void returnsEmptyListForEmptyField() {
        var field = new int[]{};
        var k = 3;
        var expected = List.of();
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void returnsEmptyListForFieldWithNoOnes() {
        var field = new int[]{0, 0, 0, 0};
        var k = 3;
        var expected = List.of();
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void handlesSingleOneInField() {
        var field = new int[]{0, 0, 1, 0, 0};
        var k = 3;
        var expected = List.of(3);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void handlesFieldWithOneAtStart() {
        var field = new int[]{1, 0, 0, 0};
        var k = 3;
        var expected = List.of(1);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void handlesFieldWithOneAtEnd() {
        var field = new int[]{0, 0, 0, 1};
        var k = 3;
        var expected = List.of(3);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void handlesFieldWithLargeK() {
        var field = new int[]{1, 0, 1, 0, 1};
        var k = 5;
        var expected = List.of(2);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void handlesFieldWithEvenK() {
        var field = new int[]{1, 0, 1, 0, 1};
        var k = 2;
        var expected = List.of(1, 3, 4);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void handlesFieldWithEvenK2() {
        var field = new int[]{1, 1, 1, 0, 1, 1};
        var k = 2;
        var expected = List.of(1, 3, 5);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }

    @Test
    void handlesFieldWithKGreaterThanFieldLength() {
        var field = new int[]{1, 1, 1};
        var k = 7;
        var expected = List.of(2);
        assertEquals(expected, ScareCrowGreedyPlacement.placeScarecrows(field, k));
    }
}

