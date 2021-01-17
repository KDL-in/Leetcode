package com.leetcode.datastructure.unionset.q460;

import java.util.HashMap;
import java.util.Map;

/*
* 460. LFU Cache
* LFU缓存实现
* https://leetcode.com/problems/lfu-cache/
* */

/*

Runtime: 28 ms, faster than 35.94% of Java online submissions for LFU Cache.
        Memory Usage: 57.3 MB, less than 10.29% of Java online submissions for LFU Cache.
*/

class LFUCache {
    private Map<Integer, Integer> keyToFreq;
    private Map<Integer, LinkedHashMap_> freqMap;
    private int cap, minFreq;

    public LFUCache(int capacity) {
        keyToFreq = new HashMap<>(capacity);
        freqMap = new HashMap<>();
        cap = capacity;
    }

    public int get(int key) {
        int freq, r;
        Node cur;
        r = -1;
        if (keyToFreq.containsKey(key)) {
            // 更新现有节点的频率
            freq = keyToFreq.get(key);
            cur = freqMap.get(freq).remove(key);
            r = cur.val;
            if (!freqMap.containsKey(freq + 1)) freqMap.put(freq + 1, new LinkedHashMap_());
            freqMap.get(freq + 1).add(cur);
            keyToFreq.put(key, freq + 1);
            // 更新minFreq
            if (freq == minFreq) {
                if (freqMap.get(freq).size() == 0)
                    minFreq = freq + 1;
            }
        }
        // disp();
        return r;
    }

    public void put(int key, int value) {
        if (cap == 0) return;
        int freq;Node cur;
        if (keyToFreq.containsKey(key)) {
            // 更新现有节点的频率和值
            freq = keyToFreq.get(key);
            cur = freqMap.get(freq).remove(key);
            cur.val = value;
            if (!freqMap.containsKey(freq + 1)) freqMap.put(freq + 1, new LinkedHashMap_());
            freqMap.get(freq + 1).add(cur);
            keyToFreq.put(key, freq + 1);
            // 更新minFreq
            if (freq == minFreq) {
                if (freqMap.get(freq).size() == 0)
                    minFreq = freq + 1;
            }
        } else {
            // 删除freq最小的最早的节点
            if (keyToFreq.size() == cap) {
                cur = freqMap.get(minFreq).removeFirst();
                keyToFreq.remove(cur.key);
            }
            // 添加新节点
            if (!freqMap.containsKey(1)) freqMap.put(1, new LinkedHashMap_());
            freqMap.get(1).add(new Node(key, value));
            keyToFreq.put(key, 1);
            // 更新minFreq
            minFreq = 1;
        }
        // disp();
    }

    private void disp() {
        for (Map.Entry<Integer, LinkedHashMap_> en : freqMap.entrySet()) {
            System.out.println(en.getKey() + ": " + en.getValue());
        }
        System.out.println("==============");
    }

    // public static void main(String[] args) {
    //     LFUCache lfu = new LFUCache(2);
    //     lfu.put(1, 1);
    //     lfu.put(2, 2);
    //     lfu.get(1);      // return 1
    //     lfu.put(3, 3);   // evicts key 2
    //     lfu.get(2);      // return -1 (not found)
    //     lfu.get(3);      // return 3
    //     lfu.put(4, 4);   // evicts key 1.
    //     lfu.get(1);      // return -1 (not found)
    //     lfu.get(3);      // return 3
    //     lfu.get(4);      // return 4
    // }
}

class LinkedHashMap_ {
    private BiLinkedList list;
    private Map<Integer, Node> map;

    public LinkedHashMap_() {
        map = new HashMap<>();
        list = new BiLinkedList();
    }


    public Node removeFirst() {
        Node cur = list.removeFirst();
        map.remove(cur.key);
        return cur;
    }

    public void add(Node cur) {
        list.addLast(cur);
        map.put(cur.key, cur);
    }

    public int size() {
        return list.size();
    }

    public Node remove(int key) {
        Node cur = map.get(key);
        list.remove(cur);
        return cur;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

class BiLinkedList {
    private Node first, last;
    private int size;

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

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node cur = first;
        while ((cur = cur.next) != last) {
            str.append(cur.val + " ");
        }

        return str.toString();
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


