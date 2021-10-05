# 框架

回溯的核心特征，做选择。

回溯主要基于递归，它的特点是选择以及撤销选择，非常经典。子问题性质不是很明显。算法复杂度可以从子集数考虑，空间复杂度则一般为递归深度N。

回溯法框架如下：

````java
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
		剪枝
        做选择
        backtrack(路径, 选择列表)
        撤销选择
````

集合选择（子集数，全排列）是典型的回溯法应用，而且它们的算法非常规则，简洁。

以40，求子集组合为target的所有可能，元素不可重复，但可选集合有重复。

````java
public class com.leetcode.dp.q91.Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 排序，方便去重
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int sum, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        // 满足条件加入集合
        if(sum == 0) res.add(new ArrayList<>(cur));
        else if (sum < 0) return;
        else {
            // 选择所有状态
            for (int i = k; i < nums.length; i++) {
                // 可选集合去重
                if (i > k && nums[i] == nums[i-1])continue;
                // 回溯
                cur.add(nums[i]);
                backtrack(nums, sum - nums[i], i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
````

上述算法包含了集合选择题目的所有核心要点（注释）。

- **为何是子集数**？

  事实上这个算法不算直观，递归树也不算直观。但观察状态选择部分，可以发现，对于每个状态，它都有两个分支，选择（加入），或者不选，直接i++。因而本质就是子集数。

- **全排列为何是子集数**，其实唯一的区别就是，全排列每次都考虑所有状态，而子集数选过的之前的状态不再考虑（关键是上述回溯调用的k值）。另外全排列需要标记。

- **子集数去重**，这是个很抽象的点，理解请参考 47 有重复的全排列。它的本质是，对可选集合去重，实际操作就是上述代码的sort和for中的if部分。

- **考虑空集**，请参考题目78，90.

# 问题

## 78. Subsets

子集数

https://leetcode.com/problems/subsets/

````java
/*
* Runtime: 0 ms, faster than 100.00% of Java online submissions for Subsets.
* Memory Usage: 39.7 MB, less than 16.56% of Java online submissions for Subsets.
* */
public class SolutionV2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        // 包括空的简洁实现方法，相当于在nums后加上null元素
        res.add(new ArrayList<>(cur));

        for (int i = k; i < nums.length; i++) {
            cur.add(nums[i]);
            backtrack(nums, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}
````

## 90. Subsets II

子集数，集合有重复元素，求出子集不得重复

https://leetcode.com/problems/subsets-ii/

````java
/*
Runtime: 1 ms, faster than 99.52% of Java online submissions for Subsets II.
        Memory Usage: 39.5 MB, less than 33.78% of Java online submissions for Subsets II.
*/

class com.leetcode.dp.q91.Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        // 包括空的简洁实现方法，相当于在nums后加上null元素
        res.add(new ArrayList<>(cur));

        for (int i = k; i < nums.length; i++) {
            if (k < i && nums[i] == nums[i-1]) continue;
            cur.add(nums[i]);
            backtrack(nums, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}
````



## 46. Permutations

 全排列
 https://leetcode.com/problems/permutations/

````java
/*

Runtime: 2 ms, faster than 49.39% of Java online submissions for Permutations.
        Memory Usage: 39.1 MB, less than 82.56% of Java online submissions for Permutations.
*/

class com.leetcode.dp.q91.Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Boolean> flag = new HashMap<>();

        backtrack(nums,  new ArrayList<>(), res, flag);
        return res;
    }
    private void backtrack(int[] nums,ArrayList<Integer> cur, List<List<Integer>> res, Map<Integer, Boolean> flag) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (flag.getOrDefault(i, false)) continue;
                cur.add(nums[i]);
                flag.put(i, true);
                backtrack(nums, cur, res, flag);
                cur.remove(cur.size() - 1);
                flag.put(i, false);
            }
        }
    }
}
````

## 47 Permutations II

全排列，元素有重复

https://leetcode.com/problems/permutations-ii/

````java
/*
 * 47. Permutations II
 * 全排列
 * https://leetcode.com/problems/permutations-ii/
 * */

