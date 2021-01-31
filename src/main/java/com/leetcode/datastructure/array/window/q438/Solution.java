package com.leetcode.datastructure.array.window.q438;

import java.util.ArrayList;
import java.util.List;

/*
 * 438. Find All Anagrams in a String
 * 寻找所有异构字串起始下标
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * */

/*
滑动窗口
时间复杂度，O(N)
滑动遍历N个字符，由于hash，异构字符串判断为O(1)
空间复杂度，O(K)，K为不同字符数
Runtime: 7 ms, faster than 76.50% of Java online submissions for Find All Anagrams in a String.
        Memory Usage: 39.5 MB, less than 98.83% of Java online submissions for Find All Anagrams in a String.
*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int left, right;
        Window w = new Window(p);
        List<Integer> res = new ArrayList<>();
        left = right = 0;
        while (right < s.length()) {
            w.add(s.charAt(right++));
            if (right > w.size) {
                w.remove(s.charAt(left++));
            }
            // w.disp();
            if (w.check()) res.add(left);
            // System.out.println(left + " " + right );
        }
        return res;
    }

    // public static void main(String[] args) {
    //     System.out.println(new Solution().findAnagrams("cbaebabacd",
    //             "abc"));
    // }

    class Window {

        int[] map;

        int size, count;

        public Window(String target) {
            map = new int[130];
            this.size = target.length();
            for (int i = 0; i < size; ++i) {
                if (map[target.charAt(i)]++ == 0) ++count;
            }
        }

        public void add(char c) {
            if (--map[c] == 0) --count;
        }

        public void remove(char c) {
            if (map[c]++ == 0) ++count;
        }

        public boolean check() {
            if (count == 0) return true;
            return false;
        }

        public void disp() {
            for (int i = 'A'; i <= 'z'; i++) {
                System.out.print(map[i] + " ");
            }
            System.out.println();
        }
    }
}