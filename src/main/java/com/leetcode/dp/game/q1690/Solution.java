package com.leetcode.dp.game.q1690;
/*
* 1690. Stone Game VII
* 石头游戏七
* https://leetcode.com/problems/stone-game-vii/
* */


/*
博弈先手后手的框架，只不过优化目标改为最大化差值，注意，最大化差值，不是最大化获得值
- dp(i, j)定义为[i:j]的石头能获得的最大差值时的分数，包含两值，先手和后手
    $$
    dp(i, j).fir = max(l_sum + dp(i+1, j).sec - dp(i+1, j).fir, r_sum + dp(i, j-1).sec - dp(i, j-1).fir)
    $$
   当前先手得到的分数，等于选择左（或右）得到的分数（剩余石子和）+ 子问题.后手得到的分数。选择左右中差值最大的。
- 状态i，j
- 选择，选择左或右中差值更大的
- base，当i==j时，此时必为Bob的轮次，只剩下一个，去除后只能得到0，return 0

Runtime: 481 ms, faster than 11.09% of Java online submissions for Stone Game VII.
Memory Usage: 107.7 MB, less than 13.77% of Java online submissions for Stone Game VII.
*/

class Solution {
    private int[][][] memo;
    public int stoneGameVII(int[] stones) {
        int n = stones.length, sum = 0;
        memo = new int[n][n][2];
        for (int stone : stones) sum += stone;

        dp(0, n - 1, stones, sum);
        return memo[0][n - 1][0] - memo[0][n - 1][1];
    }

    private int[] dp(int i, int j, int[] stones, int sum) {
        if (i == j)  return memo[i][j];

        if (memo[i][j][0]!=0) return memo[i][j];
        int l_fir, r_fir, l_sec, r_sec;
        l_fir = (sum - stones[i]) + dp(i + 1, j, stones, (sum - stones[i]))[1];
        r_fir = (sum - stones[j]) + dp(i, j - 1, stones, (sum - stones[j]))[1];
        l_sec = memo[i+1][j][0];
        r_sec = memo[i][j-1][0];
        if (l_fir - l_sec > r_fir - r_sec) {
            memo[i][j][0] = l_fir;
            memo[i][j][1] = l_sec;
        } else {
            memo[i][j][0] = r_fir;
            memo[i][j][1] = r_sec;
        }
        // System.out.println(i + " " + j + " " + memo[i][j][0] + " " + memo[i][j][1]);
        return memo[i][j];
    }

    // public static void main(String[] args) {
    //     int[] input = {5, 3, 1, 4, 2};
    //     System.out.println(new Solution().stoneGameVII(input));
    //
    // }

}