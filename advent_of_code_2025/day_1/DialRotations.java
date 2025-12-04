import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DialRotations {
    private static int DIAL_POSITIONS = 100;
    public static void main(String[] args) {
        int position  = 50;
        Scanner scanner = new Scanner(new File(HistorianHysteriaPart1.class.getResource("./.txt").toURI()));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] numbers = line.split("\\s+");
        }
            List<String> rotations = List.of(
                "L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"
        );

        int password = 0;
        for (String rotation : rotations) {
            boolean isLeft = rotation.charAt(0) == 'L';
            int distance = Integer.parseInt(rotation.substring(1));

            position = moveNeedle(position, isLeft, distance);
            if (position == 0) {
                password++;
            }
        }

        System.out.println("Password: " + password);
    }

    private static int moveNeedle(int position, boolean isLeft, int distance) {
        if (isLeft) {
            return leftNeedleMove(position, distance);
        }
        else {
            return rightNeedleMove(position, distance);
        }
    }

    private static int rightNeedleMove(int position, int distance) {
        int newPosition = position + (distance % DIAL_POSITIONS);
        if (newPosition < DIAL_POSITIONS) {
            return newPosition;
        }
        else {
            return newPosition - DIAL_POSITIONS;
        }
    }

    private static int leftNeedleMove(int position, int distance) {
        int newPosition = position - (distance % DIAL_POSITIONS);
        if (newPosition >= 0) {
            return newPosition;
        }
        else {
            // effectively reduces value from DIAL_POSITIONS as
            // newPosition would be negative
            return DIAL_POSITIONS + newPosition;
        }
    }




}
