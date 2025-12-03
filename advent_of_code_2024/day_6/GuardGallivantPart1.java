import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class GuardGallivantPart1 {
    private static final char GUARD = '^';
    private static final char OBSTACLE = '#';
    private static final char PATH = '.';

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        List<List<Character>> gridLines = new ArrayList<>();
        int lineIndex = 0;
        Coord guardCoord = null;

        Scanner in = new Scanner(new File(GuardGallivantPart1.class.getResource("./day_6_actual_input.txt").toURI()));
        while (in.hasNext()) {
           String line = in.next();
           List<Character> characters = new ArrayList<>();
           for (int charIndex = 0; charIndex < line.length(); charIndex++) {
               char ch = line.charAt(charIndex);
               if (ch == GUARD) {
                   guardCoord = new Coord(charIndex, lineIndex);
                   characters.add(PATH);
               }
               else {
                   characters.add(ch);
               }
           }

           gridLines.add(characters);

           lineIndex++;
        }

        Grid grid = new Grid(gridLines, gridLines.get(0).size(), lineIndex);
        MoveDirection currentMoveDirection = MoveDirection.NORTH;
        Set<Coord> distinctPositionsVisited = new HashSet<>();
        int guardMoves = 0;

        while (true) {
            Coord coordPositionBeforeGuard = computePositionBeforeGuard(guardCoord, currentMoveDirection);

            if (isPositionOutsideGrid(grid, coordPositionBeforeGuard)) {
                break;
            }

            char positionBeforeGuard = charAtPosition(grid, coordPositionBeforeGuard);
            if (positionBeforeGuard == OBSTACLE) {
                currentMoveDirection = nextMoveDirection(currentMoveDirection);
            }
            else if (positionBeforeGuard == PATH){
                distinctPositionsVisited.add(coordPositionBeforeGuard);
                guardCoord = coordPositionBeforeGuard;
                guardMoves++;
            }
            else {
                throw new IllegalArgumentException("This is not possible");
            }
        }

        System.out.println("Total positions visited by guard: " + guardMoves);
        System.out.println("Distinct positions visited by guard: " + distinctPositionsVisited.size());

    }

    private static boolean isPositionOutsideGrid(Grid grid, Coord guardCoord) {
        return (guardCoord.x >= grid.width) || (guardCoord.y >= grid.height);
    }

    private static char charAtPosition(Grid grid, Coord c) {
        return grid.grid.get(c.y).get(c.x);
    }

    private static Coord computePositionBeforeGuard(Coord guardCoord, MoveDirection currentMoveDirection) {
        Objects.requireNonNull(guardCoord);
        Objects.requireNonNull(currentMoveDirection);

        int newXCoord = guardCoord.x + (1 * currentMoveDirection.xOffset);
        int newYCoord = guardCoord.y + (1 * currentMoveDirection.yOffset);

        return new Coord(newXCoord, newYCoord);
    }

    private static MoveDirection nextMoveDirection(MoveDirection currentDirection) {
        Map<MoveDirection, MoveDirection> nextMoveDirection = Map.of(
                MoveDirection.NORTH, MoveDirection.EAST,
                MoveDirection.EAST, MoveDirection.SOUTH,
                MoveDirection.SOUTH, MoveDirection.WEST,
                MoveDirection.WEST, MoveDirection.NORTH
        );

        return nextMoveDirection.get(currentDirection);
    }

    record Grid (List<List<Character>> grid, int width, int height) { }

    record Coord(int x, int y) { }

    enum MoveDirection {
        NORTH (0, -1),
        EAST (1, 0),
        SOUTH (0, 1),
        WEST (-1, 0);
        
        final int xOffset;
        final int yOffset;
        
        MoveDirection(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }
    }

}
