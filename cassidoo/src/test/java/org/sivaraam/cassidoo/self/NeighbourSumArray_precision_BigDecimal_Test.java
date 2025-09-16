package org.sivaraam.cassidoo.self;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class has some test cases to test the precision scenario
 * rigorously. Test cases generated with help of Claude.
 * <p>
 * This specific class has the term 'BigDecimal' in its name as it tries
 * to evaluate the BigDecimal approach of adding the decimal and
 * checking how many test cases we could pass using that approach.
 */
public class NeighbourSumArray_precision_BigDecimal_Test {

    private static final BigDecimal PRECISION_TOLERANCE = new BigDecimal("1e-100");

    @Test
    public void testBasicPrecision() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("0.1"),
                new BigDecimal("0.2"),
                new BigDecimal("0.3")
        );
        BigDecimal expected = new BigDecimal("1.4");
        BigDecimal actual = solution(input);
        assertTrue(expected.subtract(actual).abs().compareTo(PRECISION_TOLERANCE) <= 0,
                "Basic decimal precision test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testVerySmallNumbers() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("1e-15"),
                new BigDecimal("2e-15"),
                new BigDecimal("3e-15")
        );
        BigDecimal expected = new BigDecimal("1.4e-14");
        BigDecimal actual = solution(input);
        BigDecimal tolerance = new BigDecimal("1e-24");
        assertTrue(expected.subtract(actual).abs().compareTo(tolerance) <= 0,
                "Very small numbers precision test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testMixedVerySmallAndNormal() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("1e-10"),
                new BigDecimal("0.5"),
                new BigDecimal("1e-10")
        );
        BigDecimal expected = new BigDecimal("1.5000000004");
        BigDecimal actual = solution(input);
        BigDecimal tolerance = new BigDecimal("1e-12");
        assertTrue(expected.subtract(actual).abs().compareTo(tolerance) <= 0,
                "Mixed small and normal numbers test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testLargeMagnitudeDifferences() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("1e-15"),
                new BigDecimal("1e15"),
                new BigDecimal("1e-15")
        );
        BigDecimal expected = new BigDecimal("3000000000000000.000000000000004");
        BigDecimal actual = solution(input);
        BigDecimal tolerance = new BigDecimal("1e5");
        assertTrue(expected.subtract(actual).abs().compareTo(tolerance) <= 0,
                "Large magnitude differences test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testPrecisionLossScenario() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("0.1"), new BigDecimal("0.1"), new BigDecimal("0.1"),
                new BigDecimal("0.1"), new BigDecimal("0.1"), new BigDecimal("0.1"),
                new BigDecimal("0.1"), new BigDecimal("0.1"), new BigDecimal("0.1"),
                new BigDecimal("0.1")
        );
        BigDecimal expected = new BigDecimal("2.8");
        BigDecimal actual = solution(input);
        assertTrue(expected.subtract(actual).abs().compareTo(PRECISION_TOLERANCE) <= 0,
                "Repeated 0.1 precision loss test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testNumbersThatDontSumCleanlyInBinary() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("0.1"), new BigDecimal("0.2"), new BigDecimal("0.3"),
                new BigDecimal("0.4"), new BigDecimal("0.5"), new BigDecimal("0.6"),
                new BigDecimal("0.7"), new BigDecimal("0.8"), new BigDecimal("0.9")
        );
        BigDecimal expected = new BigDecimal("12.5");
        BigDecimal actual = solution(input);
        assertTrue(expected.subtract(actual).abs().compareTo(PRECISION_TOLERANCE) <= 0,
                "Binary representation precision test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testAlternatingTinyAndHuge() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("1e-16"),
                new BigDecimal("1e16"),
                new BigDecimal("1e-16"),
                new BigDecimal("1e16"),
                new BigDecimal("1e-16")
        );
        BigDecimal expected = new BigDecimal("60000000000000000.0000000000000007");
        BigDecimal actual = solution(input);
        BigDecimal tolerance = new BigDecimal("1e6");
        assertTrue(expected.subtract(actual).abs().compareTo(tolerance) <= 0,
                "Alternating tiny and huge numbers test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testNearDoublePrecisionLimits() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("1.7976931348623157e308"),
                new BigDecimal("1e-100"),
                new BigDecimal("-1.7976931348623157e308")
        );
        BigDecimal expected = new BigDecimal("3e-100");
        BigDecimal actual = solution(input);
        BigDecimal tolerance = new BigDecimal("1e-110");
        assertTrue(expected.subtract(actual).abs().compareTo(tolerance) <= 0,
                "Near double precision limits test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testCatastrophicCancellation() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("1.0000000000000002"),
                new BigDecimal("-1.0"),
                new BigDecimal("1.0000000000000002")
        );
        BigDecimal expected = new BigDecimal("1.0000000000000008");
        BigDecimal actual = solution(input);
        BigDecimal tolerance = new BigDecimal("1e-15");
        assertTrue(expected.subtract(actual).abs().compareTo(tolerance) <= 0,
                "Catastrophic cancellation test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testExtremePrecisionChallenge() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("0.123456789012345"),
                new BigDecimal("-0.123456789012344"),
                    new BigDecimal("0.000000000000001"),
                new BigDecimal("1e-14"),
                new BigDecimal("-1e-14")
        );
        BigDecimal expected = new BigDecimal("-.123456789012329");
        BigDecimal actual = solution(input);
        BigDecimal tolerance = new BigDecimal("1e-16");
        assertTrue(expected.subtract(actual).abs().compareTo(tolerance) <= 0,
                "Extreme precision challenge test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testAccumulatedRoundingErrors() {
        // Create list with values that accumulate rounding errors
        List<BigDecimal> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add(new BigDecimal("0.01"));
        }
        BigDecimal expected = new BigDecimal("2.98"); // Theoretical exact value
        BigDecimal actual = solution(input);
        assertTrue(expected.subtract(actual).abs().compareTo(PRECISION_TOLERANCE) <= 0,
                "Accumulated rounding errors test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testNegativePrecisionScenario() {
        List<BigDecimal> input = Arrays.asList(
                new BigDecimal("-0.1"), new BigDecimal("-0.2"), new BigDecimal("-0.3"),
                new BigDecimal("-0.4"), new BigDecimal("-0.5")
        );
        BigDecimal expected = new BigDecimal("-3.9");
        BigDecimal actual = solution(input);
        assertTrue(expected.subtract(actual).abs().compareTo(PRECISION_TOLERANCE) <= 0,
                "Negative precision scenario test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testEmptyList() {
        List<BigDecimal> input = new ArrayList<>();
        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = solution(input);
        assertTrue(expected.subtract(actual).abs().compareTo(PRECISION_TOLERANCE) <= 0,
                "Empty list test failed. Expected: " + expected + ", Actual: " + actual);
    }

    @Test
    public void testSingleElement() {
        List<BigDecimal> input = Arrays.asList(new BigDecimal("5.5"));
        BigDecimal expected = new BigDecimal("5.5");
        BigDecimal actual = solution(input);
        assertTrue(expected.subtract(actual).abs().compareTo(PRECISION_TOLERANCE) <= 0,
                "Single element test failed. Expected: " + expected + ", Actual: " + actual);
    }

    private BigDecimal solution(List<BigDecimal> list) {
        // Your BigDecimal implementation here
        // return NeighbourSumArray_precision.neighbourSum(list);
        return NeighbourSumArray_precision.neighbourSum(list);
    }
}