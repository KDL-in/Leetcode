### 背包问题

背包问题是给定物品，背包容量，要求取出符合容量最大价值的物品，每个物品的价值为v[i]，重量为w[i]，容量为W。01背包要求物品只能使用一次，完全背包要求物品能使用无限次。

背包问题容易可以想到子集数的解，其状态是当前物品索引i以及剩余容量，考虑当前物品i选不选用的问题。

0-1背包的算法复杂度等于子集数的数量，也就是$O(2^N)$。而完全背包，由于每个状态可以作$K=W/w[i]$中选择，所以暴力求解的复杂度为$O(K^N)$。

完全背包的暴力求解方法不是很好实现，但是以递推式的角度来看，二者几乎一致

0-1背包

`dp(i,w)=max(dp(i-1, w), dp(i-1, w-c[i-1])+ v[i])`

完全背包

`dp(i,w)=max(dp(i-1, w), dp(i, w-c[i-1])+ v[i])`

这非常巧妙，即对于完全背包，即使选用了当前i物品，i也不需要减一，因为它可以多次选用。

从动态规划memo角度来看，二者的时间复杂度必然为子问题的个数$O(NW)$。

#####　416 子集目标和

> 　* 416. Partition Equal Subset Sum
> * 子集目标和
> * https://leetcode.com/problems/partition-equal-subset-sum/

01背包问题，动态规划，压缩
需要简单转化一下，优化目标单一化，sum(A) = sum(set) / 2

- dp(i, sum)定义为set[:i]子集之和为sum的可能，true or false
    dp[i,sum] = dp[i-1, sum - nums[i - 1]] || dp[i-1, sum];
    
- 选择，当前i元素是否考虑，

- 状态，i和sum，影响可能性dp

- base，i==0时，集合没有元素，return 0，sum==0时，只有一种选择满足，那就是什么都不选， return 1

  复杂度 O(N SUM) O(SUM)

````java
/*
子集背包问题，动态规划，压缩
需要简单转化一下，优化目标单一化，sum(A) = sum(set) / 2
- dp(i, sum)定义为set[:i]子集之和为sum的可能，true or false
    dp[i,sum] = dp[i-1, sum - nums[i - 1]] || dp[i-1, sum];
- 选择，当前i元素是否考虑，
- 状态，i和sum，影响可能性dp
- base，i==0时，集合没有元素，return 0，sum==0时，只有一种选择满足，那就是什么都不选， return 1
复杂度 O(N SUM) O(SUM)
Runtime: 22 ms, faster than 70.32% of Java online submissions for Partition Equal Subset Sum.
        Memory Usage: 38.6 MB, less than 81.21% of Java online submissions for Partition Equal Subset Sum.
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0, n = nums.length;
        for (int num : nums) s += num;
        if ((s & 1) ==1) return false;
        s >>= 1;
        boolean[] memo = new boolean[s + 1];
        memo[0] = true;
        for (int i = 1; i < n; i++) {
            for (int sum = s; sum >= nums[i-1]; sum--) {
                memo[sum] = memo[sum - nums[i - 1]] || memo[sum];
            }
        }
        return memo[s];
    }
}
````

##### 518 凑零钱的可能性

> * 518. Coin Change 2
> * 找零可能性，硬币无限
> * https://leetcode.com/problems/coin-change-2/

完全背包问题
- dp(i, sum)，定义为set[:i]个物品下，得到sum的可能性
    dp[i, sum] = dp[i-1, sum] + dp[i, sum - coins[i-1]];
- 状态为i，sum
- 选择，当前i物品是否选用
- base，当sum = 0时，只能全不选，return 1， 当i = 0时，无物品，return 1

````java
/*
完全背包问题
- dp(i, sum)，定义为set[:i]个物品下，得到sum的可能性
    dp[i, sum] = dp[i-1, sum] + dp[i, sum - coins[i-1]];
- 状态为i，sum
- 选择，当前i物品是否选用
- base，当sum = 0时，只能全不选，return 1， 当i = 0时，无物品，return 1
Runtime: 2 ms, faster than 100.00% of Java online submissions for Coin Change 2.
        Memory Usage: 36.1 MB, less than 95.99% of Java online submissions for Coin Change 2.

*/

class Solution {
    public int change(int amount, int[] coins) {
        int[] memo = new int[amount + 1];
        memo[0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int sum = coins[i-1]; sum <= amount; sum++) {
                memo[sum] = memo[sum] + memo[sum - coins[i-1]];
            }
        }
        return memo[amount];
    }
}
````



##### 494 得到目标和的方式

>  * 494. Target Sum
>  * 达到目标和的可能性
>  * https://leetcode.com/problems/target-sum/

```
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
```

转化之后，是一道典型的01背包问题。

**dp分析**

一种更像回溯的暴力动态规划

- dp(i, sum)的定义是，i到N的元素加起来为sum的可能性之和
  dp函数，对应加当前元素或者减去，等于两个子问题的可能性之和
      dp(i, sum) = dp(i+1, sum-cur) + dp(i+1, sum+cur)
- 选择，当i元素+或-，影响子问题目标sum值
- 状态，i和sum
- base，i==N时，没有可选元素，如果sum==0，则有一种可能，如果sum！=0，则返回0

**解法一：回溯、暴力递归**

````java

/*
一种更像回溯的暴力动态规划
dp(i, sum)的定义是，i到N的元素加起来为sum的可能性之和
dp函数，对应加当前元素或者减去，等于两个子问题的可能性之和
    dp(i, sum) = dp(i+1, sum-cur) + dp(i+1, sum+cur)
选择，当i元素+或-，影响子问题目标sum值
base，i==N时，没有可选元素，如果sum==0，则有一种可能，如果sum！=0，则返回0

Runtime: 470 ms, faster than 31.47% of Java online submissions for Target Sum.
Memory Usage: 36.3 MB, less than 96.46% of Java online submissions for Target Sum.
*/

