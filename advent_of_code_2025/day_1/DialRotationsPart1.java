import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.URISyntaxException;
import java.io.FileNotFoundException;

public class DialRotationsPart1 {
    private static int DIAL_POSITIONS = 100;

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        int position  = 50;
        Scanner scanner = new Scanner(new File(DialRotations.class.getResource("./day_1_actual_input.txt").toURI()));
        List<String> rotations = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
	    rotations.add(line);
        }
 
 	int password = 0;
        for (String rotation : rotations) {
            position = moveNeedle(position, rotation);
	    
	    if (position == 0) {
                password++;
	    }

            // System.out.println("Interim password after rotation " + rotation + " : " + password + "; Position: " + position);
        }

        System.out.println("Password: " + password);
    }

    private static int moveNeedle(int position, String rotation) {
        boolean isLeft = rotation.charAt(0) == 'L';
        int distance = Integer.parseInt(rotation.substring(1));

        if (isLeft) {
            return leftNeedleMove(position, distance);
        }
        else {
            return rightNeedleMove(position, distance);
        }
    }

    private static int rightNeedleMove(int position, int distance) {
        int newPosition = position + (distance % DIAL_POSITIONS);

	if (newPosition >= DIAL_POSITIONS) {
            newPosition = newPosition - DIAL_POSITIONS;
        }

	return newPosition;
    }

    private static int leftNeedleMove(int position, int distance) {
        int newPosition = position - (distance % DIAL_POSITIONS);

        if (newPosition < 0) {
	    newPosition = DIAL_POSITIONS + newPosition;
        }

	return newPosition;

    }




}
