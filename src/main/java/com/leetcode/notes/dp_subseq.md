### 概述

经典的子序列问题。

**一些体会**

- 很多问题可以轻易想到子集数的解法。
- 动态规划的重要特征之一，求最值。

**动态规划的两种思考方法**

- 考虑最优子结构，当前问题是什么，子问题是什么，子问题的最优解如何得出当前问题的最优解
- 另一种方法就是解题的套路，考虑dp的常见定义方式

**常见的解法**

- dp(i) 类型

  - 一维memo解决
  - 常见以nums[i]结尾的如何如何
  - 需要考虑当前状态需不需要遍历前面的所有元素，因而复杂度可能为O(N^2)

- dp(i, j) 类型

  - 二维memo

  - 常见s[i:j]的最xx值为多少

  - 迭代需要考虑遍历方式

  - 时间复杂度空间复杂度通常为O(N^2)

  - 可以使用状态压缩

**遍历方式**

````java
对角线遍历：
for (int k = 1; k < n; k++) {
    for (int i = 0; i < n - k; i++) {
        int j = i + k;
    }
}
倒上三角反向遍历
for (int i = n - 1; i >= 0; i--) {
    for (int j = i + 1; j < n; j++) {
    }
}
````

**状态压缩**

节省空间复杂度


### dp(i)

##### 53 最大子序列和

>* 53. Maximum Subarray
>* 最大子序列和
>* https://leetcode.com/problems/maximum-subarray/
>
>```
>Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
>Output: 6
>Explanation: [4,-1,2,1] has the largest sum = 6.
>```

**分析**：

很常见的一道题，因为它有多种解法可以体现多种思想，暴力，分治，动态规划，贪心。

**dp分析**：

- dp(i)，定义为以nums[i]结尾的最大子序列和

  dp[i] = max(dp[i-1]+nums[i], nums[i])

- 状态，i，从0到i的子序列，以i为结尾。

- 选择，i是否重新开始，或者接上i-1。

- base，dp[0] = nums[0]

**解法一：暴力求解**

- 时间复杂度，O(N^2)
- 空间复杂度O（1）

````java
/*
精巧的暴力，O(N^2)，空间复杂度O（1）
Runtime: 129 ms, faster than 5.01% of Java online submissions for Maximum Subarray.
        Memory Usage: 41.2 MB, less than 12.84% of Java online submissions for Maximum Subarray.
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int max = -Integer.MAX_VALUE, sum;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }
}
````

**解法二：动态规划，迭代**

- 时间复杂度，O(N)
- 空间复杂度，O(N)

````java

/*
优化的动态规划，O(N), O(N)
主要在于dp函数的定义，比较玄学，其实本质上的原理和v2一致
dp[i]定义为，以nums[i]结尾的最大子序列和
那么dp[i] = max(dp[i-1]+nums[i], nums[i])，当前数
接上前面的值是否小于直接使用当前数
Runtime: 2 ms, faster than 14.26% of Java online submissions for Maximum Subarray.
        Memory Usage: 41.5 MB, less than 5.83% of Java online submissions for Maximum Subarray.
        Next challenges:
*/

public class SolutionV3 {

    public int maxSubArray(int[] nums) {
        int sum, res;
        int[] dp = new int[nums.length];
        res = sum = dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            res = Math.max(res, sum);
        }
        return res;
    }
}
````

 **解法三，贪心**

- 时间复杂度O(N)
- 空间复杂度O(1)

````JAVA
/*
贪心，
时间复杂度O(N)，空间O(1)
若某时刻sum为负数，则该时刻之前的序列对结果没有贡献，可以舍去
Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Subarray.
        Memory Usage: 39.4 MB, less than 18.35% of Java online submissions for Maximum Subarray.
*/

public class SolutionV2 {
    public int maxSubArray(int[] nums) {
        int sum = 0, max = -Integer.MAX_VALUE;
        for (int x : nums) {
            sum += x;
            max = Math.max(sum, max);
            if (sum < 0) sum = 0;
        }
        return max;
    }
}
````

##### 300 最长递增序列

> * 300. Longest Increasing Subsequence
> * 最长递增子序列
> * https://leetcode.com/problems/longest-increasing-subsequence/

