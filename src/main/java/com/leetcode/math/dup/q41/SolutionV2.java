package com.leetcode.math.dup.q41;
/*
* 41. First Missing Positive
* 寻找缺失的第一个整数
* https://leetcode.com/problems/first-missing-positive/
*
* */


import java.util.HashSet;
import java.util.Set;

/*
同样利用索引做文章，将nums[i]低于n的打上标记，
但是由于nums中本身有负数，为了避免混淆，需要将原本的负数处理到n之外。
最后再扫描一遍，找到第一个为正的位置。
Runtime: 0 ms, faster than 100.00% of Java online submissions for First Missing Positive.
        Memory Usage: 36.6 MB, less than 74.75% of Java online submissions for First Missing Positive.

*/



class SolutionV2 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length, j;
        for (int i = 0; i < n; i++)
            if (nums[i] <= 0) nums[i] = n + 1;
        for (int i = 0; i < n; i++) {
            int idx = nums[i] < 0 ? -nums[i] - 1 : nums[i] - 1;
            if (idx < n && nums[idx] > 0) nums[idx] = -nums[idx];
        }
        for (j = 1; j <= n; j++) {
            if (nums[j-1] > 0) return j;
        }
        return j;
    }
}