package com.leetcode.comm.lcof.q18;

/**
 * 203. Remove Linked List Elements
 * 删除链表中的相同节点，dummy节点
 * https://leetcode.com/problems/remove-linked-list-elements/
 *
 */
class SolutionV2 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        while( head.next != null){
            if(head.next.val == val)head.next = head.next.next;
            else head = head.next;
        }
        return dummy.next;
    }
}