import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static int[][] merge(int[][] intervals) {
        int[][] merged_intervals = new int[intervals.length][2];
        int merged = 0;

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        merged_intervals[0] = intervals[0];

        for (int j = 1; j < intervals.length; j++) {
            int lower = intervals[j][0];
            int merged_upper = merged_intervals[merged][1];
            if (lower <= merged_upper) {
                merged_intervals[merged][1] = Math.max(merged_upper, intervals[j][1]);
            }
            else {
                merged_intervals[++merged] = intervals[j];
            }
        }

        return Arrays.copyOf(merged_intervals, merged+1);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] { { 1,4 }, {0, 4}};
        int[][] merged = merge(intervals);
        Arrays.stream(merged).forEach((pair) -> System.out.print(String.join("; ", Arrays.toString(pair))));

        System.out.println();
        intervals = new int[][] {{ 1,3 }, { 2,6 }, { 8,10 }, { 15,18 }};
        merged = merge(intervals);
        Arrays.stream(merged).forEach((pair) -> System.out.print(String.join("; ", Arrays.toString(pair))));
    }

}
