package org.sivaraam.cassidoo.sep01_2025;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBattleShipsTest {
    private static Logger log = Logger.getLogger(CountBattleShipsTest.class.getName());

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @Test
    public void testSingleBattleShip() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', 'X', '.'},
                {'.', '.', '.'}
        };
        assertEquals(1, CountBattleShips.countBattleships(board));
    }

    @Test
    public void testMultipleHorizontalBattleShips() {
        char[][] board = {
                {'X', 'X', '.'},
                {'.', '.', '.'},
                {'X', 'X', 'X'}
        };
        assertEquals(2, CountBattleShips.countBattleships(board));
    }

    @Test
    public void testMultipleVerticalBattleShips() {
        char[][] board = {
                {'X', '.', '.'},
                {'X', '.', 'X'},
                {'.', '.', 'X'}
        };
        assertEquals(2, CountBattleShips.countBattleships(board));
    }

    @Test
    public void testEdgeCaseSingleRow() {
        char[][] board = {
                {'X', '.', 'X', '.', 'X'}
        };
        assertEquals(3, CountBattleShips.countBattleships(board));
    }

    @Test
    public void testEdgeCaseSingleColumn() {
        char[][] board = {
                {'X'},
                {'.'},
                {'X'},
                {'.'},
                {'X'}
        };
        assertEquals(3, CountBattleShips.countBattleships(board));
    }

    @Test
    public void testEdgeCaseLargeBoard() {
        char[][] board = {
                {'X', '.', '.', '.', 'X'},
                {'.', 'X', '.', '.', '.'},
                {'.', '.', 'X', '.', 'X'},
                {'X', '.', '.', '.', 'X'},
                {'.', 'X', 'X', '.', '.'}
        };
        assertEquals(7, CountBattleShips.countBattleships(board));
    }

    @Test
    public void testEdgeCaseNoBattleships() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}
        };
        assertEquals(0, CountBattleShips.countBattleships(board));
    }
}
