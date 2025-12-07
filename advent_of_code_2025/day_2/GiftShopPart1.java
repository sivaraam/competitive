import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GiftShopPart1 {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        Scanner scanner = new Scanner(new File(DialRotationsPart1.class.getResource("./day_2_actual_input.txt").toURI()));
        scanner.useDelimiter(",");
        List<String> ranges = new ArrayList<>();

        // Contains only one line
        while (scanner.hasNext()) {
            ranges.add(scanner.next());
        }

//        System.out.println(ranges);

        System.out.println(sumInvalidIds(ranges));
    }

    private static long sumInvalidIds(List<String> ranges) {
        long invalidIdSum = 0l;
        for (String range : ranges) {
            String[] rangeValues = range.split("-");
            String startRangeStr = rangeValues[0];
            String endRangeStr = rangeValues[1];

            // Skip if length is odd
            if (startRangeStr.length() == endRangeStr.length() && startRangeStr.length() % 2 == 1) {
                continue;
            }

            long startRange = Long.parseLong(startRangeStr);
            long endRange = Long.parseLong(endRangeStr);
            for (long i = startRange; i <= endRange; i++) {
                String str = Long.toString(i);
                String firstStr = str.substring(0, str.length() / 2);
                String secondStr = str.substring(str.length()/2);

                if (firstStr.equals(secondStr)) {
                    invalidIdSum += i;
                }
            }
        }
        return invalidIdSum;
    }
}
