package com.leetcode.datastructure.array.twopoints.q344;

/*
* 344. Reverse String
* 翻转数组
* https://leetcode.com/problems/reverse-string/
*
* */



/*
while比for快，什么道理。
Runtime: 2 ms, faster than 11.32% of Java online submissions for Reverse String.
Memory Usage: 52.6 MB, less than 6.24% of Java online submissions for Reverse String.
*/

class Solution {
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
}