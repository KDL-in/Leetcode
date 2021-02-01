package com.leetcode.datastructure.array.other.q238;
/*
官方解，同样是左右遍历
巧妙的错位减少了不必要的判断。
Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
        Memory Usage: 50.4 MB, less than 41.52% of Java online submissions for Product of Array Except Self.
*/

public class SolutionV3 {
    public int[] productExceptSelf(int[] nums) {

        int length = nums.length;
        int[] answer = new int[length];

        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }
}