/*
(i > 0 && nums[i] == nums[i - 1] && !flag[i-1])该条件是去重的关键
按照类似的调节，排序后的数组重复元素优先只会选择第一个，以[1 1 2]为例子，该递归树被去重为如下，这也是在子集数去重中的原理——每次可选集合没有重复元素
         / | \
        1  1  2
       /|\
      1 1 2
     /|\
    1 1 2
>>>>>>
         / \
        1   2
       / \
      1   2
     / \
    1   2
Runtime: 1 ms, faster than 98.93% of Java online submissions for Permutations II.
Memory Usage: 39.8 MB, less than 38.95% of Java online submissions for Permutations II.
 */

class com.leetcode.dp.q91.Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new ArrayList<>(), res, new boolean[nums.length]);
        return res;
    }

    private void backtrack(int[] nums, ArrayList<Integer> cur, List<List<Integer>> res, boolean[] flag) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (flag[i] || (i > 0 && nums[i] == nums[i - 1] && !flag[i-1])) continue;
                cur.add(nums[i]);
                flag[i] = true;
                backtrack(nums, cur, res, flag);
                cur.remove(cur.size() - 1);
                flag[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        int[] candidates = {1, 1, 5};
        System.out.println(new com.leetcode.dp.q91.Solution().permuteUnique(candidates));
    }
}
````


## 39. Combination Sum
组合，元素无限制，和等于目标值

https://leetcode.com/problems/combination-sum/

````java
/*
更好的形式
Runtime: 3 ms, faster than 78.07% of Java online submissions for Combination Sum.
        Memory Usage: 39.4 MB, less than 44.99% of Java online submissions for Combination Sum.
*/
public class SolutionV2 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> r;
        r = new ArrayList<>();
        backtrack(target, 0, new ArrayList<>(), r, candidates);
        return r;
    }

    private void backtrack(int sum, int k, ArrayList<Object> cur, List<List<Integer>> result, int[] nums) {
        if(sum == 0) result.add(new ArrayList(cur));
        else if(sum < 0) return;
        else{
            for (int i = k; i < nums.length; i++) {
                cur.add(nums[i]);
                backtrack(sum - nums[i], i, cur, result, nums);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
````

## 40. Combination Sum II
组合，求目标和，元素不重复

````java
/*
集合选择框架版本
非常好的子集数实现。
算法复杂度，它的递归树是一个倾斜的递归树，实际上不好直接考虑，一个宽松的上界为元组可重复的版本，即使递归深度为target，
每个节点能选择的为N，因而为O(target ^ N)。从子集数来考虑，该问题的状态空间等于子集数数量，也就是O（2^N).
空间复杂度，递归深度等于O(N)，N为集合大小
Runtime: 3 ms, faster than 81.28% of Java online submissions for Combination Sum II.
        Memory Usage: 39.5 MB, less than 41.18% of Java online submissions for Combination Sum II.
*/

public class SolutionV2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int sum, int k, ArrayList<Integer> cur, List<List<Integer>> res) {
        if(sum == 0) res.add(new ArrayList<>(cur));
        else if (sum < 0) return;
        else {
            for (int i = k; i < nums.length; i++) {
                // 防止组合重复的要点
                if (i > k && nums[i] == nums[i-1])continue;
                cur.add(nums[i]);
                backtrack(nums, sum - nums[i], i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
````

## 131 Palindrome Partitioning

切割字符串，使得所有切分都是回文，求所有可能

https://leetcode.com/problems/palindrome-partitioning/

````java
/*
算法复杂度，这个问题本质上是子集数的问题，所以总共有O(2^N）个节点，每个节点需要O(N)
来判断是否是回文，所以时间复杂度为O(N 2^N)
空间复杂度为递归深度，O(N)
Runtime: 7 ms, faster than 85.32% of Java online submissions for Palindrome Partitioning.
        Memory Usage: 52.9 MB, less than 53.17% of Java online submissions for Palindrome Partitioning.
*/

public class SolutionV2 {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(0, s, new ArrayList<String>(), res);
        return res;
    }

    private void backtrack(int k, String s, ArrayList<String> cur, List<List<String>> res) {
        if (k == s.length()) res.add(new ArrayList<>(cur));
        else {
            for (int i = k; i < s.length(); i++) {
                if (!isPalindrome(s, k, i)) continue;
                cur.add(s.substring(k, i+1));
                backtrack(i + 1, s, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String sub, int low, int high) {
        for (; low < high; low++, high--)
            if (sub.charAt(low) != sub.charAt(high)) return false;
        return true;
    }
}
````



# Ref

- 全部灵感来自 https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)