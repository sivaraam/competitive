import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Need to ruminate later to see if there are alternative optimal approaches than this
public class MullItOverPart2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> inputLine = new ArrayList<>();
        List<Integer> multiplicationResults = new ArrayList<>();
        StringBuilder searchPhrases = new StringBuilder();

        Scanner scanner = new Scanner(new File("/mnt/d/kaartic/competitive/advent_of_code/day_3/actual_input.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            inputLine.add(line);
        }

        String combinedInput = String.join("", inputLine);
        Pattern mulPattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)");
        Pattern doPattern = Pattern.compile("do\\(\\)");
        Pattern dontPattern = Pattern.compile("don't\\(\\)");

        // Use regex to extract all the do and don't pattern from the combined input string
        // This helps us identify the search zones from the whole input which is the only
        // area we need to look for 'mul' instructions.
        Matcher doMatch = doPattern.matcher(combinedInput);
        Matcher dontMatch = dontPattern.matcher(combinedInput);
        int startIndex = 0, doStart = -1, dontStart = -1;
        List<Map.Entry<Integer, Integer>> searchZones = new ArrayList<>();

        while(dontMatch.find()) {
            dontStart = dontMatch.start();
            if (dontStart > startIndex) {
                searchZones.add(new AbstractMap.SimpleEntry<>(startIndex, dontStart));
                while (doMatch.find(dontStart)) {
                    doStart = doMatch.start();
                    if (doStart > dontStart) {
                        startIndex = doStart;
                        break;
                    }
                }
                if (doMatch.hitEnd()) {
                    break;
                }
            }
        }

        if (doStart > dontStart) {
            searchZones.add(new AbstractMap.SimpleEntry<>(startIndex, combinedInput.length()));
        }

        // Search for the mul instructions in the search zones
        for (Map.Entry<Integer, Integer> searchZone : searchZones) {
            String searchPhrase = combinedInput.substring(searchZone.getKey(), searchZone.getValue());
            searchPhrases.append(searchPhrase).append("\n\n");
            Matcher mulMatch = mulPattern.matcher(searchPhrase);
            while (mulMatch.find()) {
                String firstNumber = mulMatch.group(1);
                String secondNumber = mulMatch.group(2);
                multiplicationResults.add(Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber));
            }
        }

        System.out.println("Multiplication results: " + multiplicationResults);
        System.out.println("Total: " + multiplicationResults.stream().mapToInt(Integer::intValue).sum());
        System.out.println(searchPhrases);
    }
}
