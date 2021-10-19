package com.leetcode.array.twopoints.q16;//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 排序 👍 914 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int abs = Integer.MAX_VALUE;
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int curTarget = target - nums[i];
            int p = i + 1, q = nums.length - 1;
            while (p < q) {
                int sum = nums[p] + nums[q];
                int t = Math.abs(sum - curTarget);
                if (t < abs) {
                    abs = t;
                    ans = sum + nums[i];
                }
                if (sum == curTarget) return target;
                else if (sum > curTarget) q--;
                else p++;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
