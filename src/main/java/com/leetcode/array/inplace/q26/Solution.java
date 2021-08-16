package com.leetcode.datastructure.array.inplace.q26;
/*
* 26. Remove Duplicates from Sorted Array
*  有序数组去重，不得使用额外空间
* https://leetcode.com/problems/remove-duplicates-from-sorted-array/
*
* */
/*

Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array.
        Memory Usage: 40.4 MB, less than 97.45% of Java online submissions for Remove Duplicates from Sorted Array.
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int i,j;
        for (j = 1, i = 1; j < nums.length; j++) {
            if (nums[j]!=nums[j-1]) nums[i++] = nums[j];
        }
        return nums.length == 0? 0: i;
    }
}