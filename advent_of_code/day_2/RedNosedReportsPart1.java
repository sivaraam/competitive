import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

class RedNosedReportsPart1 {
    public static void main(String[] args) {
        long safeReports = 0;

        try {
            Scanner scanner = new Scanner(new File(RedNosedReportsPart1.class.getResource("./day_2_actual_input.txt").toURI()));
            while (scanner.hasNextLine()) {
                List<Integer> currentReport = new ArrayList<>();
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+");
                Arrays.stream(numbers).map(Integer::parseInt).forEach(currentReport::add);
                if (isSafeReport(currentReport)) {
                    safeReports++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Safe reports: " + safeReports);
    }

    private static boolean isSafeReport(List<Integer> currentReport) {
        // Assume there will be at least two elements
        boolean increasing = currentReport.get(0) < currentReport.get(1);

        for (int i = 0; i < currentReport.size() - 1; i++) {
            int firstElement = currentReport.get(i);
            int nextElement =  currentReport.get(i+1);

            if (increasing) {
                int diff = nextElement - firstElement;
                if (diff < 1 || diff > 3) {
                    return false;
                }
            }
            else {
                int diff = firstElement - nextElement;
                if (diff < 1 || diff > 3) {
                    return false;
                }
            }
        }

        return true;
    }
}