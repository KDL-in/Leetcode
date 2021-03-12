package com.leetcode.comm.q855;

import java.awt.*;
import java.util.*;

/*
 * 855. Exam Room
 * 座位调度
 * https://leetcode.com/problems/exam-room/
 *
 *
 * */

/*
朴素的想法，需要对线段排序，每次取中值距离最短的
这里不能用堆（priority）的原因：
堆的删除需要去先定位元素，O(N)。平衡二叉树set的删除是O(logN)
TreeSet不能add null的原因
TreeSet的底层是TreeMap，是红黑树。
需要比较才能插入，如果没有传入对比器，默认抛出空指针异常，如果传入对比器，对比器需要处理空这种情况（这不是红黑树的责任）。

算法复杂度，seat为平衡树插入，O(logN)，删除为平衡树删除O(logN）
Runtime: 12 ms, faster than 86.07% of Java online submissions for Exam Room.
        Memory Usage: 39.4 MB, less than 84.25% of Java online submissions for Exam Room.
*/

class ExamRoom {
    private TreeSet<int[]> treeSet;
    private Map<Integer, int[]> start, end;
    private int N;

    public ExamRoom(int N) {
        treeSet = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                int d = dist(a) - dist(b);
                return d == 0 ? b[0] - a[0] : d;
            }
        });
        start = new HashMap<>();
        end = new HashMap<>();
        this.N = N;
        addIntv(-1, N);

    }

    private int dist(int[] intv) {
        if (intv[0] == -1) return intv[1];
        if (intv[1] == N) return intv[1] - intv[0] - 1;
        return (intv[1] - intv[0]) / 2;
    }

    private void addIntv(int i, int j) {

        int intv[] = {i, j};
        treeSet.add(intv);
        start.put(i, intv);
        end.put(j, intv);

    }

    public int seat() {
        int intv[];
        int i, j, seat;
        intv = treeSet.last();
        i = intv[0];
        j = intv[1];
        if (i == -1) seat = 0;
        else if (j == N) {
            seat = N - 1;
        } else {
            seat = (j - i) / 2 + i;// 防止越界
        }
        removeIntv(intv);
        addIntv(i, seat);
        addIntv(seat, j);
        return seat;
    }

    private void removeIntv(int[] intv) {
        treeSet.remove(intv);
        start.remove(intv[0]);
        end.remove(intv[1]);
    }

    public void leave(int p) {
        int[] left, right, intv;
        left = end.remove(p);
        right = start.remove(p);
        treeSet.remove(left);
        treeSet.remove(right);
        addIntv(left[0], right[1]);
    }

    public static void main(String[] args) {
        ExamRoom obj = new ExamRoom(10);
    }


}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */