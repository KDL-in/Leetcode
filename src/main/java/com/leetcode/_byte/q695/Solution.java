package com.leetcode._byte.q695;
/*
* 695. Max Area of Island
* 最大的陆地，dfs
* https://leetcode.com/problems/max-area-of-island/
*
* */
class Solution {
    private int D[][];
    private boolean vis[][];
    private int max,count;

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null) return 0;
        D = new int[][]{
                {0,1},{0,-1},{1,0},{-1,0}
        };
        vis = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[0].length; ++j){
                if(!vis[i][j] && grid[i][j] == 1){
                    count = 0;
                    dfs(i, j, grid);
                    max = Math.max(count, max);
                }
            }
        }
        return max;
    }

    private void dfs(int i, int j, int[][]grid){
        vis[i][j] = true;
        count ++;
        //System.out.println(i + " " + j + " " + max);
        for (int k = 0; k < 4; ++k){
            int x = i + D[k][0];
            int y = j + D[k][1];
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !vis[x][y] && grid[x][y] == 1)
                dfs(x, y, grid);
        }
    }
}