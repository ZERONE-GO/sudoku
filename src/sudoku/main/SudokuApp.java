package sudoku.main;

public class SudokuApp {

    private int[] row = new int[9];
    private int[] col = new int[9];
    private int[][] square = new int[3][3];

    public int[][] sudoku(int[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    row[i] = row[i] ^ bit(board[i][j]);
                    col[j] = col[j] ^ bit(board[i][j]);
                    square[i / 3][j / 3] = square[i / 3][j / 3] ^ bit(board[i][j]);
                }
            }
        }
        run(board, 0, 0);
        return null;
    }

    private void run(int[][] board, int r, int c) {
        if (c == 9) {
            run(board, r + 1, 0);
            return;
        }

        if (r == 9) {
            print(board);
            return;
        }

        if (board[r][c] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check(r, c, i)) {
                    board[r][c] = i;
                    row[r] = row[r] ^ bit(i);
                    col[c] = col[c] ^ bit(i);
                    square[r / 3][c / 3] = square[r / 3][c / 3] ^ bit(i);
                    run(board, r, c + 1);
                    board[r][c] = 0;
                    row[r] = row[r] ^ bit(i);
                    col[c] = col[c] ^ bit(i);
                    square[r / 3][c / 3] = square[r / 3][c / 3] ^ bit(i);
                }
            }
        } else {
            run(board, r, c + 1);
        }
    }

    private boolean check(int r, int c, int v) {
        return (row[r] & bit(v)) == 0 && (col[c] & bit(v)) == 0 && (square[r / 3][c / 3] & bit(v)) == 0;
    }

    private int bit(int i) {
        return 1 << (i - 1);
    }

    private void print(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int[][] board = { { 0, 0, 9, 0, 0, 0, 0, 0, 2 }, { 8, 0, 6, 0, 0, 1, 0, 0, 9 }, { 4, 1, 0, 0, 0, 0, 0, 8, 3 },
                { 0, 0, 0, 1, 3, 0, 0, 9, 0 }, { 0, 2, 0, 6, 0, 0, 3, 0, 0 }, { 0, 0, 0, 9, 7, 0, 0, 6, 0 },
                { 2, 7, 0, 0, 0, 0, 0, 1, 6 }, { 6, 0, 4, 0, 0, 7, 0, 0, 5 }, { 0, 9, 1, 0, 0, 0, 0, 0, 4 } };
        SudokuApp sudoku = new SudokuApp();
        sudoku.sudoku(board);
        System.out.println("OVER");
    }
}
