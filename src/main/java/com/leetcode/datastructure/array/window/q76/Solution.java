package com.leetcode.datastructure.array.window.q76;


/*
* 76. Minimum Window Substring
* 寻找包含目标字所有字母的最小子串
* https://leetcode.com/problems/minimum-window-substring/
*
* */

/*
算法复杂度，O(N N) + O(N M)
其中N，M分别为s，t的长度。
里层如q567，O(N) + O(M),重复N次。
空间复杂度，O(M + K)
K为桶固定常数开销。
Runtime: 871 ms, faster than 5.02% of Java online submissions for Minimum Window Substring.
Memory Usage: 39.5 MB, less than 54.77% of Java online submissions for Minimum Window Substring.
*/

class Solution {
    public String minWindow(String s, String t) {
        for (int n = t.length(); n <= s.length(); ++n) {
            Window w = new Window(t, n);
            for (int i = 0; i < s.length(); ++i) {
                if (i < w.size) {
                    w.add(s.charAt(i));
                } else {
                    w.remove(s.charAt(i - w.size));
                    w.add(s.charAt(i));
                }
                System.out.println(s.charAt(i));
                w.disp();
                if (w.check()) return s.substring(i-w.size+1,i+1);
            }
            System.out.println("=============");
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("a"
                , "a"));
    }
}

class Window {

    int[] map;

    int size, count;

    public Window(String target, int size) {
        map = new int[130];
        this.size = size;
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

    public void disp() {
        for (int i = 'A'; i <= 'z'; i++) {
            System.out.print(map[i]+" ");
        }
        System.out.println();
    }
}