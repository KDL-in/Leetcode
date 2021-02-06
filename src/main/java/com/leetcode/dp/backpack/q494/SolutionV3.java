package com.leetcode.dp.backpack.q494;

import com.leetcode.common.array.ArrayTools;


/*
递归版
原始问题的动态规划不好优化，转化为背包问题
A为全部+数，B为全部-数
sum(A) - sum(B) = target
sum(A) = target + sum(B)
sum(A) + sum(A) = target + sum(B) + sum(A)
2 * sum(A) = target + sum(nums)
经过以上变形之后，变成子集问题，选择子集A和为（target + sum(nums)）/2

dp(i,sum)定义为set[0:i]选出子集得到sum的可能性
dp递推式的含义是，dp(i, sum) = 选择i + 不选择i
    dp[i][sum] = dp(i - 1, sum - nums[i], nums) + dp(i - 1, sum, nums);
base，sum==0时直接返回1，否则，i == -1 或者 sum <0 时说明没有可能凑到sum
选择，当前i是否选进集合中，影响状态值sum

* */

// Memory Limit Exceeded
public class SolutionV3 {
    private static int[][] memo;
    public int findTargetSumWays(int[] nums, int S) {
        for (int n : nums)
            S += n;
        if ((S & 1)==1) return 0;
        S >>= 1;
        memo = new int[nums.length][S+1];
        return dp(nums.length-1, S, nums);
    }

    private int dp(int i, int sum, int[] nums) {
        if (sum == 0) return 1;
        if (i==-1 || sum < 0) return 0;
        if (memo[i][sum]!=0) return memo[i][sum];
        memo[i][sum] = dp(i - 1, sum - nums[i], nums) + dp(i - 1, sum, nums);
        return memo[i][sum];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(new SolutionV3().findTargetSumWays(nums, S));
        ArrayTools.disp2DArray(memo);
    }
}
