package com.leetcode.datastructure.unionset.q130;

/*
dfs，算法复杂度O(MN),MN为宽高
Runtime: 1 ms, faster than 99.67% of Java online submissions for Surrounded Regions.
        Memory Usage: 40.7 MB, less than 91.11% of Java online submissions for Surrounded Regions.
*/

public class SolutionV2 {
    private boolean[][] vis;

    public void solve(char[][] board) {
        if (board.length == 0) return;
        vis = new boolean[board.length][board[0].length];

        for (int r = 0; r < board.length; r++) {
            if(board[r][0] == 'O') dfs(r, 0, board);
            if(board[r][board[0].length - 1] == 'O') dfs(r, board[0].length - 1, board);
        }

        for (int c = 0; c < board[0].length; c++) {
            if(board[0][c]=='O') dfs(0, c, board);
            if(board[board.length - 1][c]=='O') dfs(board.length - 1, c, board);
        }

        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                if (board[r][c] == 'O' && !vis[r][c]) board[r][c] = 'X';

    }

    private void dfs(int r, int c, char[][] board) {
        if (!vis[r][c]) {
            vis[r][c] = true;
            if (r > 0 && board[r - 1][c] == 'O') dfs(r - 1, c, board);
            if (c < board[0].length - 1 && board[r][c + 1] == 'O') dfs(r, c + 1, board);
            if (r < board.length - 1 && board[r + 1][c] == 'O') dfs(r + 1, c, board);
            if (c > 0 && board[r][c - 1] == 'O') dfs(r, c - 1, board);
        }
    }
}
