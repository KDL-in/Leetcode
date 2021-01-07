package com.leetcode.binarytree.q341;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/*
* 341. Flatten Nested List Iterator
* 无限制嵌套列表的迭代器
* https://leetcode.com/problems/flatten-nested-list-iterator/
* */


interface NestedInteger_2 {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}

/*
视为一个多叉树，直接遍历取出所有数据
Runtime: 2 ms, faster than 98.58% of Java online submissions for Flatten Nested List Iterator.
        Memory Usage: 41.3 MB, less than 81.91% of Java online submissions for Flatten Nested List Iterator.
*/
class NestedIterator_2 implements Iterator<Integer> {

    private List<Integer> list;
    private Iterator<Integer> it;

    public void trav(List<NestedInteger> root) {
        for (NestedInteger n : root) {
            if(n.isInteger()) list.add(n.getInteger());
            else{
                trav(n.getList());
            }
        }
    }
    public NestedIterator_2(List<NestedInteger> nestedList) {
        list = new LinkedList<>();
        trav(nestedList);
        it = list.iterator();
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */