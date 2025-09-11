package org.sivaraam.cassidoo.sep08_2025;

import java.util.Arrays;
import java.util.List;

/**
 * For an array of numbers, generate an array where for every element,
 * all neighboring elements are added to itself, and return the sum
 * of that array.
 * <p>
 * Examples:
 * <p>
 * []               -> 0
 * [1]              -> 1
 * [1, 4]           -> 10 // (1+4 + 4+1)
 * [1, 4, 7]        -> 28
 * [1, 4, 7, 10]    -> 55
 * [-1, -2, -3]     -> -14
 * [0.1, 0.2, 0.3]  -> 1.4
 * [1,-20,300,-4000,50000,-600000,7000000] -> 12338842
 */
public class NeighbourSumArray {
    public static void main(String[] args) {
        List<Double> arr = Arrays.asList(
                1d, -20d, 300d, -4000d, 50000d, -600000d, 7000000d
        );


        System.out.println("Sum of the array: " + neighbourSum(arr));
    }

    static double neighbourSum(List<Double> nums) {
        double sum = 0d;

        if (nums.size() == 1) {
            sum = nums.get(0);
        }
        else {
            for (int i = 0; i < nums.size(); i++) {
                double item = nums.get(i);
                if (i == 0 || i == nums.size() - 1) {
                    /*
                     * The terminal elements of the list are added twice
                     * during the summation. Once as the terminal element itself.
                     * Another time when it is considered as a neighbour
                     * of its only ... neighbour.
                     */
                    sum += item * 2;
                }
                else {
                    /*
                     * The elements in between the list are added thrice.
                     * Once as the element itself.
                     * Once as the neighbour of its left neighbour.
                     * Once as the neighbour of its right neighbour.
                     */
                    sum += item * 3;
                }
            }
        }

        return sum;
    }
}
