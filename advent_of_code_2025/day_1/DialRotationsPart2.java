import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.URISyntaxException;
import java.io.FileNotFoundException;

public class DialRotationsPart2 {
    private static int DIAL_POSITIONS = 100;

    record NeedleMove(int newPosition, int pointedAtZero) {
    }

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        int position = 50;
        NeedleMove afterMove;
        Scanner scanner = new Scanner(new File(DialRotationsPart2.class.getResource("./day_1_actual_input.txt").toURI()));
        List<String> rotations = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //String[] numbers = line.split("\\s+");
            rotations.add(line);
        }

        int password = 0;
        for (String rotation : rotations) {
            afterMove = moveNeedle(position, rotation);
            position = afterMove.newPosition;
            password += afterMove.pointedAtZero;

            // Debug
            // System.out.println("Interim password after rotation " + rotation + " : " + password + "; Position: " + position);
        }

        System.out.println("Password: " + password);
    }

    private static NeedleMove moveNeedle(int position, String rotation) {
        boolean isLeft = rotation.charAt(0) == 'L';
        int distance = Integer.parseInt(rotation.substring(1));

        if (isLeft) {
            return leftNeedleMove(position, distance);
        } else {
            return rightNeedleMove(position, distance);
        }
    }

    private static NeedleMove rightNeedleMove(int position, int distance) {
        int newPosition = position + (distance % DIAL_POSITIONS);
        int pointedAtZero = distance / DIAL_POSITIONS;

        /*
         * Conditional covers the case of needle crossing zero when
         * moving in the residual case (including the case of the
         * needle resting at zero).
         */
        if (newPosition >= DIAL_POSITIONS) {
            newPosition = newPosition - DIAL_POSITIONS;
            pointedAtZero++;
        }

        return new NeedleMove(newPosition, pointedAtZero);
    }

    private static NeedleMove leftNeedleMove(int position, int distance) {
        int newPosition = position - (distance % DIAL_POSITIONS);
        int pointedAtZero = distance / DIAL_POSITIONS;

        if (newPosition < 0) {
            newPosition = DIAL_POSITIONS + newPosition;

            /*
             * Increment when we start at non-zero position as
             * we would cross 0 once, during the calculation
             * in the above line.
             *
             * Skip it when we the start position is zero as
             * we would´ve counted it already.
             */
            if (position != 0) {
                pointedAtZero++;
            }

        } else {
            /*
             * Increment when we rest at 0 but don´t do so when
             * we already started at 0 as that would´ve been
             * counted.
             *
             * Handled in else, as we can´t rest at 0 when newPosition
             * goes negative.
             */
            if (position != 0 && newPosition == 0) {
                pointedAtZero++;
            }
        }


        return new NeedleMove(newPosition, pointedAtZero);

    }


}
