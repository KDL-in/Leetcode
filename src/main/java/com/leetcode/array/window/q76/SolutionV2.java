package com.leetcode.datastructure.array.window.q76;


/*
全新框架，单边收缩版本
Runtime: 4 ms, faster than 83.81% of Java online submissions for Minimum Window Substring.
Memory Usage: 40.9 MB, less than 13.46% of Java online submissions for Minimum Window Substring.
*/
public class SolutionV2 {

    public String minWindow(String s, String t) {
        int left, right, rl, rr;
        Window w = new Window(t);
        left = right = 0;
        rl = 0;
        rr = Integer.MAX_VALUE;
        // 移动右边
        while (right < s.length()) {
            w.add(s.charAt(right++));
            // 收缩左边
            if (w.check()) {
                while (w.check()) {
                    w.remove(s.charAt(left++));
                    // System.out.println(left + " " + right);
                }
                if (rr - rl > right - left + 1) {
                    rr = right;
                    rl = left - 1;
                }
            }

        }

        return rr == Integer.MAX_VALUE ? "" : s.substring(rl, rr);
    }

    // public static void main(String[] args) {
    //     System.out.println(new SolutionV2().minWindow("ADOBECODEBANC"
    //             , "ABC"));
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
