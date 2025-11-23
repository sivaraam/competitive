package org.sivaraam.cassidoo.nov17_2025;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepeatedIntegersTest {
    @Test
    public void repeatedIntegersWithPositiveInput() {
        List<Integer> result = RepeatedIntegers.repeatedIntegers(4);
        assertEquals(10, result.size());
        assertTrue(result.containsAll(List.of(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)));
    }

    @Test
    public void repeatedIntegersWithZeroInput() {
        List<Integer> result = RepeatedIntegers.repeatedIntegers(0);
        assertTrue(result.isEmpty());
    }

    @Test
    public void repeatedIntegersWithNegativeInput() {
        List<Integer> result = RepeatedIntegers.repeatedIntegers(-5);
        assertTrue(result.isEmpty());
    }

    @Test
    public void repeatedIntegersWithLargeInput() {
        List<Integer> result = RepeatedIntegers.repeatedIntegers(1000);
        assertEquals(500500, result.size());
    }

    @Test
    public void repeatedIntegersWithOneAsInput() {
        List<Integer> result = RepeatedIntegers.repeatedIntegers(1);
        assertEquals(List.of(1), result);
    }

    @Test
    public void repeatedIntegersWithSevenAsInput() {
        List<Integer> result = RepeatedIntegers.repeatedIntegers(7);
        assertEquals(28 , result.size());
        assertTrue(result.containsAll(List.of(7, 7, 7, 7, 7, 7, 7, 1, 2, 2, 5, 5, 5, 5, 5, 3, 3, 3, 6, 6, 6, 6, 6, 6, 4, 4, 4, 4)));
    }

}
