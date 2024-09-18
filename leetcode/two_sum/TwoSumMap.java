import java.util.Arrays;
import java.util.HashMap;

/**
 * A bit optimal solution that constructs a HashMap from the array.
 * The key is the array element and the value is the array index
 * of the element.
 * <p>
 * Then it starts iterating over each element of the array. For
 * each element, it computes the difference between the target
 * and the current element. It looks up the hash map to see if
 * the difference is present and if so picks the index of that
 * element. If the index matches the current index, we continue
 * further with the search.
 * <b>Caveat</b>
 * This ideally does not work well when there are duplicate
 * elements in the array as the map. The test case has one
 * such problem. It passes only by chance given the LeetCode
 * constraints.
 * <p>
 * LeetCode runtime: 4ms
 * LeetCode memory: 44.3 MB
 */
public class TwoSumMap {
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

        result = twoSum(new int[] { -3, 4, 3, 90 }, 0);
        printResult(result);

        result = twoSum(new int[] { 3, 2, 95, 4, -3 }, 92);
        printResult(result);

        // This case would ideally fail as the input has duplicate elements. It passes only
        // because the constraints allows only one possible solution and with the presence
        // of duplicates that is only possible when the target is a sum of two duplicates.
        result = twoSum(new int[] { -500000000, 2, 4, -500000000 }, -1000000000);
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
        int i = 0, length = nums.length;
        HashMap<Integer, Integer> numsMap = new HashMap<>();

        for (; i < length; i++) {
            numsMap.put(nums[i], i);
        }

        Integer needle2 = null;
        for (i = 0; i < length; i++) {
            int diff = target - nums[i];
            needle2 = numsMap.get(diff);
            if (needle2 != null && needle2 != i) {
                break;
            }
        }

        if (i != nums.length && needle2 != null) {
            return new int[] {i, needle2};
        }
        else {
            return new int[] {};
        }
    }
}