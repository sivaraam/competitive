package org.sivaraam.cassidoo.oct27_2025;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScareCrowPlacementTest {

    @Test
    void returnsCorrectPositionsForFieldWithGaps() {
        var field = List.of(1, 0, 1, 1, 0, 1);
        var k = 3;
        var expected = Map.of(1, 2, 4, 2);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void returnsCorrectPositionsForFieldWithAllOnes() {
        var field = List.of(1, 1, 1, 1, 1);
        var k = 1;
        var expected = Map.of(0, 1, 1, 1, 2, 1, 3, 1, 4, 1);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void returnsEmptyMapForEmptyField() {
        var field = List.<Integer>of();
        var k = 3;
        var expected = Map.of();
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void returnsEmptyMapForFieldWithNoOnes() {
        var field = List.of(0, 0, 0, 0);
        var k = 3;
        var expected = Map.of();
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void handlesSingleOneInField() {
        var field = List.of(0, 0, 1, 0, 0);
        var k = 3;
        var expected = Map.of(2, 1);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void handlesFieldWithOneAtStart() {
        var field = List.of(1, 0, 0, 0);
        var k = 3;
        var expected = Map.of(0, 1);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void handlesFieldWithOneAtEnd() {
        var field = List.of(0, 0, 0, 1);
        var k = 3;
        var expected = Map.of(3, 1);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void handlesFieldWithLargeK() {
        var field = List.of(1, 0, 1, 0, 1);
        var k = 5;
        var expected = Map.of(2, 3);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void handlesFieldWithEvenK() {
        var field = List.of(1, 0, 1, 0, 1);
        var k = 2;
        var expected = Map.of(0, 1, 2, 1, 4, 1);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }


    @Test
    void handlesFieldWithEvenK2() {
        var field = List.of(1, 1, 1, 0, 1, 1);
        var k = 2;
        var expected = Map.of(0, 2, 2, 1, 4, 2);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }

    @Test
    void handlesFieldWithKGreaterThanFieldLength() {
        var field = List.of(1, 1, 1);
        var k = 7;
        var expected = Map.of(1, 3);
        assertEquals(expected, ScareCrowPlacement.placeScareCrows(field, k));
    }
}

