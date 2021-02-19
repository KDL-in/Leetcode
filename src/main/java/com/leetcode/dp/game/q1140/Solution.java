package com.leetcode.dp.game.q1140;
/*
* 1140. Stone Game II
* 石头游戏II
* https://leetcode.com/problems/stone-game-ii/
*
* */
/*
这道题的突破口依然是“选择”，从哪里划堆的问题
- dp(i,M)定义为面对[i:)的石头，前手后手分别能得到的最高分数
        M' = max(M, (j - i) + 1)
        dp(i,M)[0] = max(sum(i, j) + dp(j + 1, M')[1]), where i <= j < i + 2M, while
        dp(i,M)[1] = dp(j + 1, M')[0]
- 选择，选择从哪里划堆，划分点1 <= x <= 2M
- 状态， i，M
- base， 基值条件比较特殊，当剩下的石头能够一次取完，则直接取完即可

Runtime: 24 ms, faster than 19.72% of Java online submissions for Stone Game II.
        Memory Usage: 39.1 MB, less than 25.51% of Java online submissions for Stone Game II.

*/

class Solution {
    private int[][][] memo;
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        memo = new int[n][n+1][2];
        return dp(0, 1, piles)[0];
    }

    private int[] dp(int i, int M, int[] piles) {
        int N;
        if ((N = (i + (M << 1))) >= piles.length) {
            for (int j = i; j < piles.length; j++) memo[i][M][0] += piles[j];
            return memo[i][M];
        }
        if (memo[i][M][1] != 0) return memo[i][M];
        int sum = 0, x, fir;
        for (int j = i; j < N; ++j) {
            sum += piles[j];
            x = Math.max(M, (j - i) + 1);
            if ((fir = sum + dp(j + 1, x, piles)[1]) > memo[i][M][0]) {
                memo[i][M][0] = fir;
                memo[i][M][1] = memo[j+1][x][0];
            }
        }
        return memo[i][M];
    }

}