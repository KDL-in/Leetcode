package com.leetcode._byte.q138随机指针复制;

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Node cur, copy;
        cur = head;
        // weave link list
        while(cur != null){
            copy = new Node(cur.val);
            copy.next = cur.next;
            cur.next = copy;
            cur = copy.next;
        }
        // copy random pointer
        cur = head;
        while (cur != null){
            copy = cur.next;
            if (cur.random != null) copy.random = cur.random.next;    
            cur = copy.next;
        }
        // extract copy link
        cur = head;
        head = cur.next;
        while (cur.next.next != null){
            copy = cur.next;
            cur.next = cur.next.next;
            copy.next = copy.next.next;
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }
}