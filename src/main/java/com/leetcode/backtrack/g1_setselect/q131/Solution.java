package com.leetcode.backtrack.g1_setselect.q131;

import java.util.ArrayList;
import java.util.List;

/*
 * 131. Palindrome Partitioning
 * 切割字符串，使得所有切分都是回文，求所有可能
 * https://leetcode.com/problems/palindrome-partitioning/
 * */
/*

Runtime: 7 ms, faster than 85.32% of Java online submissions for Palindrome Partitioning.
        Memory Usage: 52.8 MB, less than 64.33% of Java online submissions for Palindrome Partitioning.
*/

class Solution {
    String sub;

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(0, s, new ArrayList<String>(), res);
        return res;
    }

    private void backtrack(int k, String s, ArrayList<String> cur, List<List<String>> res) {
        if (k == s.length()) res.add(new ArrayList<>(cur));
        else {
            for (int i = k; i < s.length(); i++) {
                if (!isPalindrome(sub = s.substring(k, i + 1))) continue;
                cur.add(sub);
                backtrack(i + 1, s, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String sub) {
        for (int i = 0, j = sub.length() - 1; i < j; i++, j--)
            if (sub.charAt(i) != sub.charAt(j)) return false;
        return true;
    }

    public static void main(String[] args) {
        String input = "aab";
        new Solution().partition(input).forEach(System.out::println);
    }
}