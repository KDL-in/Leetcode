package com.leetcode.datastructure.array.twopoints.q141;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
/*
快慢指针
Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle.
        Memory Usage: 39.5 MB, less than 28.97% of Java online submissions for Linked List Cycle.
*/

public class SolutionV1_1 {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow, fast;
        slow = head; fast = head.next;
        while (slow != null && fast != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }
        return false;
    }
}

