package org.sivaraam.cassidoo.sep08_2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * This class has some test cases to test the precision scenario
 * rigorously. Test cases generated with help of Claude.
 * <p>
 * This specific class has the term 'naive' in its name as it tries
 * to evaluate the naive approach of directly adding the decimal without
 * any additional handling and confirming that it fails as expected.
 */
public class NeighbourSumArray_precision_naive_Test {

    private static final double PRECISION_TOLERANCE = 1e-10;

    @Test
    public void testBasicPrecision() {
        List<Double> input = Arrays.asList(0.1, 0.2, 0.3);
        double expected = 1.4;
        double actual = solution(input);
        assertEquals(expected, actual, PRECISION_TOLERANCE,
                "Basic decimal precision test failed");
    }

    @Test
    public void testVerySmallNumbers() {
        List<Double> input = Arrays.asList(1e-15, 2e-15, 3e-15);
        double expected = 1.4e-14;
        double actual = solution(input);
        assertEquals(expected, actual, 1e-24,
                "Very small numbers precision test failed");
    }

    @Test
    public void testMixedVerySmallAndNormal() {
        List<Double> input = Arrays.asList(1e-10, 0.5, 1e-10);
        double expected = 1.5000000004;
        double actual = solution(input);
        assertEquals(expected, actual, 1e-12,
                "Mixed small and normal numbers test failed");
    }

    @Test
    public void testLargeMagnitudeDifferences() {
        List<Double> input = Arrays.asList(1e-15, 1e15, 1e-15);
        double expected = 3000000000000000.000000000000004;
        double actual = solution(input);
        assertEquals(expected, actual, 1e5,
                "Large magnitude differences test failed");
    }

    @Test
    public void testPrecisionLossScenario() {
        List<Double> input = Arrays.asList(0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1);
        double expected = 2.8;
        double actual = solution(input);
        assertEquals(expected, actual, PRECISION_TOLERANCE,
                "Repeated 0.1 precision loss test failed");
    }

    @Test
    public void testNumbersThatDontSumCleanlyInBinary() {
        List<Double> input = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9);
        double expected = 12.5;
        double actual = solution(input);
        assertEquals(expected, actual, PRECISION_TOLERANCE,
                "Binary representation precision test failed");
    }

    @Test
    public void testAlternatingTinyAndHuge() {
        List<Double> input = Arrays.asList(1e-16, 1e16, 1e-16, 1e16, 1e-16);
        double expected = 60000000000000000.0000000000000007;
        double actual = solution(input);
        assertEquals(expected, actual, 1e6,
                "Alternating tiny and huge numbers test failed");
    }

    @Test
    public void testNearDoublePrecisionLimits() {
        List<Double> input = Arrays.asList(1.7976931348623157e308, 1e-100, -1.7976931348623157e308);
        double expected = 3e-100;
        double actual = solution(input);
        assertEquals(expected, actual, 1e-110,
                "Near double precision limits test failed");
    }

    @Test
    public void testCatastrophicCancellation() {
        List<Double> input = Arrays.asList(1.0000000000000002, -1.0, 1.0000000000000002);
        double expected = 1.0000000000000008;
        double actual = solution(input);
        assertEquals(expected, actual, 1e-15,
                "Catastrophic cancellation test failed");
    }

    @Test
    public void testExtremePrecisionChallenge() {
        List<Double> input = Arrays.asList(
                0.123456789012345,
                -0.123456789012344,
                0.000000000000001,
                1e-14,
                -1e-14
        );
        double expected = -.123456789012329;
        double actual = solution(input);
        assertEquals(expected, actual, 1e-16,
                "Extreme precision challenge test failed");
    }

    @Test
    public void testAccumulatedRoundingErrors() {
        // Create list with values that accumulate rounding errors
        List<Double> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add(0.01);
        }
        double expected = 2.98; // Theoretical exact value
        double actual = solution(input);
        assertEquals(expected, actual, 1e-10,
                "Accumulated rounding errors test failed");
    }

    @Test
    public void testNegativePrecisionScenario() {
        List<Double> input = Arrays.asList(-0.1, -0.2, -0.3, -0.4, -0.5);
        double expected = -3.9;
        double actual = solution(input);
        assertEquals(expected, actual, PRECISION_TOLERANCE,
                "Negative precision scenario test failed");
    }

    @Test
    public void testEmptyList() {
        List<Double> input = new ArrayList<>();
        double expected = 0.0;
        double actual = solution(input);
        assertEquals(expected, actual, PRECISION_TOLERANCE,
                "Empty list test failed");
    }

    @Test
    public void testSingleElement() {
        List<Double> input = Arrays.asList(5.5);
        double expected = 5.5;
        double actual = solution(input);
        assertEquals(expected, actual, PRECISION_TOLERANCE,
                "Single element test failed");
    }

    private double solution(List<Double> list) {
//        return NeighbourSumArray.neighbourSum(list);
        return NeighbourSumArray_V2.neighbourSum(list);
    }
}