很经典的题目，正确地定义dp函数的重要性。dp[i]正确的定义是，以i为结尾的最长序列

**dp分析**

- base： 当nums[i]前面比其他值大的时候，说明它无法接前面任意子序列的前面
- 状态：i和接上之后改变i的lis
- 选择：前面任意子序列，选哪个接上
- dp函数：前面也说了dp[i]

**复杂度分析**

- 时间复杂度，O(N^2)，虽然定义dp[i]存放在一维的memo，但为了得到dp[i]必须遍历前面的所有已知dp
- 空间复杂度，O(N)

**解法一：迭代**

````java
/*
动态规划
dp函数还是很难想到正确的形式。这道题从子集的角度考虑很难进一步得到动态规划的算法
重复子问题很难找。
dp[i]正确的定义是，以i为结尾的最长序列，非常精彩
base： 当nums[i]前面比其他值大的时候，说明它无法接前面任意子序列的前面
状态：nums[i]接上去后得到多长的lis
选择：前面任意子序列，选哪个接上
dp函数：前面也说了dp[i]
Runtime: 60 ms, faster than 42.45% of Java online submissions for Longest Increasing Subsequence.
        Memory Usage: 39 MB, less than 33.08% of Java online submissions for Longest Increasing Subsequence.
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int res;
        int lis[] = new int[nums.length];
        res = 1;
        Arrays.fill(lis, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]  > nums[j]) lis[i] = Math.max(lis[i], lis[j] + 1);
            }
            res = Math.max(res, lis[i]);
        }
        return res;
    }
}
````

**解法二：巧妙的解法**



这是一种巧妙的算法，和一种纸牌游戏的原理一样

> 根据题目的意思，我都很难想象这个问题竟然能和二分查找扯上关系。
> 其实最长递增子序列和一种叫做 patience game 的纸牌游戏有关。
> 甚至有一种排序方法就叫做 patience sorting（耐心排序）。
> 为了简单起见，后文跳过所有数学证明，通过一个简化的例子来理解一下思路。
> 首先，给你一排扑克牌，我们像遍历数组那样从左到右一张一张处理这些扑克牌，最终要把这些牌分成若干堆。
> 处理这些扑克牌要遵循以下规则：
> 只能把点数小的牌压到点数比它大的牌上。
> 如果当前牌点数较大没有可以放置的堆，则新建一个堆，把这张牌放进去。
> 如果当前牌有多个堆可供选择，则选择最左边的堆放置。
> ref：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484498&idx=1&sn=df58ef249c457dd50ea632f7c2e6e761&source=41#wechat_redirect

这种游戏的特点是，按照上述规则，在游戏任意时刻排队顶总是递增的——因为，若当前值比某排队顶小，则成为
该牌堆的牌堆顶，否则放到右侧的牌堆顶。
有意思的结论是，按这种方式得到的最终牌堆，必然包含最长递增子序列（a，b，……）
而且a必定在堆一，b必定在堆二……
证明：
若a不在堆1，则堆1顶x必定有x < a，且x的索引在a之前，则可以将a替换为x，最长递增子序列为（x，b，……）
同理可以证明，b必定在堆2，c……
从整体来看，若某序列存在最长递增序列，那么这个序列必然会从左到右存放于上述堆中

````java

/*

Runtime: 4 ms, faster than 80.50% of Java online submissions for Longest Increasing Subsequence.
        Memory Usage: 41.4 MB, less than 12.61% of Java online submissions for Longest Increasing Subsequence.
*/

public class SolutionV2 {
    public int lengthOfLIS(int[] nums) {
        int left, right, mid, N;
        int bucket[] = new int[nums.length];
        N = 0;
        for (int x : nums) {
            left = 0;
            right = N;
            while (left < right) {
                mid = (left + right) >> 1;
                if (x > bucket[mid]) left = mid + 1;
                else right = mid;
            }
            bucket[left] = x;
            if (left == N) ++N;
        }
        return N;
    }
}

````

##### 354  俄罗斯套娃

> * 354. Russian Doll Envelopes
> * 俄罗斯套娃，信封嵌套最多层
> * https://leetcode.com/problems/russian-doll-envelopes/

同350，寻找递增序列，拓展为二个维度。

**解法一：迭代的动态规划**

