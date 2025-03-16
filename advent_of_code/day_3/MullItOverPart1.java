import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOverPart1 {
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        List<String> inputLine = new ArrayList<>();
        List<Integer> multiplicationResults = new ArrayList<>();

        Scanner scanner = new Scanner(new File(MullItOverPart1.class.getResource("./day_3_actual_input.txt").toURI()));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            inputLine.add(line);
        }

        Pattern mulPattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)");
        for (String line : inputLine) {
            // Use regex to extract all the mul pattern from each line of the string
            Matcher matcher = mulPattern.matcher(line);
            while (matcher.find()) {
                String firstNumber = matcher.group(1);
                String secondNumber = matcher.group(2);
                multiplicationResults.add(Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber));
            }
        }

        System.out.println("Multiplication results: " + multiplicationResults);
        System.out.println("Total: " + multiplicationResults.stream().mapToInt(Integer::intValue).sum());
    }
}
