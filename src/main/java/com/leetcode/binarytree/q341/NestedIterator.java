package com.leetcode.binarytree.q341;

import sun.security.action.PutAllAction;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/*
* 341. Flatten Nested List Iterator
* 无限制嵌套列表的迭代器
* https://leetcode.com/problems/flatten-nested-list-iterator/
* */

interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}
/*
比较有意思的思路，在hashNext的时候，如果发现首部节点是列表，则展开
并且，最重要的是添加到当前list的头部，这个是神来之笔
此外，为了保证addFirst的效率，另一个技巧是将原来的list直接转为LinkedList
Runtime: 2 ms, faster than 98.58% of Java online submissions for Flatten Nested List Iterator.
Memory Usage: 41.6 MB, less than 35.44% of Java online submissions for Flatten Nested List Iterator.*/
public class NestedIterator implements Iterator<Integer> {
    private LinkedList<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = new LinkedList<>(nestedList);
    }

    @Override
    public Integer next() {
        return list.remove(0).getInteger();
    }

    @Override
    public boolean hasNext() {
        while (list != null && !list.isEmpty() && !list.get(0).isInteger()) {
            List<NestedInteger> cur = list.remove(0).getList();
            for (int i = cur.size() - 1; i >= 0; i--) {
                list.addFirst(cur.get(i));
            }
        }
        return !list.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */