import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CeresSearchPart1 {
    public static void main(String[] args) {
        List<List<Character>> grid = new ArrayList<>();
        int width;
        int height;

        try {
            Scanner scanner = new Scanner(new File("/mnt/d/kaartic/competitive/advent_of_code/day_4/actual_input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<Character> gridRow = new ArrayList<>(line.length());

                // Fill the grid
                for (int i = 0; i < line.length(); i++) {
                    gridRow = line.chars()
                                  .filter(ch -> ch != '\n')
                                  .mapToObj(ch -> (char) ch)
                                  .toList();
                }
                grid.add(gridRow);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }

        height = grid.size();
        width = grid.get(0).size();

        // Print the grid to verify
        for (List<Character> rowArray : grid) {
            System.out.println(rowArray);
        }

        XmasGrid xmasGrid = new XmasGrid(grid, width, height);
        Set<XmasCoords> allXmasCoords = new HashSet<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                GridIndex index = new GridIndex(x, y);
                allXmasCoords.addAll(identifyXmas(xmasGrid, index));
            }
        }

        System.out.println("XMAS occurences: " + allXmasCoords.size());
//        System.out.println("All XMAS coordinates: " + allXmasCoords);
    }

    private static Set<XmasCoords> identifyXmas(XmasGrid grid, GridIndex index) {
        Set<XmasCoords> coords = new HashSet<>();
        char indexChar = grid.grid.get(index.y).get(index.x);
        GridIndex result;
        if (indexChar == 'X') {
            char[] searchChars = {'X', 'M', 'A', 'S'};

            result = identifyXmas(grid, SearchDirection.NORTH, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(index, result));
            }

            result = identifyXmas(grid, SearchDirection.NORTH_EAST, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(index, result));
            }

            result = identifyXmas(grid, SearchDirection.EAST, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(index, result));
            }

            result = identifyXmas(grid, SearchDirection.SOUTH_EAST, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(index, result));
            }

            result = identifyXmas(grid, SearchDirection.SOUTH, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(index, result));
            }
        }
        else if (indexChar == 'S') {
            char[] searchChars = {'S', 'A', 'M', 'X'};
            result = identifyXmas(grid, SearchDirection.NORTH, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(result, index));
            }

            result = identifyXmas(grid, SearchDirection.NORTH_EAST, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(result, index));
            }

            result = identifyXmas(grid, SearchDirection.EAST, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(result, index));
            }

            result = identifyXmas(grid, SearchDirection.SOUTH_EAST, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(result, index));
            }

            result = identifyXmas(grid, SearchDirection.SOUTH, index, searchChars);
            if (result != null) {
                coords.add(new XmasCoords(result, index));
            }
        }

        return coords;
    }

    private static GridIndex identifyXmas(XmasGrid grid, SearchDirection direction, GridIndex index, char[] searchChars) {
        int i;

        for (i = 0; i < searchChars.length; i++) {
            int xIndex = index.x + (i * direction.xIncrement);
            int yIndex = index.y + (i * direction.yIncrement);

            if (xIndex < 0 || xIndex >= grid.width || yIndex < 0 || yIndex >= grid.height) {
                return null;
            }

            if (grid.grid.get(yIndex).get(xIndex) != searchChars[i]) {
                break;
            }
        }

        if (i != searchChars.length) {
            return null;
        }
        else {
            return new GridIndex(index.x + (3 * direction.xIncrement), index.y + (3 * direction.yIncrement));
        }
    }

    record XmasGrid (List<List<Character>> grid, int width, int height) { }
    record XmasCoords (GridIndex start, GridIndex end) { }

    record GridIndex(int x, int y) { }

    enum SearchDirection {
        // For XMAS search
        EAST (1, 0),
        NORTH_EAST (1, -1),
        NORTH (0, -1),
        SOUTH_EAST (1, 1),
        SOUTH (0, 1);

        private final int xIncrement;
        private final int yIncrement;

        private SearchDirection(int xIncrement, int yIncrement) {
            this.xIncrement = xIncrement;
            this.yIncrement = yIncrement;
        }
    }
}
