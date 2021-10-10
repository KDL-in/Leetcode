package com.leetcode.array.q670;//给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
//
// 示例 1 : 
//
// 
//输入: 2736
//输出: 7236
//解释: 交换数字2和数字7。
// 
//
// 示例 2 : 
//
// 
//输入: 9973
//输出: 9973
//解释: 不需要交换。
// 
//
// 注意: 
//
// 
// 给定数字的范围是 [0, 10⁸] 
// 
// Related Topics 贪心 数学 👍 194 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 官方解也是这个思路，只不过因为0-9数字范围有限，使得看起来像是O(N)
class Solution {
    public int maximumSwap(int num) {
        char[] nums = (num + "").toCharArray();
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            int maxi = i;
            for (int j = nums.length - 1; j > i; j -- ) {
                if (nums[j] > nums[maxi]){
                    maxi = j;
                }
            }
            if (maxi != i) {
                char t = nums[i];
                nums[i] = nums[maxi];
                nums[maxi] = t;
                flag = true;
            }
            if (flag) break;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = ans * 10 + (nums[i] - '0');
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
