package com.leetcode.datastructure.array.window.q992;
//给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组。
//
// （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。） 
//
// 返回 A 中好子数组的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：A = [1,2,1,2,3], K = 2
//输出：7
//解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
// 
//
// 示例 2： 
//
// 
//输入：A = [1,2,1,3,4], K = 3
//输出：3
//解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length 
// 1 <= K <= A.length 
// 
// Related Topics 数组 哈希表 计数 滑动窗口 
// 👍 308 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return mostK(nums, k) - mostK(nums, k-1);
    }

    private int mostK(int[] nums, int k) {
        int l, r, count, res;
        l = r = res = count = 0;
        int set[] = new int[nums.length + 1];
        while (r < nums.length){
            if (set[nums[r]] == 0) {
                count++;
            }
            set[nums[r]]++;
            while (count > k) {
                if (-- set[nums[l++]] == 0) count--;
            }
            res += r++ - l + 1;
        }
        return  res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
