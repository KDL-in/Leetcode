package com.leetcode.datastructure.mano.q84;//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
// Related Topics 栈 数组 单调栈 👍 1604 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        stack.push(0);
        for (int i = 1; i <= heights.length + 1; ) {
            int cur = i == heights.length + 1 ? 0 : heights[i - 1];
            int last = stack.peek() == 0 ? 0 : heights[stack.peek() - 1];
            if (cur >= last) {
                stack.push(i);
                i++;
            } else {
                stack.pop();
                ans = Math.max(ans, last * (i - stack.peek() - 1));
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
