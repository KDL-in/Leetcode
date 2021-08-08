package com.leetcode.array.twosum.q15;//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 3606 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionV2 {
    private List<List<Integer>> res;
    public List<List<Integer>> threeSum(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, 0, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int[]nums, int k, int sum, List<Integer> cur) {
        if (cur.size() == 3) {
            if (sum == 0) res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = k; i < nums.length; ++i) {
            if (i > k && nums[i] == nums[i - 1]) continue;
            // 最后一个做二分搜索剪枝
            if (cur.size() == 2) {
                if ((i = binarySearch(nums, i, nums.length, -sum)) != -1) {
                    cur.add(nums[i]);
                    sum += nums[i];
                    backtrack(nums, i + 1, sum, cur);
                    cur.remove(cur.size() - 1);
                    sum -= nums[i];
                }
                break;
            }
            cur.add(nums[i]);
            sum += nums[i];
            backtrack(nums, i + 1, sum, cur);
            cur.remove(cur.size() - 1);
            sum -= nums[i];
        }
        return;
    }

    private int binarySearch(int[] nums, int l, int r, int target) {
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < target) l = m + 1;
            else r = m;
        }
        if (l < nums.length && nums[l] == target) return l;
        return -1;
    }
}

class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> threeSum(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = - nums[i];
            twoSum(nums, i + 1, nums.length - 1, target);
        }
        return res;
    }

    private void twoSum(int[] nums, int l, int r, int target) {
        while(l < r) {
            int t = nums[l] + nums[r];
            if (t == target) {
                List<Integer> cur = new ArrayList<>();
                cur.add(-target);
                cur.add(nums[l ++]);
                cur.add(nums[r --]);
                res.add(cur);
                while (l < r && nums[l] == nums[l - 1]) l++;
                while (l < r && nums[r] == nums[r + 1]) r--;
            }
            else if(t > target) r--;
            else l++;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
