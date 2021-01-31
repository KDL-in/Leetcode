package com.leetcode.datastructure.array.window.q3;



/*
* 3. Longest Substring Without Repeating Characters
* 不重复的最长字串
* https://leetcode.com/problems/longest-substring-without-repeating-characters/
*
* */

/*
滑动窗口的思想下，非常简单
移动右边，直到出现重复，则开始移动左边，直到不再重复。
时间复杂度 o(n)，hash加持下下，重复判断只需要O(1)
空间复杂度 o（k），k为不同字符个数，常数
Runtime: 5 ms, faster than 81.47% of Java online submissions for Longest Substring Without Repeating Characters.
        Memory Usage: 40.4 MB, less than 21.48% of Java online submissions for Longest Substring Without Repeating Characters.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left, right, size;
        Window w = new Window();
        left = right = size = 0;
        while (right < s.length()) {
            w.add(s.charAt(right++));
            while (w.check()) {
                w.remove(s.charAt(left++));
            }
            size = Math.max(size, w.size);
        }
        return size;
    }

    class Window {

        int[] map;

        int size, idx;

        public Window() {
            map = new int[130];
        }

        public void add(char c) {
            if (++map[c] == 2) idx = c;
            ++size;
        }

        public void remove(char c) {
            --map[c];
            --size;
        }

        public boolean check() {
            if (map[idx] == 2) return true;
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