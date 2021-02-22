package com.leetcode.dp.other.q312;
/*
* 312. Burst Balloons
* 戳气球，最高分
* https://leetcode.com/problems/burst-balloons/
* */

/*
这道题动态规划很难想，主要是定义比较巧妙。暴力的解法可以比较容易想到全排列的解法。
动态规划的解法体现了子问题的独立性特征，动态规划要求子问题和最优子结构的子问题必须相互独立。
- dp(i,j)，定义为开区间内(i,j)中能取得的最大分数，开区间是一个精彩的技巧，保证了子问题的独立性。
    $$
    dp(i,j) = max(dp(i,k) + dp(k,j) + point(i,k, j)), where i+ 1 <= k < j
    $$
   对于每一组开区间i，j，遍历其中的所有k，k代表最后被戳破的气球，这也是极为精彩的定义
- 状态，i，j，k
- 选择， 戳破i到j中的哪个气球
- base，当i + 1 = j的时候，则此时开区间内没有任何气球，直接返回0

**复杂度**
- 时间复杂度为 O(N N N),由于子问题的求解为O(N)
- 空间，O(N N)

Runtime: 219 ms, faster than 7.52% of Java online submissions for Burst Balloons.
Memory Usage: 40.2 MB, less than 6.32% of Java online submissions for Burst Balloons.
*/

class Solution {
    private int[][] memo;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int nums2[] = new int[n + 2];
        memo = new int[n + 2][n + 2];

        for (int i = 0; i < n; i++) nums2[i + 1] = nums[i];
        nums2[0] = nums2[n + 1] = 1;
        return dp(0, n + 1, nums2);
    }

    private int dp(int i, int j, int[] nums) {
        if (i == j - 1) return 0;
        if (memo[i][j]!=0) return memo[i][j];
        for (int k = i+1; k < j; k++) {
            memo[i][j] = Math.max(memo[i][j],dp(i,k,nums) + dp(k,j,nums) +
                    nums[i] * nums[k] * nums[j]);
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        int nums[] = {3, 1, 5, 8};
        new Solution().maxCoins(nums);
    }
}