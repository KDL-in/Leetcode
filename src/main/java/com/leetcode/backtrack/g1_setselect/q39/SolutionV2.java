package com.leetcode.backtrack.g1_setselect.q39;

import java.util.ArrayList;
import java.util.List;


/*
更好的形式
Runtime: 3 ms, faster than 78.07% of Java online submissions for Combination Sum.
        Memory Usage: 39.4 MB, less than 44.99% of Java online submissions for Combination Sum.
*/

public class SolutionV2 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> r;
        r = new ArrayList<>();
        backtrack(target, 0, new ArrayList<>(), r, candidates);
        return r;
    }

    private void backtrack(int sum, int k, ArrayList<Object> cur, List<List<Integer>> result, int[] nums) {
        if(sum == 0) result.add(new ArrayList(cur));
        else if(sum < 0) return;
        else{
            for (int i = k; i < nums.length; i++) {
                cur.add(nums[i]);
                backtrack(sum - nums[i], i, cur, result, nums);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
