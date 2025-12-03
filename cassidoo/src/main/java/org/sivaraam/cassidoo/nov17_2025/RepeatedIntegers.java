package org.sivaraam.cassidoo.nov17_2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given a positive integer n, write a function that
 * returns an array containing all integers from 1 to n,
 * where each integer i appears exactly i times in the
 * result. For example, 3 should appear 3 times, 5 should
 * appear 5 times, and so on. The order of the integers
 * in the output array does not matter.
 * <pre>
 *   > repeatedIntegers(4)
 *   > [1, 2, 2, 3, 3, 3, 4, 4, 4, 4]
 * </pre>
 * TODO: Try some other concurrent execution approach
 *  like via Threads / Executors.
 */
public class RepeatedIntegers {
    public static void main(String[] args) {
        int n = 4;
        System.out.println("Repeated integers: " + repeatedIntegers(n));
    }

    public static List<Integer> repeatedIntegers(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }

        // Thread safe list: https://www.javaspring.net/blog/java-thread-safe-list/
        var integers = Collections.synchronizedList(new ArrayList<Integer>());
        IntStream.rangeClosed(1, n)
                 .parallel()
                 .forEach(
                     i -> IntStream.rangeClosed(1, i)
                                   .forEach(j -> integers.add(i))
                 );

        return integers;
    }


}
