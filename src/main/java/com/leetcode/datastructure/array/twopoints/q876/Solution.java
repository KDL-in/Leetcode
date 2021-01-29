package com.leetcode.datastructure.array.twopoints.q876;

import com.leetcode.common.linklist.ListNode;

/*
* 876. Middle of the Linked List
* 链表中间值
* https://leetcode.com/problems/middle-of-the-linked-list/
*
* */
/*
快慢指针，两倍速，找到中值
Runtime: 0 ms, faster than 100.00% of Java online submissions for Middle of the Linked List.
        Memory Usage: 36.3 MB, less than 64.58% of Java online submissions for Middle of the Linked List.
*/

class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode fast, slow, dummy;
        dummy = new ListNode();
        dummy.next = head;
        fast = slow = dummy;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast == null ? slow : slow.next;
    }
}