package com.leetcode.backtrack.g1_setselect.q39;

import java.util.*;
/*
* 39. Combination Sum
* 组合，元素无限制，和等于目标值
* https://leetcode.com/problems/combination-sum/
*
* */

/*
回溯法，每次递归选择当前数要使用多少次
时间复杂度为O(SUM ^ N)，指数级，空间复杂度等于递归树深度，即O(N)

Runtime: 2 ms, faster than 98.86% of Java online submissions for Combination Sum.
Memory Usage: 38.9 MB, less than 92.87% of Java online submissions for Combination Sum.
*/

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> cur;List<List<Integer>> r;
        r = new ArrayList<>();cur = new ArrayList<>();
        comSum(target, 0, cur, r, candidates);
        return r;
    }

    private void comSum(int target, int k, List<Integer> cur, List<List<Integer>> r, int[] candidates) {
        if (target == 0) {
            r.add(new ArrayList<>(cur));
            return;
        }
        if (k == candidates.length) return;
        for (int sum = 0, time = 0; sum <= target; sum += candidates[k], time++) {
            for (int i = 0; i < time; i++) cur.add(candidates[k]);
            comSum(target - sum, k + 1, cur, r, candidates);
            for (int i = 0; i < time; i++) cur.remove(cur.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {3,2,  5};
        System.out.println(new Solution().combinationSum(candidates, 8));

    }
}
