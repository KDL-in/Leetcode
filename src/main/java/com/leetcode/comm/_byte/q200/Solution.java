package com.leetcode.comm._byte.q200;
/*
*  200. Number of Islands
* 岛屿问题
* https://leetcode.com/problems/number-of-islands/
*
* */
/*
连通分量数量
* */
class Solution {
    private boolean flag[][];
    private int dirs[][] = {
            {-1, 0}, {1, 0}, {0, 1}, {0, -1}
    };

    public int numIslands(char[][] grid) {
        int m, n, count;
        count = 0;
        m = grid.length;
        n = grid[0].length;
        flag = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!flag[i][j] && grid[i][j] == '1') {
                    dfs(i, j, grid);
                    count++;
                }

            }
        }
        return count;
    }

    private void dfs(int i, int j, char[][] grid) {
        flag[i][j] = true;
        int x, y;
        for (int k = 0; k < 4; k++) {
            x = i + dirs[k][0];
            y = j + dirs[k][1];
            if (check(x, y, grid)) dfs(x, y, grid);
        }
    }

    private boolean check(int x, int y, char[][] grid) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !flag[x][y] && grid[x][y] == '1';
    }
}