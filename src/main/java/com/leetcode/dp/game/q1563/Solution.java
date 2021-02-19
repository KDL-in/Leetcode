package com.leetcode.dp.game.q1563;

import com.leetcode.common.array.ArrayTools;

/*
 * 1563. Stone Game V
 * 石头游戏V，划堆
 * https://leetcode.com/problems/stone-game-v/
 *
 * */
/*
选择，很容易看出来，就是从哪里划分为两个堆。
- dp(i,j)定义为[i，j]能得到的最高分数
            if (l > r)
                dp(i, j) = max(r + dp(k + 1, j))
            else if (l < r)
                dp(i, j) = max(l + dp(i, k));
            else
                dp(i, j) = max(max(dp(k + 1, j) + r, dp(i, k) + l) );
            where i <= k < j
- 状态 i，j
- 选择，从哪里划分为两个堆
- base，当i==j时，说明此时只剩下一个石头，直接return 0
时间复杂度，n个子问题，每个子问题都需要sqrt(n)次，因此，O(n sqrt(n))
Runtime: 180 ms, faster than 46.58% of Java online submissions for Stone Game V.
        Memory Usage: 40 MB, less than 71.99% of Java online submissions for Stone Game V.

*/

class Solution {
    private static int[][] memo;
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length, sum = 0;
        memo = new int[n][n];
        for (int v : stoneValue) sum += v;
        return dp(0, n-1, sum, stoneValue);
    }

    private int dp(int i, int j, int r, int[] stoneValue) {
        if (j == i) return 0;
        if (memo[i][j]!=0) return memo[i][j];
        int l = 0;
        for (int k = i; k < j; k++) {
            l += stoneValue[k];
            r -= stoneValue[k];
            System.out.println(i + " " + k +  " " + (k+1) + " " + j);
            if (l > r) {
                memo[i][j] = Math.max(memo[i][j], r + dp(k + 1, j, r, stoneValue));
            } else if (l < r) {
                memo[i][j] = Math.max(memo[i][j], l + dp(i, k, l, stoneValue));
            } else {
                memo[i][j] = Math.max(memo[i][j], Math.max(dp(k + 1, j, r, stoneValue), dp(i, k, l, stoneValue)) + l);
            }
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        int[] input = {6, 2, 3, 4, 5, 5};
        new Solution().stoneGameV(input);
        ArrayTools.disp2DArray(memo);
    }
}