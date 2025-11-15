package org.sivaraam.cassidoo.nov10_2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortedArraysTest {
    @Test
    public void mergeArraysWithEmptySecondArrayAndEmptySlotsInFirstArray() {
        Integer[] sortedArray1 = {1, 2, 3, 0, 0, 0};
        Integer[] sortedArray2 = {};
        MergeSortedArrays.mergeArrays(sortedArray1, sortedArray2);
        assertArrayEquals(new Integer[]{1, 2, 3, 0, 0, 0}, sortedArray1);
    }

    @Test
    public void mergeArraysWithSecondArrayHavingSmallerElements() {
        Integer[] sortedArray1 = {5, 6, 7, 0, 0, 0};
        Integer[] sortedArray2 = {1, 2, 3};
        MergeSortedArrays.mergeArrays(sortedArray1, sortedArray2);
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 7}, sortedArray1);
    }

    @Test
    public void mergeArraysWithSecondArrayHavingLargerElements() {
        Integer[] sortedArray1 = {1, 2, 3, 0, 0, 0};
        Integer[] sortedArray2 = {4, 5, 6};
        MergeSortedArrays.mergeArrays(sortedArray1, sortedArray2);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6}, sortedArray1);
    }

    @Test
    public void mergeArraysWithInterleavedElements() {
        Integer[] sortedArray1 = {1, 4, 7, 0, 0, 0};
        Integer[] sortedArray2 = {2, 5, 6};
        MergeSortedArrays.mergeArrays(sortedArray1, sortedArray2);
        assertArrayEquals(new Integer[]{1, 2, 4, 5, 6, 7}, sortedArray1);
    }

    @Test
    public void mergeArraysWithNegativeAndPositiveNumbers() {
        Integer[] sortedArray1 = {-3, -1, 2, 0, 0, 0};
        Integer[] sortedArray2 = {-2, 0, 3};
        MergeSortedArrays.mergeArrays(sortedArray1, sortedArray2);
        assertArrayEquals(new Integer[]{-3, -2, -1, 0, 2, 3}, sortedArray1);
    }

    @Test
    public void mergeArraysWithDuplicateValues() {
        Integer[] sortedArray1 = {1, 2, 3, 0, 0, 0};
        Integer[] sortedArray2 = {2, 3, 4};
        MergeSortedArrays.mergeArrays(sortedArray1, sortedArray2);
        assertArrayEquals(new Integer[]{1, 2, 2, 3, 3, 4}, sortedArray1);
    }

    @Test
    public void mergeArraysWithAllZeroesInFirstArray() {
        Integer[] sortedArray1 = {0, 0, 0, 0, 0, 0};
        Integer[] sortedArray2 = {1, 2, 3};
        MergeSortedArrays.mergeArrays(sortedArray1, sortedArray2);
        assertArrayEquals(new Integer[]{1, 2, 3, 0, 0, 0}, sortedArray1);
    }
}
