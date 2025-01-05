import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HistorianHysteriaPart2 {
    public static void main(String[] args) {
        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        Map<Integer, Integer> occurrences = new HashMap<>();
        long similarityScore = 0;

        try {
            Scanner scanner = new Scanner(new File("/mnt/d/kaartic/competitive/advent_of_code/day_1/actual_input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+");
                array1.add(Integer.parseInt(numbers[0]));
                array2.add(Integer.parseInt(numbers[1]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            return;
        }


        // compute occurrence of each element in array2
        for (int elem : array2) {
            occurrences.compute(elem, (k, v) -> v == null ? 1 : v + 1);
        }

//        Collections.sort(array1);
//        Collections.sort(array2);

        // compute similarity score
        for (int elem : array1) {
            similarityScore += (long) occurrences.getOrDefault(elem, 0) * elem;
        }

        System.out.println("Similarity score: " + similarityScore);
    }
}