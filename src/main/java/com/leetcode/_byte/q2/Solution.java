package com.leetcode._byte.q2;

/*
* 2. Add Two Numbers
* https://leetcode.com/problems/add-two-numbers/
* 链表模拟加法
* */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        int t = 0, add = 0;
        dummy.next = l1;
        while(l1 != null && l2 != null){
            t = l1.val + l2.val + add;
            l1.val = t % 10;
            add = t / 10;
            l1 = l1.next;
            l2 = l2.next;
            pre = pre.next;
        }
        if(l1 == null && l2 != null){
            pre.next = l2;
            l1 = l2;
        }
        while(add != 0 && l1 != null){
            t = l1.val + add;
            l1.val = t % 10;
            add = t / 10;
            l1 = l1.next;
            pre = pre.next;
        }
        if(add != 0) pre.next = new ListNode(add);
        return dummy.next;
    }
}