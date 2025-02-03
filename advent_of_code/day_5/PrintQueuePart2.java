import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PrintQueuePart2 {
    public static void main(String[] args) throws FileNotFoundException {
        Map<Integer, Set<Integer>> shouldAppearBefore = new HashMap<>();
        List<List<Integer>> allUpdates = new ArrayList<>();

        final Pattern pageOrderPattern = Pattern.compile("([\\d]+)\\|([\\d]+)");
        final Pattern pagesToProduceInUpdate = Pattern.compile("([\\d]+\\,[\\d]+)+[\\d]+");

        try (Scanner scanner = new Scanner(new File("/mnt/d/kaartic/competitive/advent_of_code/day_5/actual_input.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.matches(pageOrderPattern.pattern())) {
                    Matcher matcher = pageOrderPattern.matcher(line);
                    if (matcher.find()) {
                        int mustComeBefore = Integer.parseInt(matcher.group(1));
                        int mustComeAfter = Integer.parseInt(matcher.group(2));

                        shouldAppearBefore.compute(mustComeAfter, (k, v) -> {
                            if (v == null) {
                                Set<Integer> newSet = new HashSet<>();
                                newSet.add(mustComeBefore);
                                return newSet;
                            } else {
                                v.add(mustComeBefore);
                                return v;
                            }
                        });
                    }
                } else if (line.matches(pagesToProduceInUpdate.pattern())) {
                    List<Integer> pagesToProduce = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                    allUpdates.add(pagesToProduce);
                } else {
                    // ignore the plausibly empty / non-conformant lines
                    System.err.println("Ignoring line: " + line);
                }
            }
        }

        System.out.println("Should appear before: " + shouldAppearBefore);
        System.out.println("All Updates: " + allUpdates);

        int conformantUpdatesMiddleSum = 0;
        for (List<Integer> pagesToUpdate : allUpdates) {
            if (isConformantUpdate(shouldAppearBefore, pagesToUpdate)) {
                if (pagesToUpdate.size() % 2 == 0) {
                    System.err.println("Pages to update is even!");
                }
                int middleIndex = pagesToUpdate.size() / 2;
                conformantUpdatesMiddleSum += pagesToUpdate.get(middleIndex);
            }
        }

        System.out.println("Corrected conformant updates middle sum: " + conformantUpdatesMiddleSum);
    }

    private static boolean isConformantUpdate(Map<Integer, Set<Integer>> shouldAppearBefore, List<Integer> pagesToUpdate) {
        boolean incorrectUpdateCorrected = false;

        for (int i = 0; i < pagesToUpdate.size() - 1; i++) {
            for (int j = i+1; j < pagesToUpdate.size(); ) {
                int currentPage = pagesToUpdate.get(i);
                Set<Integer> pagesCantAppearLater = shouldAppearBefore.get(currentPage);
                int laterPage = pagesToUpdate.get(j);

                if (pagesCantAppearLater != null && pagesCantAppearLater.contains(laterPage)) {
                    pagesToUpdate.remove(j);
                    pagesToUpdate.add(i, laterPage);
                    incorrectUpdateCorrected = true;
                }
                else {
                    j++;
                }
            }
        }

        return incorrectUpdateCorrected;
    }
}
