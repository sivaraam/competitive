package org.sivaraam.cassidoo.sep08_2025;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NeighbourSumArrayTest {

    @Test
    void testEmptyArray() {
        List<Double> nums = Collections.emptyList();
        assertEquals(0, NeighbourSumArray.neighbourSum(nums));
    }

    @Test
    void testSingleElementArray() {
        List<Double> nums = Collections.singletonList(1d);
        assertEquals(1, NeighbourSumArray.neighbourSum(nums));
    }

    @Test
    void testTwoElementArray() {
        List<Double> nums = Arrays.asList(1d, 4d);
        assertEquals(10, NeighbourSumArray.neighbourSum(nums));
    }

    @Test
    void testThreeElementArray() {
        List<Double> nums = Arrays.asList(1d, 4d, 7d);
        assertEquals(28, NeighbourSumArray.neighbourSum(nums));
    }

    @Test
    void testNegativeNumbers() {
        List<Double> nums = Arrays.asList(-1d, -2d, -3d);
        assertEquals(-14, NeighbourSumArray.neighbourSum(nums));
    }

    @Test
    void testDecimalNumbers() {
        List<Double> nums = Arrays.asList(0.1, 0.2, 0.3);
        assertEquals(1.4, NeighbourSumArray.neighbourSum(nums), 0.0001);
    }

    @Test
    void testLargeNumbers() {
        List<Double> nums = Arrays.asList(1d, -20d, 300d, -4000d, 50000d, -600000d, 7000000d);
        assertEquals(12338842, NeighbourSumArray.neighbourSum(nums));
    }

    @Test
    void testAllZeros() {
        List<Double> nums = Arrays.asList(0d, 0d, 0d, 0d);
        assertEquals(0, NeighbourSumArray.neighbourSum(nums));
    }

    @Test
    void testMixedNumbers() {
        List<Double> nums = Arrays.asList(1d, 0d, -1d, 2d, -2d);
        assertEquals(1, NeighbourSumArray.neighbourSum(nums));
    }
}