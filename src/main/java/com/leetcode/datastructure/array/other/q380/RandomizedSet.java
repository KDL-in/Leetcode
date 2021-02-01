package com.leetcode.datastructure.array.other.q380;

import java.util.*;

/*
* 380. Insert Delete GetRandom O(1)
* O(1)时间实现删除，插入指定元素，并随机返回集合元素
* https://leetcode.com/problems/insert-delete-getrandom-o1/
* */

/*

Runtime: 22 ms, faster than 20.95% of Java online submissions for Insert Delete GetRandom O(1).
        Memory Usage: 53.2 MB, less than 7.16% of Java online submissions for Insert Delete GetRandom O(1)
*/

class RandomizedSet {

    private List<Integer> list;
    private Map<Integer, Integer> val2Idx;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        val2Idx = new HashMap<>();
        random = new Random();

    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (val2Idx.containsKey(val)) return false;
        list.add(val);
        val2Idx.put(val, list.size() - 1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!val2Idx.containsKey(val)) return false;
        int valIdx, last;
        valIdx = val2Idx.get(val);

        last = list.get(list.size() - 1);
        list.set(valIdx, last);
        list.remove(list.size() - 1);

        val2Idx.put(last, valIdx);
        val2Idx.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = random.nextInt(list.size());
        return list.get(idx);
    }

}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */