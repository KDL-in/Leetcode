package com.leetcode.dp.game.q1510;

/*
 * 1510. Stone Game IV
 * 石头游戏四
 * https://leetcode.com/problems/stone-game-iv/
 * */

/*
- dp(n)定义为n个石头时先手能否获胜
    $$
        dp(n) = !dp(n-sq), where sq <= n, sq = i * i, i = 1,2,3,\cdots
    $$
  对于dp(n)，先手胜出的唯一条件是找到dp(n-i*i)先手失败的子问题，则取对应的石子，对该子问题，先手必输
- 状态n
- 选择，找到先手失败的子问题
- base，dp(0)，没有石头可取，return false
Runtime: 16 ms, faster than 65.41% of Java online submissions for Stone Game IV.
Memory Usage: 35.9 MB, less than 90.88% of Java online submissions for Stone Game IV.* */
class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[]memo = new boolean[n + 1];
        int i, sq;
        for (int k = 1; k <= n ; k++) {
            i = 1;
            while ((sq = i*i++) <= k && (memo[k] = memo[k-sq])) ;
            memo[k] = !memo[k];
        }
        return memo[n];
    }
}