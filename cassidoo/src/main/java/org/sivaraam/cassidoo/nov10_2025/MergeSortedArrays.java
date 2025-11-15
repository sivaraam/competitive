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
 */
public class MergeSortedArrays {
    public static void main(String[] args) {
        var sortedArray1 = new Integer[] { 1, 4, 7, 8, 0, 0, 0};
        var sortedArray2 = new Integer[] { 4, 5, 9 };
//        var sortedArray1 = new Integer[] { 1, 5, 7, 8, 0};
//        var sortedArray2 = new Integer[] { 4 };


        mergeArrays(sortedArray1, sortedArray2);
    }

    /*
     * Inserts the numbers from an already sorted array into another
     * already sorted array.
     * <p>
     * Takes an element (x) from sortedArray2. Finds the first largest element (y)
     * in sortedArray1 that's not empty and  bigger than x. Then shifts the element
     * by one spot to the right starting from y when it's not empty. Then inserts the
     * x into sortedArray1.
     */
    public static void mergeArrays(Integer[] sortedArray1, Integer[] sortedArray2) {
        int needle = 0;

        for (Integer elementToInsert : sortedArray2) {
            while (needle < sortedArray1.length && !isEmptyElement(sortedArray1[needle]) && sortedArray1[needle] <= elementToInsert) {
                needle++;
            }

            if (!isEmptyElement(sortedArray1[needle])) {
                shiftElementsToRight(sortedArray1, needle);
            }
            sortedArray1[needle] = elementToInsert;
            needle++;
        }

        System.out.println(Arrays.toString(sortedArray1));
    }

    private static void shiftElementsToRight(Integer[] sortedArray, Integer needle) {
        int toShift = sortedArray[needle];
        int next;

        for (int i = needle+1; i < sortedArray.length; i++) {
            next = sortedArray[i];

            sortedArray[i] = toShift;
            toShift = next;

            if (isEmptyElement(next)) {
                break;
            }
        }
    }

    private static boolean isEmptyElement(Integer nextElement) {
        return nextElement == null || nextElement == 0;
    }
}
