import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        /*
         * We have two maps: left, right
         *
         * left: contains elements and the occurences of those
         *       elements less than current index. initially empty
         * right: contains elements and the occurences of those
         *        elements greater than the current index. initially
         *        contains all elements
         */

        Map<Long, Long> left = new HashMap<Long, Long>(),
                        right = new HashMap<Long, Long>();
        int len = arr.size();
        long validTriplets = 0;

        for (int i = 0; i < len; i++) {
            long currElem = arr.get(i);
            if (right.containsKey(currElem)) {
                right.put(currElem,  right.get(currElem) + 1);
            }
            else {
                right.put(currElem, 1L);
            }
        }

        for (int i = 0; i < len; i++) {
            long currElem = arr.get(i);

            // reduce occurence of right element by 1
            right.put(currElem, right.get(currElem) - 1);

            if (currElem % r == 0) {
                if (
                    left.containsKey(currElem/r) &&
                    right.containsKey(currElem*r)
                ) {
                    validTriplets += left.get(currElem/r) * right.get(currElem*r);
                }
            }

            if (left.containsKey(currElem)) {
                left.put(currElem, left.get(currElem) + 1);
            }
            else {
                left.put(currElem, 1L);
            }
        }

        return validTriplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr.add(arrItem);
        }

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
