package q160;

/*
*
* 160. Intersection of Two Linked Lists
* 两链表的交点
* https://leetcode.com/problems/intersection-of-two-linked-lists/
*
* */
/*
先走一遍，然后对齐两个队列，
* */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int count = 0;
        ListNode p = headA, q = headB, cur = null;
        while(p!=null&& q!=null){
            p = p.next;
            q = q.next;
        }
        if(p != null){
            while(p!=null){
                p = p.next;
                ++count;
            }
            p = headA;
            q = headB;
        } else if (q != null){
            while(q!=null){
                q = q.next;
                ++count;
            }
            p = headB;
            q = headA;
        }else{
            p = headA;
            q = headB;            
        }
        
        
        while(count-- > 0) p = p.next;
        while(p != null && p != q){
            p = p.next;
            q = q.next;
        }

        return p;
    }
}