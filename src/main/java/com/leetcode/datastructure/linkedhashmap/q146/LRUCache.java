package com.leetcode.datastructure.linkedhashmap.q146;/*


 * 146. LRU Cache
 * LRU缓存队列实现
 * https://leetcode.com/problems/lru-cache/
 * */

import java.util.HashMap;
import java.util.Map;
/*
使用linked list + map映射list node的地址
其时间复杂度主要来自于map的remove和get，查看源码
remove中hash部分视为O(1)，得到hash点之后可能要面对拉链或者一颗树
前者开销为树长O(K)后者则为O(log K)，考虑到hash map的拉链或树的大小K
在摊还意义上，我们仍然可以期望为O(1)
Runtime: 15 ms, faster than 39.66% of Java online submissions for LRU Cache.
        Memory Usage: 46.9 MB, less than 81.11% of Java online submissions for LRU Cache.
*/

public class LRUCache {
    private Map<Integer, Node> map;
    private BiLinkedList cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        cache = new BiLinkedList();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node cur = removeNode(key);
            addRecently(cur);
            return cur.val;
        }
        return -1;
    }

    private void addRecently(Node cur) {
        cache.addLast(cur);
        map.put(cur.key, cur);
    }

    private Node removeNode(int key) {
        Node cur = map.remove(key);
        cache.remove(cur);
        return cur;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node cur = removeNode(key);
            cur.val = value;
            addRecently(cur);
        } else {
            if (cache.size == capacity) {
                removeLeast();
            }
            addRecently(new Node(key, value));
        }
    }

    private void removeLeast() {
        Node cur = cache.removeFirst();
        map.remove(cur.key);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1}
        lRUCache.get(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(4, 1); // cache is {1=1}
        lRUCache.get(2);
        System.out.println();
    }
}

class BiLinkedList {
    private Node first, last;
    int size;

    public BiLinkedList() {
        first = new Node();
        last = new Node();
        first.next = last;
        last.pre = first;
    }

    public Node addLast(Node cur) {
        last.pre.next = cur;
        cur.pre = last.pre;
        cur.next = last;
        last.pre = cur;
        size++;
        return last.pre;
    }

    public Node removeFirst() {
        Node t = null;
        if (size > 0) {
            t = first.next;
            first.next = first.next.next;
            first.next.pre = first;
            size--;
        }
        return t;
    }

    public Node remove(Node cur) {
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
        size--;
        return cur;
    }

    public int size() {
        return size;
    }

    public void disp() {
        Node cur = first;
        while ((cur = cur.next) != last) {
            System.out.print(cur.val + " ");
        }
        System.out.println();

    }
}

class Node {
    int val, key;
    Node pre, next;

    public Node(int key, int val) {
        this.val = val;
        this.key = key;
    }

    public Node() {
    }
}