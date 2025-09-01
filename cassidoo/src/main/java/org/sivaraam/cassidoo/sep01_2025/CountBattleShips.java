package org.sivaraam.cassidoo.sep01_2025;

/**
 * Imagine a simplified version of the game Battleship played on a 2D grid.
 * The grid represents the sea, and each cell can either be empty (.) or
 * contain a part of a ship (X). Ships are placed horizontally or vertically,
 * and there are no adjacent ships. Given a grid, count the number of battleships
 * in it.
 * <p>
 * Example:
 * <p>
 * const ships = [
 *   ['X', 'X', '.', 'X'],
 *   ['.', '.', '.', 'X'],
 *   ['.', '.', '.', 'X'],
 *   ['.', '.', '.', '.'],
 * ];
 * <p>
 * numberOfShips(ships)
 * > 2
 */
public class CountBattleShips {
    public static void main(String[] args) {
        char[][] board = {
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        };

        System.out.println("Total battleships: " + countBattleships(board));
    }

    public static int countBattleships(char[][] board) {
        int x = board[0].length,
            y = board.length;
        int battleShips = 0;

        boolean[][] seen = new boolean[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (!seen[i][j] && board[i][j] == 'X') {
                    markShipAsSeen(board, seen, i, j);
                    battleShips++;
                }
            }
        }

        return battleShips;
    }

    private static void markShipAsSeen(char[][] board, boolean[][] seen, int i, int j) {
        seen[i][j] = true;

        /*
         * Search West
         */
        int offset = 1;
        while (j + offset < board[0].length && board[i][j+offset] == 'X') {
            seen[i][j+offset] = true;
            offset++;
        }

        /*
         * Search South
         */
        offset = 0;
        while (i + offset < board.length && board[i+offset][j] == 'X') {
            seen[i+offset][j] = true;
            offset++;
        }
    }


}
