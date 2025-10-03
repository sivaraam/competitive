package org.sivaraam.cassidoo.sep29_2025;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigInteger;

public class HyperfactorialTest {

    @Test
    @DisplayName("Test hyperfactorial(0) should return 1")
    public void testHyperfactorialZero() {
        assertEquals(BigInteger.ONE, Hyperfactorial.hyperfactorial(0));
    }

    @Test
    @DisplayName("Test hyperfactorial(1) should return 1")
    public void testHyperfactorialOne() {
        assertEquals(BigInteger.ONE, Hyperfactorial.hyperfactorial(1));
    }

    @Test
    @DisplayName("Test hyperfactorial(2) should return 4")
    public void testHyperfactorialTwo() {
        assertEquals(BigInteger.valueOf(4), Hyperfactorial.hyperfactorial(2));
    }

    @Test
    @DisplayName("Test hyperfactorial(3) should return 108")
    public void testHyperfactorialThree() {
        assertEquals(BigInteger.valueOf(108), Hyperfactorial.hyperfactorial(3));
    }

    @Test
    @DisplayName("Test hyperfactorial(3) should return 27648")
    public void testHyperfactorialFour() {
        // Test with n=4: H(4) = 1^1 * 2^2 * 3^3 * 4^4 = 1 * 4 * 27 * 256 = 27648
        assertEquals(new BigInteger("27648"), Hyperfactorial.hyperfactorial(4));
    }

    @Test
    @DisplayName("Test hyperfactorial(5) should return 86400000")
    public void testHyperfactorialFive() {
        // Test with n=5: H(5) = 1^1 * 2^2 * 3^3 * 4^4 * 5^5 = 1 * 4 * 27 * 256 * 3125 = 86400000
        assertEquals(new BigInteger("86400000"), Hyperfactorial.hyperfactorial(5));
    }

    @Test
    @DisplayName("Test hyperfactorial(6) should return 4031078400000")
    public void testHyperfactorialSix() {
        assertEquals(new BigInteger("4031078400000"), Hyperfactorial.hyperfactorial(6));
    }

    @Test
    @DisplayName("Test hyperfactorial(7) should return 3319766398771200000")
    public void testHyperfactorialSeven() {
        assertEquals(new BigInteger("3319766398771200000"), Hyperfactorial.hyperfactorial(7));
    }

    @Test
    @DisplayName("Test hyperfactorial with negative input should throw IllegalArgumentException")
    public void testHyperfactorialNegativeInput() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> Hyperfactorial.hyperfactorial(-1)
        );
        assertEquals("n must be non-negative", exception.getMessage());
    }

    @Test
    @DisplayName("Test hyperfactorial consistency - same input should give same output")
    public void testHyperfactorialConsistency() {
        BigInteger result1 = Hyperfactorial.hyperfactorial(5);
        BigInteger result2 = Hyperfactorial.hyperfactorial(5);
        assertEquals(result1, result2);
    }

    @Test
    @DisplayName("Test hyperfactorial with boundary values")
    public void testHyperfactorialHugeValues() {
        // Test with the smallest boundary value (n = 0)
        assertEquals(BigInteger.ONE, Hyperfactorial.hyperfactorial(0), "Boundary test for n=0 failed");

        // Test with a reasonably large boundary value (e.g., n = 10)
        // H(10) = 1^1 * 2^2 * 3^3 * ... * 10^10
        assertEquals(new BigInteger("215779412229418562091680268288000000000000000"), Hyperfactorial.hyperfactorial(10), "Boundary test for n=10 failed");

        // Test with a very large value to ensure no overflow or performance issues
        // Note: Adjust the value of n based on system capabilities
        assertEquals(new BigInteger("100763737898388614355527688003578055557189497372863237259215112020332401543021781214113113511884621846316264239637778832941245615057050789338689848331811015513291187346373172474675200000000000000000000000000000000000000000000000000"), Hyperfactorial.hyperfactorial(20), "Boundary test for n=20 failed");

    }
}
