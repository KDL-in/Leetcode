package com.leetcode.dfs.q200;
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1260 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private boolean vis[][];
    private int d[][];
    public int numIslands(char[][] grid) {
        int r = 0;
        d = new int[][]{
                {1, 0}, {0, 1}, {-1, 0}, {0, -1}
        };
        vis = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !vis[i][j]) {
                    dfs(i, j, grid);
                    r++;
                }
            }
        }
        return r;
    }

    private void dfs(int x, int y, char[][] grid) {
        for (int i = 0; i < 4; i++) {
            int nx = x + d[i][0], ny = y + d[i][1];
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == '1' && !vis[nx][ny]){
                vis[nx][ny] = true;
                dfs(nx, ny, grid);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
