package com.leetcode.backtrack.g1_setselect.q131;

import java.util.ArrayList;
import java.util.List;

/*
算法复杂度，这个问题本质上是子集数的问题，所以总共有O(2^N）个节点，每个节点需要O(N)
来判断是否是回文，所以时间复杂度为O(N 2^N)
空间复杂度为递归深度，O(N)
Runtime: 7 ms, faster than 85.32% of Java online submissions for Palindrome Partitioning.
        Memory Usage: 52.9 MB, less than 53.17% of Java online submissions for Palindrome Partitioning.
*/

public class SolutionV2 {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(0, s, new ArrayList<String>(), res);
        return res;
    }

    private void backtrack(int k, String s, ArrayList<String> cur, List<List<String>> res) {
        if (k == s.length()) res.add(new ArrayList<>(cur));
        else {
            for (int i = k; i < s.length(); i++) {
                if (!isPalindrome(s, k, i)) continue;
                cur.add(s.substring(k, i+1));
                backtrack(i + 1, s, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String sub, int low, int high) {
        for (; low < high; low++, high--)
            if (sub.charAt(low) != sub.charAt(high)) return false;
        return true;
    }
}
