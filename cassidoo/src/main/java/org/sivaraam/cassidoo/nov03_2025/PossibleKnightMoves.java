package org.sivaraam.cassidoo.nov03_2025;

import java.util.ArrayList;
import java.util.List;

/**
 * Given he current position of a knight as [row, col]
 * in an 8x8 chess board represented as a 2D array,
 * write a function to return all valid moves the
 * knight can make.
 * Extra credit: Do this for every chess piece!
 *
 * <pre>
 * knightMoves([4, 4])
 * > [[2, 3], [2, 5], [3, 2], [3, 6], [5, 2], [5, 6], [6, 3], [6, 5]]
 *
 * knightMoves([0, 0])
 * > [[1, 2], [2, 1]]
 *
 * knightMoves([1, 2])
 * > [[0, 0], [0, 4], [2, 0], [2, 4], [3, 1], [3, 3]]
 * </pre>
 */
public class PossibleKnightMoves {

    public static void main(String[] args) {
        var position = new BoardPosition( 1, 2);
        var positionString = "[%d, %d] ";

        var possibleKnightPositions = possibleKnightMoves(position);

        System.out.println("Possible knight positions for [" + position.x + ", " + position.y + "]:");
        for (BoardPosition knightPosition : possibleKnightPositions) {
            System.out.print(String.format(positionString, knightPosition.x, knightPosition.y));
        }

    }

    public static List<BoardPosition> possibleKnightMoves(BoardPosition position) {
        var possibleKnightPositions = new ArrayList<BoardPosition>();

        if (isValidBoardPosition(position)) {
            for (POSSIBLE_MOVES move : POSSIBLE_MOVES.values()) {
                var newPosition = new BoardPosition(position.x + move.horizontalDiff, position.y + move.verticalDiff);

                if (isValidBoardPosition(newPosition)) {
                    possibleKnightPositions.add(newPosition);
                }
            }
        }

        return possibleKnightPositions;
    }

    private static boolean isValidBoardPosition(BoardPosition newPosition) {
        if (newPosition.x < 0 || newPosition.x > 7) {
            return false;
        }

        return newPosition.y >= 0 && newPosition.y <= 7;
    }

    enum POSSIBLE_MOVES {
        /*
         * ⎯ .
         *   |
         *   |
         *   X
         */
        HIGH_SEVEN (-1, -2),

        /*
         *   . ⎯
         *   |
         *   |
         *   X
         */
        HIGH_SEVEN_MIRROR (1, -2),

        /*
         *   X
         *   |
         *   |
         * ⎯ .
         */
        HIGH_L_MIRROR (-1, 2),

        /*
         *   X
         *   |
         *   |
         *   . ⎯
         */
        HIGH_L (1,2),

        /*
         *  |
         *  . ⎯ ⎯ X
         */
        LOW_L (-2, -1),

        /*
         *  . ⎯ ⎯ X
         *  |
         */
        LOW_SEVEN_MIRROR (-2, 1),

        /*
         *        |
         *  X ⎯ ⎯ .
         */
        LOW_L_MIRROR (2, -1),

        /*
         *  X ⎯ ⎯ .
         *        |
         */
        LOW_SEVEN (2, 1);

        final int horizontalDiff;
        final int verticalDiff;

        POSSIBLE_MOVES(int hDiff, int vDiff) {
            this.horizontalDiff = hDiff;
            this.verticalDiff = vDiff;
        }
    }

    public record BoardPosition(int x, int y) { }

}
