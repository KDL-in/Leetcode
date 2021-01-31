package com.leetcode.datastructure.array.window.q567;


/*
* 567. Permutation in String
* s2是否包含s1的全排列
* https://leetcode.com/problems/permutation-in-string/
*
* */

/*
时间复杂度分析：O(N) + O(M)
其中N,M为s1，s2的长度
滑动窗口算法，框架就是滑动的时候加入元素，删除元素。然后检查合法性。
其中算法开销来自合法性检查，借用hash，实现了O(1)的合法性检查
空间复杂度，O(N)
HASH空间开销O(N)
Runtime: 6 ms, faster than 57.07% of Java online submissions for Permutation in String.
        Memory Usage: 39.2 MB, less than 57.61% of Java online submissions for Permutation in String.
*/

class Solution {
    public boolean checkInclusion(String s1, String s2) {

        Window w = new Window(s1);
        for (int i = 0; i < s2.length(); i++) {
            if (i < w.size) {
                w.add(s2.charAt(i));
            } else {
                w.remove(s2.charAt(i - w.size));
                w.add(s2.charAt(i));
            }
            // System.out.println(s2.charAt(i) + " " + w.count);
            // w.disp();
            if (w.check()) return true;
        }
        return false;
    }

    // public static void main(String[] args) {
    //     System.out.println(new Solution().checkInclusion(
    //             "abcdxabcde",
    //             "abcdeabcdx"));
    // }



}

class Window {

    int[] map;
    String tar;
    int size, count;

    public Window(String target) {
        int c;
        tar = target;
        size = target.length();
        map = new int[130];
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
        for (int i = 'a'; i <= 'z'; i++) {
            System.out.print(map[i]+" ");
        }
        System.out.println();
    }
}