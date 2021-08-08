package com.leetcode.array.q215;//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 
// 👍 1205 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int findKthLargest(int [] arr, int k) {
        int l = 0, r = arr.length, cur = 0;
        k = arr.length - k;
        while ((cur = partition(arr, l, r)) != k){
            if (cur > k) r = cur;
            else l = cur + 1;
        }
        return arr[cur];
    }

    public int partition(int [] arr, int lb, int rb) {
        int l = lb, r = rb - 1, k = rb - 1;
        while (l < r) {
            while(r - 1 >= lb && arr[--r] >= arr[k]);
            while(arr[l] < arr[k]) l++;
            if (l < r) swap(arr, l, r);
        }
        swap(arr, l, k);
        return l;
    }

    public void swap(int []arr, int lb, int rb){
        int t = arr[lb];
        arr[lb] = arr[rb];
        arr[rb] = t;
    }
};
//leetcode submit region end(Prohibit modification and deletion)
