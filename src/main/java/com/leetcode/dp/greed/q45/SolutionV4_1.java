package com.leetcode.dp.greed.q45;

/*
倒着跳，节省常数时间
Runtime: 399 ms, faster than 11.88% of Java online submissions for Jump Game II.
        Memory Usage: 41.5 MB, less than 26.16% of Java online submissions for Jump Game II.
*/

public class SolutionV4_1 {
    public int jump(int[] nums) {
        int N = nums.length, step = 0,x;
        int maxJump[] = new int[N];
        for (int i = 0; i < N; i++) maxJump[i] = nums[i] + i;
        x = N - 1;
        while (x != 0) {
            for (int i = 0; i < x; i++) {
                if (maxJump[i] >= x) {
                    x = i;
                    ++step;
                    break;
                }
            }
        }
        return step;
    }
}
