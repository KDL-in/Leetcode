package com.leetcode._byte.q15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
* 15. 3Sum
* 三数之和
* https://leetcode.com/problems/3sum/
*
* */
// 超时
class Solution {
    private List<List<Integer>> res;
    private int N;

    public List<List<Integer>> threeSum(int[] nums) {
        res = new ArrayList<>();
        N = 3;
        Arrays.sort(nums);
        backtrack(0, 0, 0, new ArrayList<Integer>(), nums);
        return res;
    }

    private void backtrack(int sum, int i, int k, ArrayList<Integer> cur, int[] nums) {

        if (k == 3) {
            if (sum == 0) res.add(new ArrayList<>(cur));
            return;
        }
        if (i == nums.length) return;
        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j-1]) continue;
            cur.add(nums[j]);
            backtrack(sum - nums[j], j + 1, k + 1, cur, nums);
            cur.remove(k);
        }

    }
}