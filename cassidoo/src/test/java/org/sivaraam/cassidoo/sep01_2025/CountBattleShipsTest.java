package org.sivaraam.cassidoo.sep01_2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBattleShipsTest {

    @Test
    void testSingleBattleShip() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', 'X', '.'},
                {'.', '.', '.'}
        };
        assertEquals(1, CountBattleShips.countBattleships(board));
    }

    @Test
    void testMultipleHorizontalBattleShips() {
        char[][] board = {
                {'X', 'X', '.'},
                {'.', '.', '.'},
                {'X', 'X', 'X'}
        };
        assertEquals(2, CountBattleShips.countBattleships(board));
    }

    @Test
    void testMultipleVerticalBattleShips() {
        char[][] board = {
                {'X', '.', '.'},
                {'X', '.', 'X'},
                {'.', '.', 'X'}
        };
        assertEquals(2, CountBattleShips.countBattleships(board));
    }

    @Test
    void testEdgeCaseSingleRow() {
        char[][] board = {
                {'X', '.', 'X', '.', 'X'}
        };
        assertEquals(3, CountBattleShips.countBattleships(board));
    }

    @Test
    void testEdgeCaseSingleColumn() {
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
    void testEdgeCaseLargeBoard() {
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
    void testEdgeCaseNoBattleships() {
        char[][] board = {
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}
        };
        assertEquals(0, CountBattleShips.countBattleships(board));
    }
}
