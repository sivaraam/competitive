import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * FIXME: This solution is meddled with edge case handling. We might need to try and
 *  find a solution that's more neater.
 */
class RedNosedReportsPart2 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> unsafeReports = new ArrayList<>();
        long safeReports = 0;

        try {
            Scanner scanner = new Scanner(new File(RedNosedReportsPart2.class.getResource("./day_2_actual_input.txt").toURI()));
            while (scanner.hasNextLine()) {
                List<Integer> currentReport = new ArrayList<>();
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+");
                Arrays.stream(numbers).map(Integer::parseInt).forEach(currentReport::add);
                if (isSafeReport(currentReport, true)) {
                    safeReports++;
                }
                else {
                    // Check safe report by cross-checking the sequence leaving out first element
                    if (isSafeReport(currentReport.subList(1, currentReport.size()), false)) {
                        safeReports++;
                    }
                    else {
                        // Check safe report by cross-checking the sequence leaving out the second element
                        // but retaining all other elements of the list
                        List<Integer> reportWithoutSecond = currentReport.subList(0, 1);
                        reportWithoutSecond.addAll(currentReport.subList(2, currentReport.size()));
                        if (isSafeReport(reportWithoutSecond, false)) {
                            safeReports++;
                        }
                        else {
                            unsafeReports.add(line);
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }

        System.out.println("Safe reports: " + safeReports);

        // Write unsafe reports to the file
        Path file = Paths.get("./advent_of_code/day_2/unsafe_reports.txt");
        Files.write(file, unsafeReports, StandardCharsets.UTF_8);
        System.out.printf("Wrote unsafe reports to a file. Location: " + file.toAbsolutePath());
    }

    private static boolean isSafeReport(List<Integer> currentReport, boolean canSkipOneReport) {
        // Assume there will be at least two elements
        boolean increasing = currentReport.get(0) < currentReport.get(1);

        for (int i = 0; i < currentReport.size() - 1;) {
            int firstElement = currentReport.get(i);
            int nextElement =  currentReport.get(i+1);

            if (!isSafeReportPair(firstElement, nextElement, increasing)) {
                if (canSkipOneReport) {
                    if (i != (currentReport.size() - 2)) {
                        if (!isSafeReportPair(firstElement, currentReport.get(i + 2), increasing)) {
                            return false;
                        }
                        canSkipOneReport = false;
                        i += 2;
                        continue;
                    }
                    else {
                        // last pair alone is unsafe
                        return true;
                    }
                }
                else {
                    return false;
                }
            }
            i++;
        }

        return true;
    }

    private static boolean isSafeReportPair(int elem1, int elem2, boolean increasing) {
        int diff;
        if (increasing) {
            diff = elem2- elem1;
        }
        else {
            diff = elem1 - elem2;
        }

        return diff >= 1 && diff <= 3;
    }
}