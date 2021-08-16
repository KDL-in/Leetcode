package com.leetcode.datastructure.array.window.q567;


/*
全新滑动窗口框架
Runtime: 9 ms, faster than 52.34% of Java online submissions for Permutation in String.
Memory Usage: 40.2 MB, less than 8.87% of Java online submissions for Permutation in String.
* */
public class SolutionV2 {
    public boolean checkInclusion(String s1, String s2) {
        int l,r;
        l = r = 0;
        Window w = new Window(s1);
        while (r < s2.length()) {
            w.add(s2.charAt(r++));
            if (r > w.size) {
                w.remove(s2.charAt(l++));
            }
            // System.out.println(l+ " " + r);
            // w.disp();
            if (w.check()) return true;
        }

        return false;
    }

    // public static void main(String[] args) {
    //     System.out.println(new SolutionV2().checkInclusion(
    //             "a",
    //             "a"));
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
