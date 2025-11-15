package org.sivaraam.cassidoo.nov10_2025;

import java.util.Arrays;

/**
 * You are given two sorted arrays, a and b, where a has a large enough
 * size buffer at the end to hold b (which can be spaces, zeroes, or
 * nulls). Write a function to merge b into a in sorted order.
 *
 * <pre>
 *   let a = [1, 3, 5, 0, 0, 0];
 *   let b = [2, 4, 6];
 *
 *   > merge(a, b)
 *   > [1, 2, 3, 4, 5, 6]
 * </pre>
 *
 * Note: This is a sub-optimal solution hacked via brute force and some
 * incorrect mental state. It works for some cases but fails rest.
 * Retaining for reference purposes.
 * Lesson learnt: Don't rush through a solution assuming the problem
 * to be trivial!
 */
public class MergeSortedArraysSubOptimal {
    public static void main(String[] args) {
        var sortedArray1 = new Integer[] { 1, 4, 7, 8, 0, 0, 0};
        var sortedArray2 = new Integer[] { 1, 2, 7 };

        mergeArrays(sortedArray1, sortedArray2);
    }

    /*
     * Inserts the numbers from an already sorted array into another
     * already sorted array.
     * <p>
     * Takes an element (x) from sortedArray2. Finds the first largest element (y)
     * in sortedArray1 that's bigger than x. Then shifts the element by one spot
     * to the right starting from y. Then inserts the x into sortedArray1.
     * <p>
     * If no such large element was found, skips the shifting and inserts the element
     * at the last.
     */
    public static void mergeArrays(Integer[] sortedArray1, Integer[] sortedArray2) {
        int needle = 0;

        for (int i = 0; i < sortedArray2.length; i++) {
            while (needle < sortedArray1.length - 1 && sortedArray1[needle] <= sortedArray2[i]) {
                needle++;
            }

            if (needle != sortedArray1.length - 1) {
                shiftElementsToRight(sortedArray1, needle);
            }
            sortedArray1[needle] = sortedArray2[i];
            needle++;
        }

        System.out.println(Arrays.toString(sortedArray1));
    }

    private static void shiftElementsToRight(Integer[] sortedArray, Integer needle) {
        Integer toShift = sortedArray[needle];
        Integer nextElement = sortedArray[needle+1];

        while (true) {
            sortedArray[needle+1] = toShift;

            if (isEmptyElement(nextElement)) {
                break;
            }
            else {
                toShift = nextElement;
                needle++;
                if (needle < sortedArray.length - 1) {
                    nextElement = sortedArray[needle+1];
                }
                else {
                    break;
                }
            }
        }
    }

    private static boolean isEmptyElement(Integer nextElement) {
        return nextElement == null || nextElement == 0;
    }
}
