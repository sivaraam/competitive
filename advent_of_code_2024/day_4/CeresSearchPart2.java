import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class CeresSearchPart2 {
    public static void main(String[] args) throws URISyntaxException {
        List<List<Character>> grid = new ArrayList<>();
        int width;
        int height;

        try {
            Scanner scanner = new Scanner(new File(CeresSearchPart2.class.getResource("./day_4_actual_input.txt").toURI()));
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

        // Debugging: Print the grid to verify
//        for (List<Character> rowArray : grid) {
//            System.out.println(rowArray);
//        }

        XmasGrid xmasGrid = new XmasGrid(grid, width, height);
        HashMap<GridIndex, Set<MasCoords>> allCenterCoordsToCoords = new HashMap<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                GridIndex index = new GridIndex(x, y);
                identifyMas(xmasGrid, index, allCenterCoordsToCoords);
            }
        }

        int xmasOccurrences = 0;
        for (GridIndex currentCenter : allCenterCoordsToCoords.keySet()) {
            // Debugging
//            System.out.println("Center: " + currentCenter);
//            System.out.println("MAS coordinates: " + allCenterCoordsToCoords.get(currentCenter));
            int possibleXmas = 0;

            for (MasCoords masCoord : allCenterCoordsToCoords.get(currentCenter)) {
                if (masCoord.start.x != masCoord.end.x && masCoord.start.y != masCoord.end.y) {
                    possibleXmas++;
                }
            }

            if (possibleXmas == 2) {
                xmasOccurrences++;
            }
        }

        System.out.println("XMAS occurences: " + xmasOccurrences);
    }

    private static void identifyMas(XmasGrid grid, GridIndex index, HashMap<GridIndex, Set<MasCoords>> centerCoordToCoords) {
        char indexChar = grid.grid.get(index.y).get(index.x);

        if (indexChar == 'M') {
            char[] searchChars = {'M', 'A', 'S'};
            searchInVariousDirections(grid, index, searchChars, centerCoordToCoords);
        }
        else if (indexChar == 'S') {
            char[] searchChars = {'S', 'A', 'M'};
            searchInVariousDirections(grid, index, searchChars, centerCoordToCoords);
        }
    }

    private static void searchInVariousDirections(XmasGrid grid, GridIndex index, char[] searchChars, HashMap<GridIndex, Set<MasCoords>> centerCoordToCoords) {
        GridIndex result;
        List<SearchDirection> directionsToSearch = List.of(
                SearchDirection.NORTH,
                SearchDirection.NORTH_EAST,
                SearchDirection.EAST,
                SearchDirection.SOUTH_EAST,
                SearchDirection.SOUTH
        );

        for (SearchDirection direction : directionsToSearch) {
            result = identifyMas(grid, direction, index, searchChars);
            if (result != null) {
                GridIndex centerCoord = computeCenterCoord(index, result);
                putCenterCoord(centerCoordToCoords, centerCoord, new MasCoords(index, result));
            }
        }
    }
    private static GridIndex computeCenterCoord(GridIndex start, GridIndex end) {
       return new GridIndex((start.x + end.x) / 2, (start.y + end.y) / 2);
    }

    private static void putCenterCoord(HashMap<GridIndex, Set<MasCoords>> centerCoordToCoords, GridIndex centerCoord, MasCoords newCoord) {
        centerCoordToCoords.compute(centerCoord, (k, v) -> {
            if (v == null) {
                HashSet<MasCoords> newSet = new HashSet<>();
                newSet.add(newCoord);
                return newSet;
            } else {
                v.add(newCoord);
                return v;
            }
        });
    }

    private static GridIndex identifyMas(XmasGrid grid, SearchDirection direction, GridIndex index, char[] searchChars) {
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
            return new GridIndex(index.x + ((searchChars.length - 1) * direction.xIncrement), index.y + ((searchChars.length - 1) * direction.yIncrement));
        }
    }

    record XmasGrid (List<List<Character>> grid, int width, int height) { }
    record MasCoords(GridIndex start, GridIndex end) { }

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
