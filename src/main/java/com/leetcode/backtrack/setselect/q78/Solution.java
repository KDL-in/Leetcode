package com.leetcode.backtrack.setselect.q78;

import java.util.ArrayList;
import java.util.List;


/*
* 78. Subsets
* 子集树
* https://leetcode.com/problems/subsets/
* */
/*
直观的子集数实现
Runtime: 0 ms, faster than 100.00% of Java online submissions for Subsets.
        Memory Usage: 39.4 MB, less than 32.12% of Java online submissions for Subsets.
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums,0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        if (k == nums.length) res.add(new ArrayList<>(cur));
        else {
            backtrack(nums, k + 1, cur, res);
            cur.add(nums[k]);
            backtrack(nums, k + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}