package com.leetcode.backtrack.q22;//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 有效括号组合需满足：左括号必须以正确的顺序闭合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 👍 1975 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        generate(n, n, new StringBuilder());
        return res;
    }

    private void generate(int l, int r, StringBuilder str) {
        if (l == 0 && r == 0) {
            res.add(str.toString());
            return;
        }
        if (l > 0) {
            str.append('(');
            generate(l - 1, r, str);
            str.setLength(str.length() - 1);
        }
        if (r > 0 && l < r) {
            str.append(')');
            generate(l, r - 1, str);
            str.setLength(str.length() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
