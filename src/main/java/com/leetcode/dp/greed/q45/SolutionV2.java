package com.leetcode.dp.greed.q45;

/*
贪心选择，每次只需要选择可选位置中跳得最远的。因为跳得最远的必然包含其他所有可选跳法的所有下一次可选集合。
Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game II.
Memory Usage: 41.3 MB, less than 32.90% of Java online submissions for Jump Game II.

*/
public class SolutionV2 {

    public int jump(int[] nums) {
        int step, l, r, n, maxr;
        step = r = 0;n = nums.length; l = -1;
        while (r < n - 1){
            maxr = r;
            for (int i = l + 1; i <= r; i++) {
                maxr = Math.max(maxr, i + nums[i]);
            }
            l = r;
            r = maxr;
            step++;
        }

        return step;
    }
}
