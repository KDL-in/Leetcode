package com.leetcode.datastructure.queuestack.q678;//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "(*))"
//输出: True
// 
//
// 注意: 
//
// 
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 栈 贪心 字符串 动态规划 👍 401 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
/*
这题不太好考虑周全，原因是*和)的抵消有点错位
*作为(的时候，虽然错位了，但它在)之前出现
*作为)的时候，必须保证下标大于左括号
 */
class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>(), star = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') left.push(i);
            else if (c == '*') star.push(i);
            else {
                if (!left.isEmpty()) {
                    left.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                }else {
                    return false;
                }
            }
        }
        while (!left.isEmpty()) {
            if (star.isEmpty()) return false;
            if (left.pop() > star.pop()) return false;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
