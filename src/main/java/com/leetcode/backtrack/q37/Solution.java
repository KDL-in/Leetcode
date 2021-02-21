package com.leetcode.backtrack.q37;

/*
* 37. Sudoku Solver
* 数独游戏
* https://leetcode.com/problems/sudoku-solver/
*
* */

/*
Runtime: 12 ms, faster than 53.71% of Java online submissions for Sudoku Solver.
Memory Usage: 36.2 MB, less than 88.06% of Java online submissions for Sudoku Solver.
* */

class Solution {
    private int N = 9;

    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int i, int j) {
        if (j == N) return backtrack(board, i + 1, 0);
        if (i == N) return true;
        if (board[i][j] != '.') return backtrack(board, i, j + 1);
        for (char c = '1'; c <= '9'; ++c) {
            if (!check(i, j, c, board)) continue;
            board[i][j] = c;
            // 找到直接返回
            if (backtrack(board, i, j)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    private boolean check(int i, int j, char t, char[][] board) {
        int r, c;
        r = i/3 * 3;c = j / 3 * 3;
        for (int k = 0; k < N; k++)
            if (board[i][k] == t||board[k][j] == t||board[r+k%3][c+k/3] == t) return false;
        return true;
    }
}