class Solution {
    // private
    public int findTargetSumWays(int[] nums, int S) {
        return dp(0, S, nums);
    }

    private int dp(int i, int sum, int[] nums) {
        if (i == nums.length) return sum == 0 ? 1 : 0;
        return dp(i + 1, sum + nums[i], nums) + dp(i + 1, sum - nums[i], nums);
    }
}
````

**解法二，备忘录**

````java
/*
备忘录，hash，效率比较低
Runtime: 106 ms, faster than 46.45% of Java online submissions for Target Sum.
Memory Usage: 39.7 MB, less than 18.14% of Java online submissions for Target Sum.
*/

/*
一种更像回溯的暴力动态规划
dp(i, sum)的定义是，i到N的元素加起来为sum的可能性之和
dp函数，对应加当前元素或者减去，等于两个子问题的可能性之和
    dp(i, sum) = dp(i+1, sum-cur) + dp(i+1, sum+cur)
选择，当i元素+或-，影响子问题目标sum值
base，i==N时，没有可选元素，如果sum==0，则有一种可能，如果sum！=0，则返回0

由于target可能为负数，所以并不好直接使用数组做memo
- 可以对target做映射，因为整体和不超过1000，但该方法比较繁琐
- 另一种方法是使用hash做memo，效率较低
Runtime: 106 ms
        Memory Usage: 39.7 MB
*/

import java.util.HashMap;
import java.util.Map;

class SolutionV2 {
    private Map<String, Integer> memo;
    private int BASE = 1000;
    // private
    public int findTargetSumWays(int[] nums, int S) {
        memo = new HashMap<>();

        return dp(0, S, nums);
    }

    private int dp(int i, int sum, int[] nums) {
        if (i == nums.length) return sum == 0 ? 1 : 0;
        String key = i + "," + sum;
        if (memo.containsKey(key)) return memo.get(key);
        int val = dp(i + 1, sum + nums[i], nums) + dp(i + 1, sum - nums[i], nums);
        memo.put(key, val);
        return val;
    }
}
````

**解法三，问题转化，背包问题**

原始问题的动态规划不好优化，转化为背包问题

> A为全部+数，B为全部-数
> sum(A) - sum(B) = target
> sum(A) = target + sum(B)
> sum(A) + sum(A) = target + sum(B) + sum(A)
> 2 * sum(A) = target + sum(nums)
> 经过以上变形之后，变成子集问题，选择子集A和为（target + sum(nums)）/2
>
> ref：https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/dong-tai-gui-hua-ji-ben-ji-qiao/targetsum

- dp(i,sum)定义为set[0:i]选出子集得到sum的可能性
  dp递推式的含义是，dp(i, sum) = 选择i + 不选择i
      dp[i][sum] = dp(i - 1, sum - nums[i], nums) + dp(i - 1, sum, nums);
- base，sum==0时直接返回1，否则，i == -1 或者 sum <0 时说明没有可能凑到sum
- 选择，当前i是否选进集合中，影响状态值sum
- 状态，i和sum，集合选择

v1 递归

 ````java

// Memory Limit Exceeded
public class SolutionV3 {
    private static int[][] memo;
    public int findTargetSumWays(int[] nums, int S) {
        for (int n : nums)
            S += n;
        if ((S & 1)==1) return 0;
        S >>= 1;
        memo = new int[nums.length][S+1];
        return dp(nums.length-1, S, nums);
    }

    private int dp(int i, int sum, int[] nums) {
        if (sum == 0) return 1;
        if (i==-1 || sum < 0) return 0;
        if (memo[i][sum]!=0) return memo[i][sum];
        memo[i][sum] = dp(i - 1, sum - nums[i], nums) + dp(i - 1, sum, nums);
        return memo[i][sum];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(new SolutionV3().findTargetSumWays(nums, S));
        ArrayTools.disp2DArray(memo);
    }
}
 ````

v2 迭代版

````java
// 迭代版
// Memory Limit Exceeded
public class SolutionV3_1 {
    private static int[][] memo;
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        for (int x : nums)
            S += x;
        if ((S & 1)==1) return 0;
        S >>= 1;
        memo = new int[n+1][S+1];
        for (int i = 0; i <= n; i++) memo[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int sum = nums[i-1]; sum <= S; sum++) {
                System.out.println(i + " " + sum);
                memo[i][sum] = memo[i - 1][sum] + memo[i - 1][sum - nums[i-1]];
            }
        }
        return memo[n][S];
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(new SolutionV3_1().findTargetSumWays(nums, S));
        ArrayTools.disp2DArray(memo);
    }
}
````

v3 状态压缩

优化极大

````java
/*
状态压缩
背包问题常用，因为背包容量通常很大，导致内存溢出，所以通过状态压缩压缩成O(N）的空间复杂度。
Runtime: 2 ms, faster than 95.78% of Java online submissions for Target Sum.
        Memory Usage: 36.4 MB, less than 92.96% of Java online submissions for Target Sum.
*/

public class SolutionV3_2 {

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length, S = 0; int[] memo;

        for (int x : nums)
            S += x;
        if (S < target || ((S + target) & 1)==1) return 0;

        S = (S + target) >> 1;

        memo = new int[S+1];
        memo[0] = 1;

        for (int i = 1; i <= n; i++)
            for (int sum = S; sum >= nums[i-1]; sum--)
                memo[sum] = memo[sum] + memo[sum - nums[i-1]];

        return memo[S];
    }

}
````



