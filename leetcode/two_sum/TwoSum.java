import java.util.Arrays;

/**
 * Brute force approach to the two-sum problem.
 */
public class TwoSum {
    private static int resultCount = 1;

    public static void main(String[] args) {
        int[] result = twoSum(new int[] { 2, 7, 11, 15 }, 9);
        printResult(result);

        result = twoSum(new int[] { 2, 6, 11, 15 }, 9);
        printResult(result);

        result = twoSum(new int[] { 3, 2, 4 }, 6);
        printResult(result);

        result = twoSum(new int[] { 3, 3 }, 6);
        printResult(result);

        result = twoSum(new int[] { -3, 3 }, 0);
        printResult(result);

        result = twoSum(new int[] { -107, -45, 7, 11, -67, 15,-3, 3, 2, 4, -209 }, -43);
        printResult(result);
    }

    private static void printResult(int[] result) {
        System.out.print("Result " + resultCount++ + ": ");
        if (result.length == 0) {
            System.out.println("No match found");
        }
        else {
            System.out.println(Arrays.toString(result));
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        int i = 0, j = 0, length = nums.length;

        outer: for (; i < length; i++) {
            int needle1 = nums[i];

            for (j = i+1; j < length; j++) {
                int needle2 = nums[j];
                if (needle1 + needle2 == target) {
                    break outer;
                }
            }
        }

        if (i != nums.length) {
            return new int[] {i, j};
        }
        else {
            return new int[] {};
        }
    }
}