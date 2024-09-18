import java.util.Arrays;
import java.util.HashMap;

/**
 * An optimal version of the TwoSumMap solution that tries to trim down
 * the search space based on the target value.
 * <p>
 * It turned out that the branching or additional operations needed by
 * this algorithm was not in favour of runtime.
 * <p>
 * Anyway, the rough algorithm is ass follows:
 * <ol>
 *     <li>Create a copy of the original array (arr). Let's call it <code>arrCopy</code></li>
 *     <li>Sort the array, <code>arrCopy</code>.</li>
 *     <li>
 *         Iterate <code>arr</code> and put elements in the hashmap.
 *         Put the element in the hashmap, mapping it to its index.
 *     </li>
 *     <ol>
 *         <li>
 *             While iterating, locate the center point of the sorted array
 *             (<code>arrCopy</code>). Let's call this <code>centerPoint</code>.
 *         </li>
 *     </ol>
 *     <li>
 *         After that's done, if a center point is found use the following steps
 *         to optimize the search space. If not found, just follow the traditional
 *         TwoSumMap strategy.
 *     </li>
 *     <ol>
 *         <li>
 *             If <code>target</code> is negative, start from the center and iterate
 *             backwards. This works as a negative target would certainly involve
 *             at least one negative value.
 *         </li>
 *         <li>
 *             If <code>target</code> is positive or zero, start from the center and
 *             iterate forwards. This works as a positive / zero target would certainly
 *             involve at least one positive value.
 *         </li>
 *     </ol>
 * </ol>
 * <p>
 * LeetCode runtime: 8ms
 * LeetCode memory: 44.1MB
 */
public class TwoSumSortMap {
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
        int[] numsSort = Arrays.copyOf(nums, nums.length);
        Arrays.sort(numsSort);

        boolean containsNegativeValues = numsSort[0] < 0;
        int i = 0;
        int length = nums.length;
        int centerIndex = -1;
        HashMap<Integer, Integer> numsMap = new HashMap<>();

        for (; i < length; i++) {
            int current = nums[i];
            Integer existingIndex = numsMap.put(current, i);

            /*
             * This is a valid optimization to do as the constraint
             * allows only one possible solution to the target. In the
             * presence of duplicate values in the input that is possible
             * only when the target is a sum of the duplicates.
             */
            if (existingIndex != null && target == nums[i] + nums[existingIndex]) {
                return new int[] {i, existingIndex};
            }

            if (containsNegativeValues && centerIndex == -1 && numsSort[i] > 0) {
                centerIndex = i;
            }
        }

        Integer needle2 = null;

        if (centerIndex != -1) {
            /*
             * Center index found. So, optimize the search space
             * based on the target arity.
             *
             * If negative target, start with center-1 and iterate
             * backward.
             *
             * If positive target, start with center and iterate
             * forward.
             */
            if (target < 0) {
                for (i = centerIndex - 1; i >= 0; i--) {
                    int diff = target - numsSort[i];
                    needle2 = numsMap.get(diff);
                    if (needle2 != null) {
                        int actualIndex = numsMap.get(numsSort[i]);
                        if (actualIndex != needle2) {
                            // reset i to the original index of the current value
                            i = numsMap.get(numsSort[i]);
                            break;
                        }
                    }
                }
            }
            else {
                for (i = centerIndex; i < length; i++) {
                    int diff = target - numsSort[i];
                    needle2 = numsMap.get(diff);
                    if (needle2 != null) {
                        int actualIndex = numsMap.get(numsSort[i]);
                        if (actualIndex != needle2) {
                            // reset i to the original index of the current value
                            i = numsMap.get(numsSort[i]);
                            break;
                        }
                    }
                }
            }
        }
        else {
            // No center point present. Array has either only positive / negative
            // values. So, we can just go ahead with normal start to finish search
            for (i = 0; i < length; i++) {
                int diff = target - nums[i];
                needle2 = numsMap.get(diff);
                if (needle2 != null && needle2 != i) {
                    break;
                }
            }
        }

        if (i != nums.length && i !=-1 && needle2 != null) {
            return new int[] {i, needle2};
        }
        else {
            return new int[] {};
        }
    }
}