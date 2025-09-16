package org.sivaraam.cassidoo.self;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * A variant of the problem covered in {@link org.sivaraam.cassidoo.sep08_2025.NeighbourSumArray}
 * with a special focus on the precision.
 * <p>
 * For an array of decimal numbers, generate an array where for every element,
 * all neighboring elements are added to itself, and return the sum
 * of that array. The result must be precise to at least 100 decimal places.
 * <p>
 * Key precision challenges:
 * - Very small numbers that might underflow
 * - Large differences in magnitude that cause precision loss
 * - Repeated operations that accumulate floating-point errors
 * - Numbers near the limits of double precision
 * <p>
 * Test Cases:
 * <ol>
 *  <li>
 *      Basic precision test: [0.1, 0.2, 0.3] -> 1.4000000000
 *  </li>
 *  <li>
 *      Very small numbers: [1e-15, 2e-15, 3e-15] -> 1.4e-14
 *  </li>
 *  <li>
 *      Large magnitude differences: [1e-15, 1e15, 1e-15] -> 2.000000000000001e15
 *  </li>
 *  <li>
 *    Catastrophic cancellation risk: [1.0000000000000002, -1.0, 1.0000000000000002] -> 2.0000000000000004
 *  </li>
 * </ol>
 * ... and more such scenarios in the corresponding test class.
 */
public class NeighbourSumArray_precision {
    public static void main(String[] args) {
        List<BigDecimal> arr = Arrays.asList(
                new BigDecimal("1e-10"),
                new BigDecimal("0.5"),
                new BigDecimal("1e-10")
        );

        System.out.println("Sum of the array: " + neighbourSum(arr).toString());
    }

    static BigDecimal neighbourSum(List<BigDecimal> nums) {
        BigDecimal sum = BigDecimal.ZERO;
//        MathContext context = new MathContext(450, RoundingMode.HALF_UP);

        if (nums.size() == 1) {
            sum = sum.add(nums.get(0));
        }
        else {
            for (int i = 0; i < nums.size(); i++) {
                BigDecimal item = nums.get(i);
                if (i == 0 || i == nums.size() - 1) {
                    /*
                     * The terminal elements of the list are added twice
                     * during the summation. Once as the terminal element itself.
                     * Another time when it is considered as a neighbour
                     * of its only ... neighbour.
                     */
                    sum = sum.add(item.multiply(BigDecimal.valueOf(2L)));
//                    sum = sum.add(item.multiply(BigDecimal.valueOf(2L), context), context);
                }
                else {
                    /*
                     * The elements in between the list are added thrice.
                     * Once as the element itself.
                     * Once as the neighbour of its left neighbour.
                     * Once as the neighbour of its right neighbour.
                     */
                    sum = sum.add(item.multiply(BigDecimal.valueOf(3L)));
//                    sum = sum.add(item.multiply(BigDecimal.valueOf(3L), context), context);
                }
            }
        }

        return sum;
    }
}
