package com.leetcode.datastructure.array.twopoints.q142;

/*
* 142. Linked List Cycle II
* 寻找环，并找出起点
* https://leetcode.com/problems/linked-list-cycle-ii/
*
* */

import com.leetcode.common.linklist.ListNode;

/*
快慢指针进阶
快慢指针相遇的时候必有，快指针走过2k步，慢指针走过k步，有如下结论：
1. 则，2k-k = k，快指针比慢指针多走的步数，必为环的整数倍（在环中循环）、
2. 从head和相遇位置出发，经过<k步必定相遇，相遇点为环的起点
Runtime: 0 ms, faster than 100.00% of Java online submissions for Linked List Cycle II.
Memory Usage: 42.3 MB, less than 5.27% of Java online submissions for Linked List Cycle II.
*/

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow, fast;
        slow = head; fast = head.next;
        while (slow != null && fast != null) {
            if (slow == fast) {
                fast = fast.next;
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }
        return null;
    }

    // public static void main(String[] args) {
    //     String input = "3,2,0,-4";
    //     ListNode head = LinkListTools.revBuild(input, ",");
    //     head.next.next.next.next = head.next;
    //     new Solution().detectCycle(head);
    // }
}

