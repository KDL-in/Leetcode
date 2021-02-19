package com.leetcode.dp.game.q877;

/*
 * 877. Stone Game
 * 石头游戏，博弈游戏
 * https://leetcode.com/problems/stone-game/
 *
 * */
/*
直观的博弈动态规划。
- dp(i, j)定义为先手后手分别能得到的最高分数，先手可以选择左边或者右边其中更大的部分，而后手则取决于先手的选择。
````java
        // 先手选择更大者
        if ((begin = piles[i] + dp(i + 1, j)[1]) > (end = piles[j] + dp(i, j - 1)[1])) {
            memo[i][j][0] = begin;
            // 后手等于下一步的先手
            memo[i][j][1] = dp(i + 1, j)[0];
        } else {
            memo[i][j][0] = end;
            // 后手等于下一步的先手
            memo[i][j][1] = dp(i, j - 1)[0];
        }
````
- 状态，i，j
- 选择，先手选择左或右，影响后手
- base，当i=j时，只剩下一个，此时必定轮到lee，Alex得到的分数为0， return memo[i][j]

Runtime: 52 ms, faster than 9.18% of Java online submissions for Stone Game.
Memory Usage: 49.3 MB, less than 10.75% of Java online submissions for Stone Game.

*/

class SolutionV2 {
    private int[][][] memo;

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        memo = new int[n][n][2];

        return dp(0, n - 1, piles)[0] > dp(0, n - 1, piles)[1];
    }

    private int[] dp(int i, int j, int[] piles) {
        if (j <= i) return memo[i][j];
        if (memo[i][j][0] != 0) return memo[i][j];
        int left, right;
        if ((left = piles[i] + dp(i + 1, j, piles)[1]) > (right = piles[j] + dp(i, j - 1, piles)[1])) {
            memo[i][j][0] = left;
            memo[i][j][1] = dp(i + 1, j, piles)[0];
        } else {
            memo[i][j][0] = right;
            memo[i][j][1] = dp(i, j - 1, piles)[0];
        }
        return memo[i][j];
    }

}
