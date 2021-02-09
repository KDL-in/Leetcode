package com.leetcode.dp.greed.q55;

/*
* 55. Jump Game
* 跳远游戏
* https://leetcode.com/problems/jump-game/
*
* */
/*

Runtime: 1 ms, faster than 86.85% of Java online submissions for Jump Game.
        Memory Usage: 41 MB, less than 64.09% of Java online submissions for Jump Game.
*/

class Solution {
    public boolean canJump(int[] nums) {
        int step, l, r, n, maxr;
        step = r = 0;n = nums.length; l = -1;
        while (r < n - 1){
            maxr = r;
            for (int i = l + 1; i <= r; i++) {
                maxr = Math.max(maxr, i + nums[i]);
            }
            if (maxr == r) return false;
            l = r;
            r = maxr;
            step++;
        }

        return true;
    }
}