package com.leetcode.datastructure.linklist.q445;//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
// 
//
// 示例2： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
// 
//
// 示例3： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
// 
//
// 
//
// 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。 
// Related Topics 栈 链表 数学 👍 428 👎 0


import com.leetcode.common.linklist.ListNode;
//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = rev(l1);
        l2 = rev(l2);
        int add = 0;
        ListNode p = l1, q = l2, dummy = new ListNode(), cur = dummy;
        while ((null != p) && (null != q)) {
            int t = p.val + q.val + add;
            add = t / 10;
            cur.next = new ListNode(t % 10);
            cur = cur.next;
            p = p.next;
            q = q.next;
        }
        while (null != p) {
            int t = p.val + add;
            add = t / 10;
            cur.next = new ListNode(t % 10);
            cur = cur.next;
            p = p.next;
        }
        while (null != q) {
            int t = q.val + add;
            add = t / 10;
            cur.next = new ListNode(t % 10);
            cur = cur.next;
            q = q.next;
        }
        if (add != 0) {
            cur.next = new ListNode(add);
        }
        dummy.next = rev(dummy.next);
        return dummy.next;
    }

    private ListNode rev(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode t = rev(head.next);
        head.next.next = head;
        head.next = null;
        return t;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
