package com.leetcode.datastructure.array.inplace.q83;


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/*
* 83. Remove Duplicates from Sorted List
* 有序链表去重
* https://leetcode.com/problems/remove-duplicates-from-sorted-list/
* */

/*

Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
        Memory Usage: 38.1 MB, less than 88.26% of Java online submissions for Remove Duplicates from Sorted List.
*/

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) return head;
        ListNode left, right; int pre;
        left = right = head;pre = head.val;
        while (right.next != null) {
            right = right.next;
            if (right.val != pre) {
                left.next = right;
                left = left.next;
            }
            pre = right.val;
        }
        left.next = null;
        return head;
    }
}