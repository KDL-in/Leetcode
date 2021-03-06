package com.leetcode.datastructure.linklist.q234;

import com.leetcode.common.linklist.LinkListTools;
import com.leetcode.common.linklist.ListNode;
/*
* 234. Palindrome Linked List
* 空间复杂度O(1)的回文判断
* https://leetcode.com/problems/palindrome-linked-list/
*
* */
/*
其实就是逆转链表
技巧，快慢指针，取中间值
Runtime: 1 ms, faster than 95.82% of Java online submissions for Palindrome Linked List.
        Memory Usage: 42.1 MB, less than 49.84% of Java online submissions for Palindrome Linked List.
*/

public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow, fast, left, right;
        for (slow = fast = head; fast != null && fast.next != null; slow = slow.next, fast = fast.next.next) ;
        // System.out.println(slow + " " + fast);
        if (fast != null) slow = slow.next;
        for (left = head, right = rev(slow); right != null && right.val == left.val; left = left.next,
                right = right.next);
        return right == null;
    }

    private ListNode rev(ListNode head) {
        ListNode pre, cur, t;
        pre = head; cur = head.next;
        while (cur != null) {
            t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        head.next = null;
        return pre;
    }

    public static void main(String[] args) {
        String input = "1->2->1";
        ListNode head = LinkListTools.revBuild(input, "->");
        System.out.println(new Solution().isPalindrome(head));
    }
}