````java
/*
lis动态规划解法
Runtime: 340 ms, faster than 10.81% of Java online submissions for Russian Doll Envelopes.
        Memory Usage: 47.5 MB, less than 6.03% of Java online submissions for Russian Doll Envelopes.
*/

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int res = 1;
        int lis[] = new int[envelopes.length];
        Arrays.sort(envelopes, (a, b) -> (a[0] - b[0]));
        Arrays.fill(lis, 1);

        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
                res = Math.max(res, lis[i]);
            }
        }
        return res;
    }
}
````

**解法二：巧妙解法**

````java
/*
lis棋牌游戏二分解法
此题的关键是，当你将w按升序排序，h按降序排序，问题就直接可以转换成字符串最长字序列
——不改变元素相对位置，直接找h升序序列。
Runtime: 8 ms, faster than 98.25% of Java online submissions for Russian Doll Envelopes.
Memory Usage: 40.2 MB, less than 38.40% of Java online submissions for Russian Doll Envelopes.
*/

public class SolutionV2 {
    public int maxEnvelopes(int[][] envelopes) {
        int left, right, mid, N;
        int bucket[] = new int[envelopes.length];
        N = 0;
        Arrays.sort(envelopes, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        for (int i = 0; i < envelopes.length; i++) {
            left = 0;
            right = N;
            while (left < right) {
                mid = (left + right) >> 1;
                if (envelopes[i][1] > bucket[mid]) left = mid + 1;
                else right = mid;
            }
            bucket[left] = envelopes[i][1];
            if (left == N) ++N;
        }
        return N;
    }
}
````



### dp(i,j)

##### 1143 最长公共子序列

> 1143. Longest Common Subsequence
> 最长公共子序列
> https://leetcode.com/problems/longest-common-subsequence/

经典问题，两字符串匹配。

解法也非常经典，反向匹配。

**dp分析**

- dp函数，dp(i, j)定义为s1[:i]，s2[:j]的LCS长度

  `dp[i][j] = s1[i-1] == s2[j-1] ? dp[i - 1][j - 1] + 1 : max(dp[i - 1][j], dp[i][j - 1]);`

- 状态，i，j -> 当前的LSC

- 选择，i是否等于j

- base，i，j其中一个为0则LSC必为0

**解法一：**

````java
/*
备忘录动态规划
时间空间都是o(N^2)
Runtime: 40 ms, faster than 7.66% of Java online submissions for Longest Common Subsequence.
Memory Usage: 43.1 MB, less than 27.87% of Java online submissions for Longest Common Subsequence.
*/

class Solution {
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        memo = new int[text1.length()][text2.length()];
        return dp(text1.length() - 1, text2.length() - 1, text1, text2);
    }

    private int dp(int i, int j, String text1, String text2) {
        if (i==-1||j==-1) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] = text1.charAt(i) == text2.charAt(j) ? 1 + dp(i - 1, j - 1, text1, text2) : Math.max(dp(i - 1, j, text1, text2), dp(i, j - 1, text1, text2));
        return memo[i][j];
    }
}
````

**解法二：迭代**

````java

/*
迭代实现，动态规划
Runtime: 11 ms, faster than 46.66% of Java online submissions for Longest Common Subsequence.
        Memory Usage: 43 MB, less than 44.07% of Java online submissions for Longest Common Subsequence.
*/

import com.leetcode.common.array.ArrayTools;

public class SolutionV2 {
    private static int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        if (n1 == 0 || n2 == 0) return 0;
        memo = new int[n1+1][n2+1];
        for (int j = 1; j <= n2; j++) {
            for (int i = 1; i <= n1; i++) {
                memo[i][j] = text1.charAt(i-1) == text2.charAt(j-1) ? memo[i - 1][j - 1] + 1 : Math.max(memo[i - 1][j], memo[i][j - 1]);
            }
        }
        return memo[n1][n2];
    }
}
````

**解法二，改进，状态压缩**

````java
/*
优化的迭代动态规划
空间复杂度可以为O(min(N,M))
Runtime: 12 ms, faster than 42.76% of Java online submissions for Longest Common Subsequence.
Memory Usage: 37 MB, less than 97.00% of Java online submissions for Longest Common Subsequence.
*/

