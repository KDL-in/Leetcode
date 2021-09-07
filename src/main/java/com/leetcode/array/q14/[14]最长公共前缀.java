package com.leetcode.array.q14;//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 👍 1753 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int k = 0;
        if (strs.length == 0) return "";
        while (k < strs[0].length()) {
            char c = strs[0].charAt(k);
            for (int i = 1; i < strs.length; i++) {
                if (k == strs[i].length() || strs[i].charAt(k) != c) return strs[0].substring(0, k);
            }
            k++;
        }
        return strs[0].substring(0, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
