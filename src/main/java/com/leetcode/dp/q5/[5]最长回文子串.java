package com.leetcode.dp.q5;//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4024 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length(), maxLength = 1, maxL = 0;
        if (n == 0) return "";
        boolean[][] isPali = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(isPali[i], false);
            isPali[i][i] = true;
        }
        for (int l = n - 1; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                if (l + 1 == r) isPali[l][r] = s.charAt(l) == s.charAt(r);
                else {
                    isPali[l][r] = isPali[l+1][r-1] &&  s.charAt(l) == s.charAt(r);
                }
                if (isPali[l][r] && (r - l + 1) > maxLength){
                    maxLength = r - l + 1;
                    maxL = l;
                }
//                System.out.print(isPali[l][r] + " ");
            }
//            System.out.println();
        }
//        System.out.println(maxL + " " + maxLength);
        return s.substring(maxL, maxL + maxLength);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
