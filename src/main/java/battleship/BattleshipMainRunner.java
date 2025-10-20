package battleship;

import static battleship.ShipPlacement.*;

public class BattleshipMainRunner {

    public static void main(String[] args) {
        char[][] battleshipBoard = new char[10][10];
        initBoard(battleshipBoard);

        // Example: Place a cruiser starting at B2, horizontally
        placeCruiser(battleshipBoard, 'B', 2, 3, true);

        // Example: Place a cruiser starting at E5, vertically
        placeCruiser(battleshipBoard, 'E', 5, 3, false);

        // Example: Place a 2x2 ship at D4
        placeBattleship(battleshipBoard, 'H', 7);

        // Example: Place a diagonal ship at C3 (bottom L to top R)
        placeSubmarine(battleshipBoard, 'C', 6, false);

        // Example: Place a diagnonal ship at D1 (top L to bottom R)
        placeSubmarine(battleshipBoard, 'D', 1, true);
        displayBoard(battleshipBoard);
    }

    public static void initBoard(char[][] battleshipBoard) {
        // Init the baord with empty spaces
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                battleshipBoard[row][col] = ' ';
            }
        }
    }

    public static void displayBoard(char[][] battleshipBoard) {
        System.out.println("    1   2   3   4   5   6   7   8   9   10");
        System.out.println("   -----------------------------------------");
        char rowLabel = 'A';
        for (int row = 0; row < 10; row++) {
            System.out.print(rowLabel + " |");
            for (int col = 0; col < 10; col++) {
                System.out.print(" " + battleshipBoard[row][col] + " |");
            }
            System.out.println();
            System.out.println("   -----------------------------------------");
            rowLabel++;
        }
    }


}
