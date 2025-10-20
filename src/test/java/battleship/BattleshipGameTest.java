package battleship;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleshipGameTest {

    private char[][] battleshipBoard;

    @BeforeEach
    public void setUp() {
        battleshipBoard = new char[10][10];
        // Initialize the board with empty spaces
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                battleshipBoard[row][col] = ' ';
            }
        }
    }

    @Test
    public void testPlaceCruiserSuccessHorizontal() {
        assertTrue(ShipPlacement.placeCruiser(battleshipBoard, 'B', 2, 3, true));
        assertEquals('C', battleshipBoard[1][1]); // B2 -> row 1, col 1
        assertEquals('C', battleshipBoard[1][2]);
        assertEquals('C', battleshipBoard[1][3]);
    }

    @Test
    public void testPlaceCruiserSuccessVertical() {
        assertTrue(ShipPlacement.placeCruiser(battleshipBoard, 'E', 5, 3, false));
        assertEquals('C', battleshipBoard[4][4]); // E5 -> row 4, col 4
        assertEquals('C', battleshipBoard[5][4]);
        assertEquals('C', battleshipBoard[6][4]);
    }

    @Test
    public void testPlaceCruiserOverlap() {
        ShipPlacement.placeCruiser(battleshipBoard, 'B', 2, 3, true);
        assertFalse(ShipPlacement.placeCruiser(battleshipBoard, 'B', 3, 3, false));
    }

    @Test
    public void testPlaceCruiserOutOfBoundsHorizontal() {
        assertFalse(ShipPlacement.placeCruiser(battleshipBoard, 'J', 9, 3, true));
    }

    @Test
    public void testPlaceCruiserOutOfBoundsVertical() {
        assertFalse(ShipPlacement.placeCruiser(battleshipBoard, 'I', 10, 3, false));
    }

    @Test
    public void testPlaceBattleshipSuccess() {
        assertTrue(ShipPlacement.placeBattleship(battleshipBoard, 'D', 4));
        assertEquals('B', battleshipBoard[3][3]); // D4 -> row 3, col 3
        assertEquals('B', battleshipBoard[3][4]);
        assertEquals('B', battleshipBoard[4][3]);
        assertEquals('B', battleshipBoard[4][4]);
    }

    @Test
    public void testPlaceBattleshipOverlap() {
        ShipPlacement.placeBattleship(battleshipBoard, 'D', 4);
        assertFalse(ShipPlacement.placeBattleship(battleshipBoard, 'D', 4));
    }

    @Test
    public void testPlaceBattleshipOutOfBounds() {
        assertFalse(ShipPlacement.placeBattleship(battleshipBoard, 'J', 9));
    }

    @Test
    public void testPlaceSubmarineSuccessTopLeftToBottomRight() {
        assertTrue(ShipPlacement.placeSubmarine(battleshipBoard, 'C', 3, true));
        assertEquals('S', battleshipBoard[2][2]); // C3 -> row 2, col 2
        assertEquals('S', battleshipBoard[3][3]);
        assertEquals('S', battleshipBoard[4][4]);
    }

    @Test
    public void testPlaceSubmarineSuccessBottomLeftToTopRight() {
        assertTrue(ShipPlacement.placeSubmarine(battleshipBoard, 'F', 4, false));
        assertEquals('S', battleshipBoard[5][3]); // F4 -> row 5, col 3
        assertEquals('S', battleshipBoard[4][4]);
        assertEquals('S', battleshipBoard[3][5]);
    }

    @Test
    public void testPlaceSubmarineOutOfBoundsTopLeftToBottomRight() {
        assertFalse(ShipPlacement.placeSubmarine(battleshipBoard, 'I', 9, true));
    }

    @Test
    public void testPlaceSubmarineOutOfBoundsBottomLeftToTopRight() {
        assertFalse(ShipPlacement.placeSubmarine(battleshipBoard, 'B', 2, false));
    }

    @Test
    public void testPlaceSubmarineOverlap() {
        ShipPlacement.placeSubmarine(battleshipBoard, 'C', 3, true);
        assertFalse(ShipPlacement.placeSubmarine(battleshipBoard, 'C', 3, true));
    }

    @Test
    public void testDisplayBoard() {
        ShipPlacement.placeCruiser(battleshipBoard, 'A', 1, 3, true);
        ShipPlacement.placeBattleship(battleshipBoard, 'C', 3);
        BattleshipMainRunner.displayBoard(battleshipBoard);
    }
}
