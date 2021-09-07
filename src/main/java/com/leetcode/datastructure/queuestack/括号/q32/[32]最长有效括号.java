package com.leetcode.datastructure.queuestack.括号.q32;//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 👍 1422 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int [] memo;
    public int longestValidParentheses(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = valid(i, s);
            if (cur > max) {
                max = cur;
            }
            //System.out.print(cur + " ");
        }
        return max * 2;
    }

    private int valid(int i, String s) {
        int r = 0, n = 0, res = 0;
        for (; i >= 0; --i){
            char c = s.charAt(i);
            if (c == ')'){
                r++;
                continue;
            }
            if (r > 0) {
                r --;
                n++;
                if (r == 0) res = n;
            } else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution().longestValidParentheses(")()())()()(");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
