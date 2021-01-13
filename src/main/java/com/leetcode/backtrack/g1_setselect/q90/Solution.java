package com.leetcode.backtrack.g1_setselect.q90;

import com.leetcode.backtrack.g1_setselect.q78.SolutionV2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
* 90. Subsets II
* 子集数，集合有重复元素，求出子集不得重复
* https://leetcode.com/problems/subsets-ii/
* */
/*
Runtime: 1 ms, faster than 99.52% of Java online submissions for Subsets II.
        Memory Usage: 39.5 MB, less than 33.78% of Java online submissions for Subsets II.
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        // 包括空的简洁实现方法，相当于在nums后加上null元素
        res.add(new ArrayList<>(cur));

        for (int i = k; i < nums.length; i++) {
            if (k < i && nums[i] == nums[i-1]) continue;
            cur.add(nums[i]);
            backtrack(nums, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}