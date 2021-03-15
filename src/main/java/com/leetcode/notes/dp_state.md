### 有限状态机

#### 198. House Robber

```
/*
* 198. House Robber
* 偷东西
* https://leetcode.com/problems/house-robber/
* */
```

有限状态机，而且符合贪心选择原则。当前的两个状态只和上一个状态有关。
- dp(i)定义为[0:i]能够得到的最大值
    $$
    dp(i)[0] = max(dp(i-1)[0], dp(i-1)[1])
    dp(i)[1] = dp(i-1)[0] + nums[i-1]
    $$
- 状态i
- 选择，当前状态如何从上一级状态转化过来
- base，当i=0时，此时没有任何一家，因而return 0

```java
/*
Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
        Memory Usage: 38.4 MB, less than 8.34% of Java online submissions for House Robber.
*/

class Solution {

    public int rob(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i - 1];
        }
        return Math.max(dp[n][0], dp[n][1]);
    }

}
/*
有限状态机，状态压缩
Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
Memory Usage: 38.2 MB, less than 13.08% of Java online submissions for House Robber.
*/

class SolutionV1_1 {

    public int rob(int[] nums) {
        int n = nums.length, dp_i_0 = 0, dp_i_1 = 0, t;
        int dp[][] = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1);
            dp_i_1 = t + nums[i - 1];
        }
        return Math.max(dp_i_0, dp_i_1);
    }

}
```

#### 213. House Robber II

```
/*
* 213. House Robber II
* 偷东西二
* https://leetcode.com/problems/house-robber-ii/
*
* */
```

```java
/*
原理同q198，但是由于收尾相连，收尾不能同时选择，此时有两种情况[0,n-1)，和[1,n)，选择最大的即可
Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber II.
        Memory Usage: 38.6 MB, less than 6.23% of Java online submissions for House Robber II.
*/

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n==1) return nums[0];
        return Math.max(rob(1, n-1, nums), rob(2, n, nums));
    }

    private int rob(int i, int j, int[] nums) {
        int dp_i_0 = 0, dp_i_1 = 0, t;
        for (; i <= j; i++) {
            t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1);
            dp_i_1 = t + nums[i - 1];
        }
        return Math.max(dp_i_0, dp_i_1);
    }
}
```

#### 337. House Robber III

```
/*
* 337. House Robber III
* 偷东西，二叉树
* https://leetcode.com/problems/house-robber-iii/
*
* */
```

有限状态机，当前节点的值和左右子树能得到的的值有关。比起动态规划更像是后序遍历，复杂度O(N)
- dp(root)定义为当前节点选或不选两个状态能够得到的当前树的最大值
    $$
    dp(root)[0] = max(dp(left)[0], dp(left)[1]) + max(dp(right)[0], dp(right)[1])\\
    dp(root)[1] = dp(left)[0] + dp(right)[0]
    $$
- 状态，树的结构不必再引入状态
- 选择，当前节点选或不选的情况下，当前树能够得到的最大值
- base，当root = null时，表明树为空，return {0，0}

```java
/*

Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber III.
        Memory Usage: 38.9 MB, less than 42.80% of Java online submissions for House Robber III.
*/

class Solution {
    public int rob(TreeNode root) {
        int[] res = trav(root);
        return Math.max(res[0], res[1]);
    }

    private int[] trav(TreeNode root) {
        if (root == null) return new int[2];
        int[] left = trav(root.left);
        int[] right = trav(root.right);
        int[] cur = new int[2];
        cur[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        cur[1] = left[0] + right[0] + root.val;
        return cur;
    }
}
```