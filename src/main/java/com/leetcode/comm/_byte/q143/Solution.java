package com.leetcode.comm._byte.q143;

/*
* 143. Reorder List
* 重排链表顺序
* https://leetcode.com/problems/reorder-list/
*
* */
/*
比较麻烦的链表指针控制题，需要时时注意防止环
首先找到中间节点
逆转后半链表
重写连接
* */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public void reorderList(ListNode head) {
        if(head.next == null) return;
        ListNode p1, p2, t;
        p1 = p2 = head;
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        if(p2 != null) p1 = p1.next;
        //System.out.println("mid: " + p1.val);
        p2 = rev(p1);
        p1.next = null;
        p1 = head;
        while(p2 != null){
            t = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = t;
        }
        p1.next = null;
    }
    
    private ListNode rev(ListNode root){
        if(root.next == null) return root;
        ListNode last = rev(root.next);
        root.next.next = root;
        return last;
    }
}