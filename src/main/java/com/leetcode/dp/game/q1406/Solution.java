package com.leetcode.dp.game.q1406;

import com.leetcode.common.array.ArrayTools;
/*
* 1406. Stone Game III
* 石头游戏三，选择1，2，3堆
* https://leetcode.com/problems/stone-game-iii/
*
* */
/*
这道题的突破口依然是“选择”，每次可选择1到3堆
- dp(i)定义为面对[i:)的石头，前手后手分别能得到的最高分数
        dp(i)[0] = max(sum(i, j) + dp(j + 1)[1]), where i <= j < i + 3, while
        dp(i)[1] = dp(j + 1)[0]
- 选择，选择从哪里划堆，划分点1 2 3
- 状态， i
- base， 当i = 石头堆数的时候，表示已经没有石堆，能得到的分数为0
Runtime: 84 ms, faster than 17.71% of Java online submissions for Stone Game III.
Memory Usage: 58 MB, less than 30.95% of Java online submissions for Stone Game III.
*/


class Solution {
    private static int[][] memo;
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n+1][2];
        dp(0, stoneValue);
        return memo[0][0] > memo[0][1] ? "Alice" : ((memo[0][0] == memo[0][1]) ? "Tie" : "Bob");
    }

    private int[] dp(int i, int[] stoneValue) {
        if (i  == stoneValue.length) {
            return memo[i];
        }
        if (memo[i][0]!=0) return memo[i];
        int sum = 0, fir;
        memo[i][0] = memo[i][1] = Integer.MIN_VALUE;
        for (int k = 1; k <= 3 && (i + k) <= stoneValue.length; ++k) {
            sum += stoneValue[i + k - 1];
            if ((fir = dp(i + k, stoneValue)[1] + sum) > memo[i][0]) {
                memo[i][0] = fir;
                memo[i][1] = memo[i + k][0];
            }
        }
        return memo[i];
    }
}