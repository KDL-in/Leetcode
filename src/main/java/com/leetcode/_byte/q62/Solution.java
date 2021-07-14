package com.leetcode._byte.q62;

import java.util.Arrays;

/*
* 62. Unique Paths
* https://leetcode.com/problems/unique-paths/
* */
/*
dfs，超时
* */
class Solution {
    private int res = 0;
    
    public int uniquePaths(int m, int n) {
        dfs(1, 1, m, n);
        return res;
    }
    
    private void dfs(int i, int j, int m, int n){
        if(i == m && j == n){
            res++;
            return;
        }
        if(i + 1 <= m) dfs(i+1, j, m, n);
        if(j + 1 <= n) dfs(i, j+1, m, n);
    }
}
/*
动态规划
$$
dp[i][j] = dp[i-1][j] + dp[i][j-1]
$$

当前点由左边或上边的点导出，基值条件，当i = 0 或j = 0时，只有一条路径
* */
class SolutionV2 {

    public int uniquePaths(int m, int n) {
        int up [] = new int[n];
        Arrays.fill(up, 1);
        for(int i = 1 ; i < m; ++i){
            for(int j = 1; j < n; ++j){
                up[j] = up[j] + up[j-1];
            }
        }
        return up[n-1];
    }


}