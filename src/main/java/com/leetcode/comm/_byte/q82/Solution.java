package com.leetcode.comm._byte.q82;

/*
* 82. Remove Duplicates from Sorted List II
* 链表去重二
* https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
*
* */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy, pre, cur;
        dummy = new ListNode(-101);
        dummy.next = cur = head;
        pre = dummy;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                int dup = cur.val;
                while(cur != null && cur.val == dup){
                    pre.next = cur.next;
                    cur = cur.next;
                }
            } else{
                pre = cur;
                cur = cur.next; 
            }

        }
        return dummy.next;
        
    }
}