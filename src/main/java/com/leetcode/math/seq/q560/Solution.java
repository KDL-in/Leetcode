package com.leetcode.math.seq.q560;
/*
* 560. Subarray Sum Equals K
* 序列和
* https://leetcode.com/problems/subarray-sum-equals-k/
*
* */



/*
序列前缀和， 适用于需要频繁求取连续区间和
O(n^2)，考虑到sum[i,j]为[i,j)的和，这种组合有n^2/2个，所以应该至少都遍历一次。好像时间复杂度上是无法节省的。
Runtime: 1262 ms, faster than 14.59% of Java online submissions for Subarray Sum Equals K.
        Memory Usage: 41.1 MB, less than 94.29% of Java online submissions for Subarray Sum Equals K.
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count, sum, n;
        count = sum = 0; n = nums.length;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) ++count;
            }
        }
        return count;
    }
}