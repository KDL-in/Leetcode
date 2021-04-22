package com.leetcode.comm._byte.q32;
/*
* 32. Longest Valid Parentheses
* 最长有效括号
* https://leetcode.com/problems/longest-valid-parentheses/
* */
/*
动态规划
很难考虑完整，很难想出来的动态规划解法。

首先，dp(i)定义为以i结束的最长有效括号序列的长度。那么，
dp(i)实际上和前面的子问题有关，具体是：
"...()"，这种形式时dp(i) = dp(i-2) + 2
"...))"，这种形式时，很复杂，例如
"...( (()) )"，需要判断s[(i - dp(i-1, s) -1)]是否是'('
    如果不是，那么说明当前的')'没有配对，所以为0
    如果是，则dp(i) = dp(i-1) + dp(i - dp(i-1) - 2) + 2;
* */


import java.util.Deque;
import java.util.LinkedList;

class Solution {
    
    private int max;
    private int[] memo;
    
    public int longestValidParentheses(String s) {
        max = 0;
        memo = new int[s.length()];
        dp(s.length() - 1, s.toCharArray());
        return max;
        
    }
    
    private int dp(int i, char[] s){
        if (i <= 0 ) return 0;
        if (s[i] == '(') {
            dp(i - 1, s);
            return 0;
        }
        if (memo[i] != 0) return memo[i];
        int idx;
        // "...()"
        if (s[i - 1] == '(') memo[i] = dp(i-2, s) + 2;
        // "...( (()) )"
        else if ((idx = (i - dp(i-1, s) -1)) >= 0 && s[idx] == '('){
            memo[i] = dp(i-1, s) + dp(i - dp(i-1, s) - 2, s) + 2;
        }
        //System.out.println(i + " " + memo[i]);
        max = Math.max(memo[i], max);
        return memo[i];
    }
}
/*
强大的栈解法。核心：栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
    如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的
        「最后一个没有被匹配的右括号的下标」
    如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
* */
class SolutionV2 {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}

