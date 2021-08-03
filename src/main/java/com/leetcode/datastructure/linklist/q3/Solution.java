package com.leetcode.datastructure.linklist.q3;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode t = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return t;
    }
}
class SolutionV2 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p, q, t;
        p = head;
        q = head.next;
        while (q != null){
            t = q.next;
            q.next = p;
            p = q;
            q = t;
        }
        head.next = null;
        return p;
    }
}