package com.leetcode._byte.q61链表旋转;

public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        ListNode first, cur, t;
        cur = head;
        int n = 0;
        while (cur != null){
            ++n;
            cur = cur.next;
        }
        k = k % n;
        if (k == 0) return head;
        first = cur = head;
        while( k != 0){
            cur = cur.next;
            k--;
        }
        while( cur.next != null){
            cur = cur.next;
            first = first.next;
        }
        t = first.next;
        first.next = null;
        cur.next = head;
        head = t;
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}