import java.util.Arrays;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 7, 1, 2, 8, 4, 5);

        System.out.println("Sum: 20. Possible:" + find_sum_of_three(list, 20));
        System.out.println("Sum: 21. Possible:" + find_sum_of_three(list, 21));
    }


    static boolean find_sum_of_three(List<Integer> arr,
                           int required_sum) {
        int size = arr.size();

        for (int i = 0; i < size; i++) {
            for (int j = i+1; j < size; j++) {
                for (int k = j+1; k < size; k++) {
                    int sum = arr.get(i) + arr.get(j) + arr.get(k);
                    if (sum == required_sum) {
                        System.out.println("Values: " + String.join(", ", arr.get(i).toString(), arr.get(j).toString(), arr.get(k).toString()));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
