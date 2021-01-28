package com.leetcode.datastructure.head;


public class MaxHeap<E extends Comparable<E>> {
    private E[] elements;
    private int N;

    public MaxHeap(int cap) {
        elements = (E[]) new Comparable[cap + 1];
    }

    public void offer(E e) {
        N++;
        elements[N] = e;
        swim(N);
    }

    public E poll() {
        E e;
        swap(1, N);
        e = elements[N];
        elements[N] = null;
        N--;
        sink(1);
        return null;
    }

    public void sink(int k) {
        int cur, r;
        // 默认切换到左侧，对比左右中的更符合要求者
        while ((cur = left(k)) <= N) {
            if ((r = right(k)) <= N && less(cur, r)) cur = r;
            if (less(cur, k)) break;
            swap(cur, k);
            k = cur;
        }
    }
    public void swim(int k) {
        int p;
        while (k > 1 && less(p = parent(k), k)) {
            swap(p, k);
            k = p;
        }
    }

    private int right(int k) {
        return k << 1 + 1;
    }

    private int left(int k) {
        return k << 1;
    }


    private int parent(int k) {
        return k >> 1;
    }

    public boolean less(int i, int j) {
        return elements[i].compareTo(elements[j]) < 0;
    }

    public void swap(int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(16);
        heap.offer(1);
        heap.offer(2);
        heap.offer(3);
        heap.offer(4);
        heap.offer(5);
        heap.offer(6);
        heap.disp();
    }

    private void disp() {
        for (E e : elements) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}
