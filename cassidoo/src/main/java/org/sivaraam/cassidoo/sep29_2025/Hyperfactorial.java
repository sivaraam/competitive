package org.sivaraam.cassidoo.sep29_2025;

import java.math.BigInteger;

/**
 * Given the non-negative integer n , output the value of its hyperfactorial.
 * Don't worry about outputs exceeding your language's integer limit.
 * <p>
 * Hyperfactorial ref: https://mathworld.wolfram.com/Hyperfactorial.html?utm_source=cassidoo&utm_medium=email&utm_campaign=i-recommend-the-freedom-that-comes-from-asking
 * <p>
 * Examples:
 * <p>
 * > hyperfactorial(0)
 * > 1
 * <p>
 * > hyperfactorial(2)
 * > 4
 * <p>
 * > hyperfactorial(3)
 * > 108
 * <p>
 * > hyperfactorial(7)
 * > 3319766398771200000
 * <p>
 * Miscellaneous:
 * Reference that we need to grok (yet): https://www.baeldung.com/java-biginteger
 */
public class Hyperfactorial {
    public static void main(String[] args) {
        System.out.println("Hyperfactorial of 7: " + hyperfactorial(7));
    }

    public static BigInteger hyperfactorial(int n) {
        // Suggested by Claude.
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i).pow(i));
        }

        return result;
    }
}
