package com.leetcode.dp.q673;//给定一个未排序的整数数组，找到最长递增子序列的个数。
//
// 示例 1: 
//
// 
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
// 
//
// 示例 2: 
//
// 
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
// 
//
// 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。 
// Related Topics 树状数组 线段树 数组 动态规划 👍 493 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] len = new int[nums.length];
        int[] cnt = new int[nums.length];
        int ans = 1, maxLen = 1;
        len[0] = 1;
        cnt[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0, count = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[j] > max) {
                        max = len[j];
                        count = cnt[j];
                    } else if (len[j] == max) {
                        count += cnt[j];
                    }
                }
            }
            len[i] = max + 1;
            cnt[i] = count;
            if (len[i] > maxLen) {
                maxLen = len[i];
                ans = cnt[i];
            } else if (len[i] == maxLen) ans += cnt[i];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
