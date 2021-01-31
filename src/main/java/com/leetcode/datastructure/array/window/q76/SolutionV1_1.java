package com.leetcode.datastructure.array.window.q76;


/*
算法复杂度，O(N N) + O(N M)
其中N，M分别为s，t的长度。
里层如q567，O(N) + O(M),重复N次。
空间复杂度，O(M + K)
K为桶固定常数开销。
Runtime: 883 ms, faster than 5.02% of Java online submissions for Minimum Window Substring.
Memory Usage: 38.8 MB, less than 97.31% of Java online submissions for Minimum Window Substring.
*/

class SolutionV1_1 {
    public String minWindow(String s, String t) {
        Window w = new Window(t);

        for (int n = t.length(); n <= s.length(); ++n) {
            w.init(n);
            for (int i = 0; i < s.length(); ++i) {
                if (i < w.size) {
                    w.add(s.charAt(i));
                } else {
                    w.remove(s.charAt(i - w.size));
                    w.add(s.charAt(i));
                }
                if (w.check()) return s.substring(i - w.size + 1, i + 1);
            }
        }
        return "";
    }



    class Window {

        int[] map;
        String target;
        int size, count;

        public Window(String t) {
            target = t;
            map = new int[130];
        }

        public void init(int size) {
            this.count = 0;
            this.size = size;
            for (int i = 'A'; i <= 'z'; ++i) {
                map[i] = 0;
            }
            for (int i = 0; i < target.length(); ++i) {
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

    }
}

