package com.leetcode.datastructure.array.other.q1;

import java.util.HashMap;
import java.util.Map;

/*
* 1. Two Sum
* 求两数之和等于目标
* https://leetcode.com/problems/two-sum/
* */
/*
Runtime: 0 ms, faster than 100.00% of Java online submissions for Two Sum.
Memory Usage: 39.4 MB, less than 27.48% of Java online submissions for Two Sum.
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}