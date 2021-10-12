package com.leetcode.dp.q459;//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
//
// 示例 1: 
//
// 
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
// 
//
// 示例 2: 
//
// 
//输入: "aba"
//
//输出: False
// 
//
// 示例 3: 
//
// 
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
// 
// Related Topics 字符串 字符串匹配 👍 541 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean repeatedSubstringPattern(String s) {

        return kmp(s + s, s);
    }

    public boolean kmp(String s, String pat) {
        int[] next = new int[pat.length() + 1];
        next[0] = -1;
        int i = 0, j = 1;
        while (j < pat.length()) {
            if (pat.charAt(i) == pat.charAt(j)) {
                next[j + 1] = ++i;
                j++;
                continue;
            }
            if (i != 0) {
                i = next[i - 1];
            } else {
                next[j++] = 0;
            }
        }

        i = 0;
        j = 1;
        while (j < s.length() && i < pat.length()) {
            if (s.charAt(j) == s.charAt(i)) {
                i++;
                j++;
                continue;
            }
            if (i != 0) {
                i = next[i - 1];
            } else {
                j++;
            }
        }
        return i == pat.length() && j != s.length();
    }

    // public static void main(String[] args) {
    //     System.out.println(new Solution().kmp("abababababab", "ababab"));
    // }
}
//leetcode submit region end(Prohibit modification and deletion)