public class SolutionV2_1 {
    public int longestCommonSubsequence(String text1, String text2) {

        int n1 = text1.length(), n2 = text2.length(), pre, t;
        int[] dp = new int[n1 + 1];
        if (n1 == 0 || n2 == 0) return 0;
        for (int j = 1; j <= n2; j++) {
            pre = 0;
            for (int i = 1; i <= n1; i++) {
                t = text1.charAt(i-1) == text2.charAt(j-1) ? pre + 1 : Math.max(dp[i], dp[i-1]);
                pre = dp[i];
                dp[i] = t;
            }
        }
        return dp[n1];
    }
}
````

##### 72 编辑距离问题

>* 72. Edit Distance
>  73. 编辑距离问题
>
>  https://leetcode.com/problems/edit-distance/

非常精彩的问题。关键的地方在于想明白匹配算法。

[ref](https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484731&idx=3&sn=aa642cbf670feee73e20428775dff0b5&chksm=9bd7fb33aca0722568ab71ead8d23e3a9422515800f0587ff7c6ef93ad45b91b9e9920d8728e&scene=21#wechat_redirect)

![图片](../../assets/img/640)

````java
if s1[i] == s2[j]:
    啥都别做（skip）
    i, j 同时向前移动
else:
    三选一：
        插入（insert） dp(i, j-1) + 1
        删除（delete） dp(i-1, j) + 1
        替换（replace） dp(i-1, j-1) + 1
````

**dp分析**

- base：当i或j为-1的时候，说明另一个字符串已经结束，这时候只需要全部插入或全部删除步即可完成
- 状态：i，j改变i，j来更新步数
- 选择：插入，删除，替换，
- dp：dp（i， j）定义为解决0 : i，0 : j，使两个字符串串相等，需要的最小步数

**解法一：递归**

````java
/*
https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484731&idx=3&sn=aa642cbf670feee73e20428775dff0b5&chksm=9bd7fb33aca0722568ab71ead8d23e3a9422515800f0587ff7c6ef93ad45b91b9e9920d8728e&scene=21#wechat_redirect
动态规划经典问题。属于子序列问题之一，但是难点在于想明白匹配算法
这里先试试纯递归。
子序列反向匹配的框架
base：当i或j为-1的时候，说明另一个字符串已经结束，这时候只需要全部插入或全部删除步即可完成
状态：i，j
选择：插入，删除，替换
dp：dp（i， j）返回解决i，j状态需要的最小步数
Time Limit Exceeded
*/

class Solution {

    public int dp(int i, int j, String word1, String word2) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        return word1.charAt(i) != word2.charAt(j) ?
            min(dp(i-1, j-1, word1, word2) + 1, dp(i, j - 1, word1, word2) + 1,
                    dp(i - 1, j, word1, word2) + 1) : dp(i - 1, j - 1, word1, word2);
    }

    private int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    public int minDistance(String word1, String word2) {
        return dp(word1.length() - 1, word2.length() - 1, word1, word2);
    }
}
````

**解法二：递归备忘**

````java
class SolutionV1 {
    private static int[][] memo;

    public int dp(int i, int j, String word1, String word2) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] =  word1.charAt(i) != word2.charAt(j) ?
            min(dp(i-1, j-1, word1, word2) + 1, dp(i, j - 1, word1, word2) + 1,
                    dp(i - 1, j, word1, word2) + 1) : dp(i - 1, j - 1, word1, word2);
        return memo[i][j];
    }

    private int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    public int minDistance(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        return dp(word1.length() - 1, word2.length() - 1, word1, word2);
    }

}
````

**解法三，迭代备忘**

````java
/*

Runtime: 9 ms, faster than 16.91% of Java online submissions for Edit Distance.
        Memory Usage: 41.2 MB, less than 16.76% of Java online submissions for Edit Distance.
*/

class  SolutionV2 {
    private static int memo[][];
    private int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }
    public int minDistance(String word1, String word2) {
        int n1, n2;
        n1 = word1.length() + 1;
        n2 = word2.length() + 1;
        memo = new int[n1][n2];
        for (int i = 0; i < n2; ++i) memo[0][i] = i;
        for (int i = 0; i < n1; ++i) memo[i][0] = i;

        for (int j = 1; j < n2; j++)
            for (int i = 1; i < n1; i++)
                memo[i][j] = word1.charAt(i-1) != word2.charAt(j-1) ? min(memo[i - 1][j - 1], memo[i - 1][j], memo[i][j - 1]) + 1 : memo[i - 1][j - 1];
        return memo[n1-1][n2-1];
    }
}
````

