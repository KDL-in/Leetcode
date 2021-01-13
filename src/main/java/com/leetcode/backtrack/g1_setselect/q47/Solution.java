package com.leetcode.backtrack.g1_setselect.q47;

import java.util.*;
/*
 * 47. Permutations II
 * 全排列
 * https://leetcode.com/problems/permutations-ii/
 * */

/*
(i > 0 && nums[i] == nums[i - 1] && !flag[i-1])该条件是去重的关键
按照类似的调节，排序后的数组重复元素优先只会选择第一个，以[1 1 2]为例子，该递归树被去重为如下，这也是在子集数去重中的原理——每次可选集合没有重复元素
         / | \
        1  1  2
       /|\
      1 1 2
     /|\
    1 1 2
>>>>>>
         / \
        1   2
       / \
      1   2
     / \
    1   2
Runtime: 1 ms, faster than 98.93% of Java online submissions for Permutations II.
Memory Usage: 39.8 MB, less than 38.95% of Java online submissions for Permutations II.
 */

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new ArrayList<>(), res, new boolean[nums.length]);
        return res;
    }

    private void backtrack(int[] nums, ArrayList<Integer> cur, List<List<Integer>> res, boolean[] flag) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (flag[i] || (i > 0 && nums[i] == nums[i - 1] && !flag[i-1])) continue;
                cur.add(nums[i]);
                flag[i] = true;
                backtrack(nums, cur, res, flag);
                cur.remove(cur.size() - 1);
                flag[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        int[] candidates = {1, 1, 5};
        System.out.println(new Solution().permuteUnique(candidates));
    }
}