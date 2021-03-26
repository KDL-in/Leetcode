package com.leetcode.comm.lcof.q18;
/*
* 237. Delete Node in a Linked List
* 删除链表当前节点，使用原地替换
* https://leetcode.com/problems/delete-node-in-a-linked-list/
*
* */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}