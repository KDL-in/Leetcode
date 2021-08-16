package com.leetcode.datastructure.array.window.q165;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int l, r, max, sum;
        Set<Integer> set = new HashSet<>();
        l = r = max = sum = 0;
        while (r < nums.length) {
            if (!set.contains(nums[r])) {
                sum += nums[r];
                set.add(nums[r++]);
            } else {
                while (set.contains(nums[r])){
                    set.remove(nums[l]);
                    sum -= nums[l++];
                }
                sum += nums[r];
                set.add(nums[r++]);
            }
            max = Math.max(sum, max);
        }
        return max;
    }
}