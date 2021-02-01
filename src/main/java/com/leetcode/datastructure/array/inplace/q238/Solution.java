package com.leetcode.datastructure.array.inplace.q238;
/*
* 238. Product of Array Except Self
* 数组累乘，除了当前数，不得使用额外空间，不得用除法
* https://leetcode.com/problems/product-of-array-except-self/
* */

/*
偷用了输出的空间。
Runtime: 2 ms, faster than 29.79% of Java online submissions for Product of Array Except Self
Memory Usage: 57.1 MB, less than 5.75% of Java online submissions for Product of Array Except Self.
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int left, right; int[] output;
        left = right = 1;
        output = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; --i) {
            right *= nums[i];
            output[i] = right;
        }
        for (int i = 0; i < nums.length; i++) {
            left = i > 0 ? left * nums[i - 1] : 1;
            right = i + 1 < nums.length ? output[i + 1] : 1;
            output[i] = left * right;
        }
        return output;

    }
}