##### 583 删除使二字符串相等

> * 583. Delete Operation for Two Strings
> * 序列匹配，删除以相同
> * https://leetcode.com/problems/delete-operation-for-two-strings/

和72基本类似。

- 状态，i，j，步数
- 选择，i，j对应的字母是否相等，不相等则删除其一，i-1或者j-1
- base，当i和j其中为-1的时候，表示一个字符串已经为“”，则此时之只要把另外一个全部字母删掉即可
- dp函数，dp(i,j)表示从s1[0:i]和s2[0:j]最大需要的步数，同状态，则`memo[i][j] = s1.charAt(i) == s2.charAt(j) ? dp(i - 1, j - 1, s1, s2): Math.min(dp(i - 1, j, s1, s2), dp(i, j - 1, s1, s2)) +  + 1`

**解法一：递归备忘**

````java

/*
动态规划，memo递归
状态，dp(i,j)表示从s1[0:i]和s2[0:j]最大需要的步数
选择，i，j对应的字母是否相等，不相等则删除其一，i-1或者j-1
base，当i和j其中为-1的时候，表示一个字符串已经为“”，则此时之只要把另外一个全部字母删掉即可
dp函数，同状态，则memo[i][j] = s1.charAt(i) == s2.charAt(j) ? dp(i - 1, j - 1, s1, s2): Math.min(dp(i - 1, j, s1, s2), dp(i, j - 1, s1, s2)) +  + 1
Runtime: 16 ms, faster than 16.40% of Java online submissions for Delete Operation for Two Strings.
        Memory Usage: 43.7 MB, less than 9.15% of Java online submissions for Delete Operation for Two Strings.

*/

class Solution {
    private int[][] memo;
    public int minDistance(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        return dp(word1.length() - 1, word2.length() - 1, word1, word2);
    }

    private int dp(int i, int j, String s1, String s2) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (memo[i][j]!=0) return memo[i][j];
        return memo[i][j] = s1.charAt(i) == s2.charAt(j) ? dp(i - 1, j - 1, s1, s2): Math.min(dp(i - 1, j, s1, s2), dp(i, j - 1, s1, s2)) +  + 1 ;
    }
}
````

**解法二：迭代实现**

````java
class SolutionV2 {
    private static int[][] memo;

    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
        memo = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) memo[i][0] = i;
        for (int i = 0; i <= n2; i++) memo[0][i] = i;
        for (int j = 1; j <= n2; j++) {
            for (int i = 1; i <= n1; i++) {
                memo[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? memo[i - 1][j - 1] : Math.min(memo[i - 1][j], memo[i][j - 1]) + 1;
            }
        }
        ArrayTools.disp2DArray(memo);
        return memo[n1][n2];
    }
}
````

**解法二：改进，状态压缩**

````java
/*
O(N)空间复杂度优化
Runtime: 5 ms, faster than 98.95% of Java online submissions for Delete Operation for Two Strings.
Memory Usage: 38.7 MB, less than 99.89% of Java online submissions for Delete Operation for Two Strings.
*/

class SolutionV2_1 {

    public int minDistance(String word1, String word2) {

        int[] dp;
        int n1 = word1.length(), n2 = word2.length(), t, pre = 0;
        dp = new int[n1 + 1];
        for (int i = 0; i <= n1; i++) dp[i] = i;
        ArrayTools.disp1DArray(dp);
        for (int j = 1; j <= n2; j++) {
            pre = dp[0];
            dp[0] = j;
            for (int i = 1; i <= n1; i++) {
                t = word1.charAt(i - 1) == word2.charAt(j - 1) ? pre :( Math.min(dp[i - 1], dp[i]) + 1);
                pre = dp[i];
                dp[i] = t;
            }
        }

        return dp[n1];
    }
}
````

##### 712 删除使两字符串相同二

要求最小ASCII删除和，同583。

````java
/*
执行用时：39 ms, 在所有 Java 提交中击败了23.21%的用户
内存消耗：39.3 MB, 在所有 Java 提交中击败了18.73%的用户
* */

