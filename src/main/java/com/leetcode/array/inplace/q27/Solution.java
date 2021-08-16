package com.leetcode.datastructure.array.inplace.q27;

/*
* 27. Remove Element
* 删除数组中的val，不得使用额外空间
* https://leetcode.com/problems/remove-element/
* */

/*

Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
        Memory Usage: 37.6 MB, less than 68.27% of Java online submissions for Remove Element.
*/

class Solution {
    public int removeElement(int[] nums, int val) {
        int i, j;
        for (i = 0, j = 0; j < nums.length; j++) {
            if (nums[j] != val) nums[i++] = nums[j];
        }
        return i;
    }
}