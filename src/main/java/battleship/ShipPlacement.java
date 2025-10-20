package battleship;

public class ShipPlacement {

    public static boolean placeCruiser(char[][] board, char startRow, int startCol, int length, boolean isHorizontal) {
        int row = startRow - 'A';  // Convert char row to 0-based index
        int col = startCol - 1;    // Convert 1-based column to 0-based index

        // First, check if cruiser fits within bounds and doesn't overlap
        for (int i = 0; i < length; i++) {
            int r = isHorizontal ? row : row + i;
            int c = isHorizontal ? col + i : col;

            // Bounds check
            if (r >= board.length || c >= board[0].length || board[r][c] != ' ') {
                return false;
            }
        }

        // Place the cruiser
        for (int i = 0; i < length; i++) {
            int r = isHorizontal ? row : row + i;
            int c = isHorizontal ? col + i : col;
            board[r][c] = 'C';
        }

        return true;
    }


    public static boolean placeBattleship(char[][] board, char startRow, int startCol) {
        int row = startRow - 'A';   // 0-based index
        int col = startCol - 1;     // 0-based index

        // Check bounds first (2x2 square)
        if (row + 1 >= board.length || col + 1 >= board[0].length) return false;

        // Check if all 4 positions are empty
        if (board[row][col] != ' ' || board[row][col + 1] != ' ' ||
                board[row + 1][col] != ' ' || board[row + 1][col + 1] != ' ') {
            return false;
        }

        // Place battleship ('B') on all 4 cells
        board[row][col] = 'B';
        board[row][col + 1] = 'B';
        board[row + 1][col] = 'B';
        board[row + 1][col + 1] = 'B';

        return true;
    }


    public static boolean placeSubmarine(char[][] board, char startRow, int startCol, boolean isTopLeftToBottomRight) {
        int row = startRow - 'A';     // 'F' -> 5
        int col = startCol - 1;       // 4 -> 3

        // Check that all positions are within bounds and unoccupied
        for (int i = 0; i < 3; i++) {
            int r = isTopLeftToBottomRight ? row + i : row - i;
            int c = col + i;

            if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
                return false;
            }

            if (board[r][c] != ' ') {
                return false;
            }
        }

        // Place the submarine
        for (int i = 0; i < 3; i++) {
            int r = isTopLeftToBottomRight ? row + i : row - i;
            int c = col + i;
            board[r][c] = 'S';
        }

        return true;
    }

}
