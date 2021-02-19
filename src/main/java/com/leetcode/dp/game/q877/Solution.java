package com.leetcode.dp.game.q877;

/*
 * 877. Stone Game
 * 石头游戏，博弈游戏，取首尾石堆
 * https://leetcode.com/problems/stone-game/
 *
 * */
/*
- dp(i,j)定义为[i,j]Alex先手能获得的最大分数
    $$
    dp(i,j) = max(p[i] + min(dp(i+2, j), dp(i+1,j-1),
                    p[j] + min(dp(i+1, j-1),dp(i,j-2)))
    $$
    其含义是，Alex能选择左右两个堆，即i和j，他的最优化目标是max，而lee则是在alex先手之后，继续选择，它的目标是min让Alex能获得的dp()最小
- 状态i，j
- 选择，i或j，lee的选择
- base，当j < i的是时候，说明二者之间不存在任何石头，直接return 0，而从题目规定石头堆数为偶数，从递推公式来看，不可能出现i=j的情况，不需处理
Runtime: 11 ms, faster than 27.04% of Java online submissions for Stone Game.
Memory Usage: 44.8 MB, less than 18.94% of Java online submissions for Stone Game.

*/

class Solution {
    private int[][] memo;
    public boolean stoneGame(int[] piles) {
        int n = piles.length, sum = 0;
        for (int p : piles) {
            sum += p;
        }
        memo = new int[n][n];
        return dp(0, n - 1, piles) * 2 > sum ;
    }

    private int dp(int i, int j, int[] piles) {
        if (j < i) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] = Math.max(
                piles[i] + Math.min(dp(i + 2, j, piles), dp(i + 1, j - 1, piles)),
                piles[j] + Math.min(dp(i + 1, j - 1, piles), dp(i, j - 2, piles))
        );
        return memo[i][j];
    }

}
