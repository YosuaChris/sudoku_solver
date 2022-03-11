package sudoku_solver;

/**
 *
 * @author Yosua PC
 */
public class Sudoku_solver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        int[][] board = {
            {3, 1, 8, 5, 0, 0, 0, 0, 6},
            {4, 0, 9, 0, 0, 0, 0, 0, 0},
            {6, 0, 0, 0, 7, 3, 0, 1, 4},
            {7, 0, 0, 4, 9, 5, 3, 8, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 3, 2, 1, 6, 0, 0, 5},
            {2, 3, 0, 8, 5, 0, 0, 0, 7},
            {0, 0, 0, 0, 0, 0, 5, 0, 2},
            {1, 0, 0, 0, 0, 7, 9, 6, 8}
        };
        
        if (solveBoard(board)) {
            System.out.println("Success");
        } else {
            System.out.println("Broken");
        } 
        
        printBoard(board);
        
    }
    
    private static void printBoard(int[][] board){
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row)
                && !isNumberInColumn(board, number, column)
                && !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numbTry = 1; numbTry <= GRID_SIZE; numbTry++) {
                        if (isValidPlacement(board, numbTry, row, column)) {
                            board[row][column] = numbTry;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
