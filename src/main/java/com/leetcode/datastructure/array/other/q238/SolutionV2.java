package com.leetcode.datastructure.array.other.q238;
/*
* 238. Product of Array Except Self
* 数组累乘，除了当前数，不得使用额外空间，不得用除法
* https://leetcode.com/problems/product-of-array-except-self/
* */

/*


Runtime: 2 ms, faster than 29.79% of Java online submissions for Product of Array Except Self.
Memory Usage: 49.5 MB, less than 82.16% of Java online submissions for Product of Array Except Self.
*/

class SolutionV2 {
    public int[] productExceptSelf(int[] nums) {
        int[] output; int n;
        output = new int[nums.length];
        n = nums.length - 1;
        if (n > 0){
            output[n] = nums[n];
            for (int i = n - 1; i >= 1; --i)
                output[i] = nums[i] * output[i + 1];
            output[0] = output[1];
            for (int i = 1; i < n; i++) {
                nums[i] *= nums[i - 1];
                output[i] = nums[i - 1] * output[i + 1];
            }
            output[n] = nums[n - 1];
        };
        return output;
    }

}