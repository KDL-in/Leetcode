package com.leetcode.datastructure.linklist.q148;//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1258 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import com.leetcode.common.linklist.ListNode;

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
    public ListNode sortList(ListNode head) {
        head = mergeSort(head);
        return head;
    }

    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = findMid(head, head);
        //System.out.println(head.val +  " " + mid.val);
        head = mergeSort(head);
        mid = mergeSort(mid);
        head = merge(head, mid);
        return head;
    }

    public ListNode findMid(ListNode p, ListNode q) {
        ListNode pre = null;
        while(q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        if (pre != null) pre.next = null;
        return p;
    }

    public ListNode merge(ListNode p, ListNode q) {
        ListNode dummy = new ListNode(), cur = dummy;
        while (p != null && q != null) {
            if(p.val <= q.val) {
                cur.next = p;
                p = p.next;
            } else{
                cur.next = q;
                q = q.next;
            }
            cur = cur.next;
        }
        if (p != null) cur.next = p;
        else if (q != null) cur.next = q;
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
