package sum_three_integers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_less_loops {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 7, 1, 2, 8, 4, 5);

        System.out.println("(2-loop variant) Sum: 20. Possible:" + find_sum_of_three_optimal(list, 20));
        System.out.println("(2-loop variant) Sum: 21. Possible:" + find_sum_of_three_optimal(list, 21));
    }

    /*
     * An optimal variant that only uses two loops.
     * 
     * First sort the array. For each element in sorted list, deduct that
     * element from required sum and then identify whether the remaining
     * sum can be obtained from the sum of two elements in the set of numbers
     * greater than the current element.
     * 
     * The sum of two elements could be optimally done by picking the first
     * and last elements of the remaining set (assuming they are marked by
     * front and back markers) and progressing front or back markers based
     * on whether the sum is greater / lesser than the desired remaining sum.
     */
    static boolean find_sum_of_three_optimal(List<Integer> arr, int required_sum) {
        Collections.sort(arr);

        for (int i = 0; i < arr.size() - 1; i++) {
            int remaining = required_sum - arr.get(i);
            boolean rest_sum = find_sum_of_two_optimal(arr, remaining, i + 1);
            if (rest_sum) {
                return true;
            }
        }

        return false;
    }

    static boolean find_sum_of_two_optimal(List<Integer> arr, int remaining_sum, int start_index) {
        for (int j = start_index, k = arr.size() - 1; j < k;) {
            int sum = arr.get(j) + arr.get(k);
            if (sum == remaining_sum) {
                return true;
            }

            if (sum < remaining_sum) {
                j++;
            }
            else {
                k--;
            }
        }

        return false;
    }
}
