package com.leetcode.backtrack.g1_setselect.q46;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 46. Permutations
* 全排列
* https://leetcode.com/problems/permutations/
* */

/*

Runtime: 2 ms, faster than 49.39% of Java online submissions for Permutations.
        Memory Usage: 39.1 MB, less than 82.56% of Java online submissions for Permutations.
*/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Boolean> flag = new HashMap<>();

        backtrack(nums,  new ArrayList<>(), res, flag);
        return res;
    }
    private void backtrack(int[] nums,ArrayList<Integer> cur, List<List<Integer>> res, Map<Integer, Boolean> flag) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (flag.getOrDefault(i, false)) continue;
                cur.add(nums[i]);
                flag.put(i, true);
                backtrack(nums, cur, res, flag);
                cur.remove(cur.size() - 1);
                flag.put(i, false);
            }
        }
    }
}