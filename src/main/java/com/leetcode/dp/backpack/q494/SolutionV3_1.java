package com.leetcode.dp.backpack.q494;

import com.leetcode.common.array.ArrayTools;



// 迭代版
// Memory Limit Exceeded
public class SolutionV3_1 {
    private static int[][] memo;
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        for (int x : nums)
            S += x;
        if ((S & 1)==1) return 0;
        S >>= 1;
        memo = new int[n+1][S+1];
        for (int i = 0; i <= n; i++) memo[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int sum = nums[i-1]; sum <= S; sum++) {
                System.out.println(i + " " + sum);
                memo[i][sum] = memo[i - 1][sum] + memo[i - 1][sum - nums[i-1]];
            }
        }
        return memo[n][S];
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(new SolutionV3_1().findTargetSumWays(nums, S));
        ArrayTools.disp2DArray(memo);
    }
}
