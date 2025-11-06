package org.sivaraam.cassidoo.nov03_2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PossibleKnightMovesTest {
    @Test
    public void knightMovesFromSomePosition() {
        var position = new PossibleKnightMoves.BoardPosition(1, 2);
        var possibleMoves = PossibleKnightMoves.possibleKnightMoves(position);
        assertEquals(6, possibleMoves.size());
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(0, 0)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(0, 4)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(2, 0)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(2, 4)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(3, 1)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(3, 3)));
    }

    @Test
    public void knightMovesFromCenterOfBoard() {
        var position = new PossibleKnightMoves.BoardPosition(4, 4);
        var possibleMoves = PossibleKnightMoves.possibleKnightMoves(position);
        assertEquals(8, possibleMoves.size());
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(2, 3)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(2, 5)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(3, 2)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(3, 6)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(5, 2)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(5, 6)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(6, 3)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(6, 5)));
    }

    @Test
    public void knightMovesFromCornerOfBoard() {
        var position = new PossibleKnightMoves.BoardPosition(0, 0);
        var possibleMoves = PossibleKnightMoves.possibleKnightMoves(position);
        assertEquals(2, possibleMoves.size());
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(1, 2)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(2, 1)));
    }

    @Test
    public void knightMovesFromEdgeOfBoard() {
        var position = new PossibleKnightMoves.BoardPosition(0, 4);
        var possibleMoves = PossibleKnightMoves.possibleKnightMoves(position);
        assertEquals(4, possibleMoves.size());
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(1, 2)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(1, 6)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(2, 3)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(2, 5)));
    }

    @Test
    public void knightMovesFromInvalidPosition() {
        var position = new PossibleKnightMoves.BoardPosition(-1, 8);
        var possibleMoves = PossibleKnightMoves.possibleKnightMoves(position);
        assertEquals(0, possibleMoves.size());
    }

    @Test
    public void knightMovesFromPositionWithNoValidMoves() {
        var position = new PossibleKnightMoves.BoardPosition(7, 7);
        var possibleMoves = PossibleKnightMoves.possibleKnightMoves(position);
        assertEquals(2, possibleMoves.size());
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(5, 6)));
        assertTrue(possibleMoves.contains(new PossibleKnightMoves.BoardPosition(6, 5)));
    }

}
