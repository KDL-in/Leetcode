package com.leetcode.comm._byte.q31;

import java.util.Arrays;
/*
* 31. Next Permutation
* 下一个排列
* https://leetcode.com/problems/next-permutation/
* */
/*
二次扫描法。这个算法非常精巧。
https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
- 找到逆序i
- 找到大于nums[i]的最右边的nums[j]
- 交换i和j
- i右边的序列翻转
* */
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length, i, j;

        for(i = n - 2; i >= 0; --i){
            if(nums[i] < nums[i + 1]) break;
        }
        if(i < 0) {
            Arrays.sort(nums);
            return;
        }
        for(j = n - 1; j >= i; --j){
            if(nums[j] > nums[i]) break;
        }

        swap(i, j, nums);
        i = i + 1;
        j = n - 1;
        while(i < j) swap(i++, j--, nums);
    }
    
    private void swap(int i, int j, int[] nums){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}