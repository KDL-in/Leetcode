package com.leetcode._byte.q64;

import java.util.Arrays;
/*
* 64. Minimum Path Sum
* https://leetcode.com/problems/minimum-path-sum/
* 迷宫，最小路径和
* */
/*
dfs + 剪枝，访问某个节点，如果新的路径和大于以前的路径和，说明这条路径不用访问
* */
class Solution {
    private int [][] cost;
    private int [][] d;
    private int n,m;
    public int minPathSum(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        cost = new int[n][m];
        d = new int[][] {
                {0, 1}, {1, 0}
        };
        for(int i = 0; i < n; ++i) Arrays.fill(cost[i], Integer.MAX_VALUE);                     cost[0][0] = grid[0][0];
        dfs(0, 0, grid);
        //System.out.println(cost[n-1][m-1] + " " + cost[n-2][m-1] + " " + cost[n-1][m-2]);
        return cost[n-1][m-1];
    }

    private void dfs(int i, int j, int [][] grid){
        if (cost[i][j] >= cost[n-1][m-1]) return;
        for(int k = 0; k <  2; ++k){
            int x = i + d[k][0], y = j + d[k][1];
            //System.out.println(x + " " + y);
            if(x >= 0 && x < n && y >= 0 && y < m && cost[i][j] + grid[x][y] < cost[x][y]){
                cost[x][y] = cost[i][j] + grid[x][y];
                //System.out.println(x + " " + y + " " + cost[x][y]);
                dfs(x, y, grid);
            }
        }
    }
}
/*
动态规划
$$
dp[i][j] = gird[i][j] + min(dp[i-1][j], dp[i][j-1])
$$

当前点，只能源于上方或左边，取其中小的路径。当i=0或j=0时，此时只有一条路径，视为基值条件。
* */
class SolutionV2 {


    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int [][]cost = new int[n][m];
        cost[0][0] = grid[0][0];
        for (int i = 1; i < n; ++ i) cost[i][0] = cost[i-1][0] + grid[i][0];
        for (int i = 1; i < m; ++ i) cost[0][i] = cost[0][i-1] + grid[0][i];
        for( int i = 1; i < n; ++i){
            for(int j = 1; j < m; ++j){
                cost[i][j] = grid[i][j] + Math.min(cost[i-1][j], cost[i][j-1]);
            }
        }
        return cost[n-1][m-1];
    }


}