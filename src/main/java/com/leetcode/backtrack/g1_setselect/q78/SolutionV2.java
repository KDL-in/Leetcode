package com.leetcode.backtrack.g1_setselect.q78;

import com.leetcode.backtrack.g1_setselect.q39.Solution;

import java.util.ArrayList;
import java.util.List;
/*
* Runtime: 0 ms, faster than 100.00% of Java online submissions for Subsets.
* Memory Usage: 39.7 MB, less than 16.56% of Java online submissions for Subsets.
* */
public class SolutionV2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        // 包括空的简洁实现方法，相当于在nums后加上null元素
        res.add(new ArrayList<>(cur));

        for (int i = k; i < nums.length; i++) {
            cur.add(nums[i]);
            backtrack(nums, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] candidates = {3, 2, 5};
        System.out.println(new SolutionV2().subsets(candidates));
    }
}