class Solution {
    private int[][] memo;
    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        memo = new int[n1+1][n2+1];
        for (int i = 1; i <= n1; i++) memo[i][0] = s1.charAt(i-1) + memo[i - 1][0];
        for (int i = 1; i <= n2; i++) memo[0][i] = s2.charAt(i-1) + memo[0][i - 1];
        return dp(s1.length(), s2.length(), s1, s2);
    }

    private int dp(int i, int j, String s1, String s2) {
        if (i == 0 || j == 0 || memo[i][j] != 0) return memo[i][j];
        memo[i][j] = Math.min(dp(i - 1, j, s1, s2) + s1.charAt(i-1), dp(i, j - 1, s1, s2) + s2.charAt(j-1));
        if (s1.charAt(i-1) == s2.charAt(j-1)) {
            // 这一步其实是多余的
            // dp[i-1][j-1]的值绝对比dp[i-1][j], dp[i][j-1]要更小
            memo[i][j] = Math.min(dp(i - 1, j - 1, s1, s2), memo[i][j]);
        }
        return memo[i][j];
    }
}
````



##### 516 最长回文子串

> * 516. Longest Palindromic Subsequence
> * 最长回文子串
> * https://leetcode.com/problems/longest-palindromic-subsequence/

从字符串中找出最长回文串，不需要连续。

dp的解法比较少见，考虑当你知道i+1:j-1的字符串中最长回文串长度，求i:j的回文串长度。

**dp分析**：

- dp定义，dp(i,j)表示s[i:j]的最长回文串的长度
      dp(i,j) = s[i] == s[j] ? (dp(i+1, j-1) + 2) : max(dp(i, j - 1, s), dp(i + 1, j))
- 状态，i，j，i：j的最长回文串长度
- 选择，根据i，j的字符是否相等，找到当前的i:j的最长回文串长度
- base，当i=j的时候，return 1，当j<i的时候，说明已经没有字符，return 0

根据基值，dp函数定义，目标（dp（0，n）），该问题的迭代解法为上斜遍历，或为反向遍历，省略了迭代解法。

**复杂度**

O(N^2)

**解法一：动态规划，备忘录**

````java

/*
动态规划，递归备忘录
dp定义，dp(i,j)表示s[i:j]的最长回文串的长度
    dp(i,j) = s[i] == s[j] ? (dp(i+1, j-1, s) + 2) : max(dp(i, j - 1, s), dp(i + 1, j, s))
状态，i，j。dp(i,j)表示s[i:j]的最长回文串的长度
选择，考虑引入s[i]和s[j]
base，当i=j的时候，这个时候只有一个字母，return 1，当j < i的时候，这时没有字母，return 0；
Runtime: 22 ms, faster than 95.08% of Java online submissions for Longest Palindromic Subsequence.
        Memory Usage: 48.9 MB, less than 86.80% of Java online submissions for Longest Palindromic Subsequence.
*/

class Solution {
    private int [][]memo;

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        memo = new int[n][n];
        return dp(0, n-1, s);
    }

    private int dp(int i, int j, String s) {
        if (i == j) return 1;
        if (j < i) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] = s.charAt(i) == s.charAt(j) ? (dp(i+1, j-1, s) + 2) : Math.max(dp(i, j - 1, s), dp(i + 1, j, s));
        // System.out.println(i + " " + j + " " + memo[i][j]);
        return memo[i][j];
    }

}
````

**解法二：迭代**

````java
/*
动态规划迭代实现，对角线遍历
对角线遍历：
for (int k = 1; k < n; k++) {
    for (int i = 0; i < n - k; i++) {
        int j = i + k;
    }
}
倒上三角反向遍历
for (int i = n - 1; i >= 0; i--) {
    for (int j = i + 1; j < n; j++) {
    }
}
Runtime: 40 ms, faster than 58.14% of Java online submissions for Longest Palindromic Subsequence.
Memory Usage: 48.9 MB, less than 81.68% of Java online submissions for Longest Palindromic Subsequence.
 */

class SolutionV2 {

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = 1;

        for (int k = 1; k < n; k++) {
            int m = n - k;
            for (int i = 0; i < m; i++) {
                int j = i + k;
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] + 2 : Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }

}
````

