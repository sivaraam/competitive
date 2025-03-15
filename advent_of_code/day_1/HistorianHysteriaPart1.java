import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HistorianHysteriaPart1 {
    public static void main(String[] args) {
        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        long totalDistance = 0;

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

        Collections.sort(array1);
        Collections.sort(array2);

        for (int i = 0; i < array1.size(); i++) {
            int elem1 = array1.get(i),
                elem2 = array2.get(i);

            totalDistance += Math.abs(elem1 - elem2);
        }

        System.out.println("Total distance: " + totalDistance);
    }
}