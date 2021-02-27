package com.leetcode.math.dup.q41;

/*
同样对索引做文章，若nums[i]<n则交换到正确的位置上（nums[i]-1），然后扫描一次，找到不在位置上的元素
Runtime: 0 ms, faster than 100.00% of Java online submissions for First Missing Positive.
Memory Usage: 36.5 MB, less than 92.55% of Java online submissions for First Missing Positive.
* */
public class SolutionV3 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length, t, idx;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n ||nums[i] == nums[nums[i]-1]) continue;
            idx = nums[i]-1;
            nums[i] = nums[idx];
            nums[idx] = idx + 1;
            i--;
        }
        for (t = 1; t <= n; ++t) {
            if (nums[t-1]!=t) break;
        }
        return t;
    }
}
