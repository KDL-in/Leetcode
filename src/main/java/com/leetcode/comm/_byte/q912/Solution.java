package com.leetcode.comm._byte.q912;
/*
* 912. Sort an Array
* quickSort
* https://leetcode.com/problems/sort-an-array/
*
* */
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(0, nums.length, nums);
        return nums;
    }
    
    private void quickSort(int i, int j, int[]nums){
        if(i + 1 >= j) return;
        int mid = partition(i, j, nums);
        quickSort(i, mid, nums);
        quickSort(mid+1, j, nums);
    }
    
    private int partition(int i, int j, int[] A){
        int key;
        key = i;
        j = j - 1;
        while(i < j){
            while(i < j && A[i] <= A[key]) i++;
            while(A[j] > A[key]) j--;
            if(i < j) swap(i, j, A);
        }
        swap(j, key, A);
        return j;
    }
    
    private void swap(int i, int j, int[] A){
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}