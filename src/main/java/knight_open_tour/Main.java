package knight_open_tour;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static int[][] chessBoard = new int[8][8];
    public static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    public static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2}; // possible knight moves


    public static void main(String[] args) {
        // Initialize the board with all squares marked as unvisited (0).
        for (int[] row : chessBoard) {
            Arrays.fill(row, 0);
        }

        int startX = 0;
        int startY = 0;

        chessBoard[startX][startY] = 1; // Mark the first move as visited

        if (moveKnight(startX, startY, 2)) {
            System.out.println("\nSolution found:");
            printBoard();
        } else {
            System.out.println("\nNo solution found starting from (" + startX + ", " + startY + ")");
        }

    }

    public static boolean moveKnight(int x, int y, int numMoves) {
        //System.out.println("Enter for next move:");
        //scanner.nextLine();
        // Base case: If all squares have been visited, a solution is found.
        if (numMoves > 64) {
            return true;
        }

        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValidMove(nextX, nextY)) { //filters out the invalid moves, tries next move step
                // If the move is valid, make it.
                chessBoard[nextX][nextY] = numMoves;
                //printBoard();

                if (moveKnight(nextX, nextY, numMoves + 1)) { // Recursion step
                    return true;
                } else chessBoard[nextX][nextY] = 0;
                //printBoard();
                //System.out.println("Invalid position. backtracking...");
                // Backtrack: If the move did not lead to a solution, undo it

            }
        }

        // If no valid moves from the current position lead to a solution, return false.
        return false;
    }

    public static boolean isValidMove(int x, int y) {
        // Check bounds and if the square is unvisited (value is 0).
        return (x >= 0 && x < 8 && y >= 0 && y < 8 && chessBoard[x][y] == 0);
    }

    public static void printBoard() {
        for (int[] row : chessBoard) {
            for (int cell : row) {
                System.out.printf("%4d", cell);
            }
            System.out.println();
        }
    }
}