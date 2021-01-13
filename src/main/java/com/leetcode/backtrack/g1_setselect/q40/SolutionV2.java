package com.leetcode.backtrack.g1_setselect.q40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
集合选择框架版本
非常好的子集数实现。
算法复杂度，它的递归树是一个倾斜的递归树，实际上不好直接考虑，一个宽松的上界为元组可重复的版本，即使递归深度为target，
每个节点能选择的为N，因而为O(target ^ N)。从子集数来考虑，该问题的状态空间等于子集数数量，也就是O（2^N).
空间复杂度，递归深度等于O(N)，N为集合大小
Runtime: 3 ms, faster than 81.28% of Java online submissions for Combination Sum II.
        Memory Usage: 39.5 MB, less than 41.18% of Java online submissions for Combination Sum II.
*/

public class SolutionV2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int sum, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        if(sum == 0) res.add(new ArrayList<>(cur));
        else if (sum < 0) return;
        else {
            for (int i = k; i < nums.length; i++) {
                // 防止组合重复的要点
                if (i > k && nums[i] == nums[i-1])continue;
                cur.add(nums[i]);
                backtrack(nums, sum - nums[i], i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
