package org.sivaraam.cassidoo.oct27_2025;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a field represented as an array of 0s and 1s,
 * where 1 means that position needs protection, you
 * can place a scarecrow at any index, and each scarecrow
 * protects up to k consecutive positions centered around
 * itself (for example, for k = 3, a scarecrow at i protects
 * i-1, i, and i+1). Return the minimum set of indices where
 * scarecrows should be placed so that all the positions with
 * 1 are protected. You can assume k is an odd number (or make
 * up what happens if it's even).
 * <pre>
 * let field = [1, 1, 0, 1, 1, 0, 1];
 * let k = 3;
 *
 * placeScarecrows(field, k);
 * > [1, 4, 6]
 *
 * placeScarecrows([1, 0, 1, 1, 0, 1], k)
 * > [1, 4]
 *
 * placeScarecrows([1, 1, 1, 1, 1], 1)
 * > [0, 1, 2, 3, 4]
 * </pre>
 */
public class ScareCrowPlacement {
    public static void main(String[] args) {
        var field = List.of(1, 1, 0, 1, 1, 0, 1);
//        var field = List.of(1, 1, 1, 0, 1, 1);

        var k = 3;
//        var k = 2;

        var scareCrowPositions = placeScareCrows(field, k);
        System.out.println(scareCrowPositions);
    }

    public static Map<Integer, Integer> placeScareCrows(List<Integer> field, int scareCrowCoverArea) {
        var firstUnprotectedSpot = -1;
        var coveredSpots = 0;
        var fieldSpot = 0;
        var fieldLength = field.size();
        var scareCrowPositionAndCoverage = new HashMap<Integer, Integer>();

        while (fieldSpot <= fieldLength) {
            boolean spotNeedsProtection = fieldSpot < fieldLength && field.get(fieldSpot) == 1;

            if (spotNeedsProtection) {
                firstUnprotectedSpot = updateFirstUnprotectedSpot(firstUnprotectedSpot, fieldSpot);
            }

            if (shouldPlaceScareCrow(fieldSpot, firstUnprotectedSpot, scareCrowCoverArea, fieldLength)) {
                placeScareCrow(scareCrowPositionAndCoverage, firstUnprotectedSpot, fieldSpot, coveredSpots);
                firstUnprotectedSpot = spotNeedsProtection ? fieldSpot : -1;
                coveredSpots = spotNeedsProtection ? 1 : 0;
            } else if (spotNeedsProtection) {
                coveredSpots++;
            }

            fieldSpot++;
        }

        return scareCrowPositionAndCoverage;
    }

    private static int updateFirstUnprotectedSpot(int firstUnprotectedSpot, int fieldSpot) {
        return firstUnprotectedSpot == -1 ? fieldSpot : firstUnprotectedSpot;
    }

    private static boolean shouldPlaceScareCrow(int fieldSpot, int firstUnprotectedSpot, int scareCrowCoverArea, int fieldLength) {
        return fieldSpot >= firstUnprotectedSpot + scareCrowCoverArea || fieldSpot == fieldLength;
    }

    private static void placeScareCrow(Map<Integer, Integer> scareCrowPositionAndCoverage, int firstUnprotectedSpot, int fieldSpot, int coveredSpots) {
        if (coveredSpots != 0) {
            int scareCrowPosition = coveredSpots > 1
                    ? (int) Math.floor((firstUnprotectedSpot + fieldSpot - 1) / 2.0)
                    : firstUnprotectedSpot;

            scareCrowPositionAndCoverage.put(scareCrowPosition, coveredSpots);
        }
    }

}
