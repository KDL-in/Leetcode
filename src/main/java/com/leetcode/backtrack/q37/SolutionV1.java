package com.leetcode.backtrack.q37;


/*

Runtime: 4 ms, faster than 86.08% of Java online submissions for Sudoku Solver.
        Memory Usage: 36.4 MB, less than 78.79% of Java online submissions for Sudoku Solver.
*/

public class SolutionV1 {
    private int N = 9;
    private boolean [][][]flag;

    public void solveSudoku(char[][] board) {
        flag = new boolean[N][3][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j]!='.'){
                    mark(i, j, board[i][j], true);
                }
            }
        }
        backtrack(board, 0, 0);
    }


    private boolean backtrack(char[][] board, int i, int j) {
        if (j == N) return backtrack(board, i + 1, 0);
        if (i == N) return true;
        if (board[i][j] != '.') return backtrack(board, i, j + 1);
        for (char c = '1'; c <= '9'; ++c) {
            if (check(i, j, c)) continue;
            board[i][j] = c;
            mark(i, j, c, true);
            // 找到直接返回
            if (backtrack(board, i, j+1)) {
                return true;
            }
            board[i][j] = '.';
            mark(i, j, c, false);
        }
        return false;
    }

    private void mark(int i, int j, char c, boolean b) {
        int ci = c - '1';
        flag[i][0][ci] = flag[j][1][ci] = flag[i / 3 * 3 + j / 3][2][ci] = b;
    }

    private boolean check(int i, int j, char c) {
        int ci = c - '1';
        return flag[i][0][ci] || flag[j][1][ci] || flag[i / 3 * 3 + j / 3][2][ci];
    }

}
