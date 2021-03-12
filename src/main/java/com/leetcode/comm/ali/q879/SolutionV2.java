package com.leetcode.comm.ali.q879;
/*
这个动态规划有点难，01背包问题

不是很懂为何最后要累加
* */
class SolutionV2 {
    private int MOD = (int)(1e9 + 7);
    private int memo[][][];

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int gn = group.length, p, g;
        memo = new int[gn+1][n+1][minProfit+1];
        memo[0][0][0] = 1;
        for(int k = 1; k <= gn; ++k){
            for(int i = 0; i <= n; ++i){
                g = group[k-1];
                p = profit[k-1];
                for(int j = 0; j <= minProfit; ++j){
                    memo[k][i][j] = memo[k-1][i][j];
                    if(g <= i){
                        memo[k][i][j] = (memo[k-1][i][j] + memo[k-1][i-g][Math.max(0, j - p)])%MOD ;
                    }
                }
            }
        }
                
       int sum = 0;                                                       
        for(int i = 0; i <= n; i++){
            sum = (sum + memo[gn][i][minProfit])%MOD;
        }
        return sum;
    }
    



}