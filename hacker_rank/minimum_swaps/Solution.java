import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int size = arr.length;
        int swaps = 0;

        /*
         * Keep swaping the current index with its correct position
         * in the array until the current index is correct, then increment by one. 
         */
        for (int i = 0; i < size; i++) {
            for (int j = i+1; (arr[i] != i+1) && j < size; j++) {
                if (arr[j] == i+1) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    swaps++;
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

        return swaps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
