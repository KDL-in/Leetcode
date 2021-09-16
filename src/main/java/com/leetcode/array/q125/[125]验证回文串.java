package com.leetcode.array.q125;//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 
//
// 示例 1: 
//
// 
//输入: "A man, a plan, a canal: Panama"
//输出: true
//解释："amanaplanacanalpanama" 是回文串
// 
//
// 示例 2: 
//
// 
//输入: "race a car"
//输出: false
//解释："raceacar" 不是回文串
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2 * 10⁵ 
// 字符串 s 由 ASCII 字符组成 
// 
// Related Topics 双指针 字符串 👍 417 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        int p = 0, q = s.length() - 1;
        while (p < q) {
            char a = s.charAt(p);
            char b = s.charAt(q);
            if (!(Character.isLetterOrDigit(a))) {
                p++;
                continue;
            }
            if (!(Character.isLetterOrDigit(b))) {
                q--;
                continue;
            }
            if (Character.toLowerCase(a) != Character.toLowerCase(b)) {
                return false;
            }
            p++;
            q--;

        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
