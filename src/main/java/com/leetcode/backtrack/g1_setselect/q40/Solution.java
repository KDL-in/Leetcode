package com.leetcode.backtrack.g1_setselect.q40;

import java.util.ArrayList;
import java.util.List;
/*
* 40. Combination Sum II
* 组合，求目标和，元素不重复
* https://leetcode.com/problems/combination-sum-ii/
* */

/*
对于该问题是错的
这是一种比较直观的子集数实现，对于每个数，把所有可能性能取一遍
但该写法只适用于集合元素没有重复的情况，否则选择的集合会重复
去重可以使用统计给集合元素去重
*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int sum, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        if(sum == 0) res.add(new ArrayList<>(cur));
        else if (sum < 0 || k == nums.length) return;
        else {
            backtrack(nums, sum, k + 1, cur, res);
            cur.add(nums[k]);
            backtrack(nums, sum - nums[k], k + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}