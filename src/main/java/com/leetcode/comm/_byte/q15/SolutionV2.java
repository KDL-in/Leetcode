package com.leetcode.comm._byte.q15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
O(N^2)的效率，用有序简化组合的逻辑
Runtime: 16 ms, faster than 97.90% of Java online submissions for 3Sum.
Memory Usage: 42.7 MB, less than 83.38% of Java online submissions for 3Sum.
* */
public class SolutionV2 {
    private List<List<Integer>> res;


    public List<List<Integer>> threeSum(int[] nums) {
        int h, l, n, target;
        res = new ArrayList<>();
        Arrays.sort(nums);
        n = nums.length - 2;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            l = i + 1;
            h = n + 1;
            target = -nums[i];
            while (l < h) {
                if (nums[l] + nums[h] == target) {
                    res.add(Arrays.asList(nums[i], nums[l++], nums[h--]));
                    while (l < h && nums[l] == nums[l-1]) ++l;
                    while (l < h && nums[h] == nums[h+1]) --h;
                }else if(nums[l] + nums[h] > target) --h;
                else ++l;
            }
        }

        return res;
    }

}
