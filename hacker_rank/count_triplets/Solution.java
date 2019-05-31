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
        long validTriplets = 0;
        int len = arr.size();

        // sort the list for the sake of simplicity
        // Collections.sort(arr);

        for (int firstPos = 0; firstPos < len - 2; firstPos++) {
            for (int secondPos = firstPos + 1; secondPos < len - 1; secondPos++) {
                for (int thirdPos = secondPos + 1; thirdPos < len; thirdPos++) {
                    long firstElem = arr.get(firstPos),
                        secondElem = arr.get(secondPos),
                        thirdElem = arr.get(thirdPos);
                    long firstMultiple = firstElem * r,
                         secondMultiple = secondElem * r,
                         thirdMultiple = thirdElem * r;

                    if (
                        firstMultiple == secondElem && secondMultiple == thirdElem ||
                        firstMultiple == thirdElem && thirdMultiple == secondElem  ||
                        secondMultiple == firstElem && firstMultiple == thirdElem  ||
                        secondMultiple == thirdElem && thirdMultiple == firstElem  ||
                        thirdMultiple == firstElem && firstMultiple == secondElem  ||
                        thirdMultiple == secondElem && secondMultiple == firstElem
                    ) {
                        validTriplets++;
                    }
                }
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
