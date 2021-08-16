package com.leetcode.datastructure.array.twopoints.q167;
/*
* 167. Two Sum II - Input array is sorted
* 两数之和等于target
* https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
* */
/*
1. 暴力遍历 + 顺序剪枝
把所有组合都遍历一遍，算法复杂度O(N^2)
2. hash set
标记所有数字，然后
for x in numbers:
    if set[target -  x]: return
同样的思路，由于-1000 <= numbers[i] <= 1000，可以使用int [] set = new int[2000]代替hash
算法复杂度O(N)
空间复杂度O(N)
3. 二分搜索
遍历第一个数字，但第二个数字搜索的时候使用二分搜索
算法复杂度 O(N log N)
4. 双指针
算法复杂度O(N)为什么以下的算法是正确的？
假如正确的解为a，b。那么left必定不会越过a，right必定不会越过b。
应该这么考虑。
right位于b右边的任意位置cur，则nums[cur] > nums[b]
而nums[a] + nums[b] == tar
所以nums[a] + nums[cur] > tar（受到约束条件约束）
所以，left不可能越过a。
Runtime: 0 ms, faster than 100.00% of Java online submissions for Two Sum II - Input array is sorted.
Memory Usage: 39.2 MB, less than 53.31% of Java online submissions for Two Sum II - Input array is sorted.
*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int t, l, r; int[]res;
        res = new int[2];
        for (l = 0, r = numbers.length - 1; ;) {
            t = numbers[l] + numbers[r];
            if (t == target) break;
            else if (t > target) r--;
            else l++;
        }
        res[0] = l + 1;
        res[1] = r + 1;
        return res;
    }
}