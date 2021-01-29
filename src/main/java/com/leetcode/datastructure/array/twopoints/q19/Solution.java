package com.leetcode.datastructure.array.twopoints.q19;

import com.leetcode.common.linklist.ListNode;


/*
* 19. Remove Nth Node From End of List
* 删除倒数k个节点
* https://leetcode.com/problems/remove-nth-node-from-end-of-list/
* */


/*
Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
        Memory Usage: 37.3 MB, less than 18.60% of Java online submissions for Remove Nth Node From End of List.
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre, l, r;
        pre = l = r = head;
        while (n-- > 1) r = r.next;
        while (r.next != null) {
            pre = l;
            l = l.next;
            r = r.next;
        }
        if (l == head) return l.next;
        pre.next = l.next;
        return head;
    }
}