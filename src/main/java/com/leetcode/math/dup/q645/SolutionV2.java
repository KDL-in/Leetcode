package com.leetcode.math.dup.q645;
/*
 * 645. Set Mismatch
 * 找到重复和缺失
 * https://leetcode.com/problems/set-mismatch/
 * */

/*
结合抑或特性，one pass
Runtime: 3 ms, faster than 57.59% of Java online submissions for Set Mismatch.
Memory Usage: 40.3 MB, less than 87.14% of Java online submissions for Set Mismatch.
* */


class SolutionV2 {
    public int[] findErrorNums(int[] nums) {
        int res[] = new int[2], idx;
        for (int i = 0; i < nums.length; i++) {
            idx = nums[i] < 0 ? -nums[i]-1 : nums[i]-1;
            if (nums[idx] < 0) {
                res[0] = idx + 1;
            } else {
                res[1] ^= idx;
                nums[idx] = -nums[idx];
            }
            res[1] ^= i;
        }
        res[1]+=1;
        return res;
    }
}