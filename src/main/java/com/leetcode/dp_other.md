### 动态规划的其他题目

#### 877.高楼抛鸡蛋问题

这是一道经典的题目。有多种解决方法，而且题目本身非常抽象，即使case很少也不好理解。例如你基本上很难想明白2个鸡蛋6层楼，需要扔多少次。

这个问题是有最优解的，即对于任意k个鸡蛋，n层，必定存在一种最优方案，使得测试次数达到最少，该方案可以规定每个测试的测试点（解法二可以更好体现这一点）。

这个问题有很多种解，部分解非常精巧但是意义并不是很大。

- 解法一，动态规划，非常直观的一种解法，也非常重要。其中很有意思的就是二分搜索的优化，以及对于max，min的复合优化目标的分析——使用图像。
- 解法二，另一种巧妙的动态规划的定义方法，这种方法比较难理解，但是却能更好的帮助理解这个问题本身。
- 其他不重要的优化，解法一可以使用决策单调性优化为严格的O(KN)，解法二可以利用数学知识优化为O(KlogN)，但这些都不是重点。

##### 解法一，动态规划

- dp(k,n)定义为k个鸡蛋，n层楼，最多需要扔的次数
  $$
  \mathrm{dp}(K, N)=\min _{1 \leq X \leq N}(\max (\mathrm{dp}(K-1, X-1), \mathrm{dp}(K, N-X)))+1
  $$
  为了确定k个鸡蛋下，n层楼需要多少次，有1-n种选择，从1-n楼抛下鸡蛋，如果碎了，则等于$\mathrm{dp}(K-1, X-1)$，鸡蛋减一，层数减一，测试该层以下的层次。如果没碎，则等于$\mathrm{dp}(K, N-X)$，鸡蛋数目不变，测试该层以上的层。由于需要考虑最坏情况，必须保证在某一层抛，取碎或不碎中的大者。这里容易混淆，在保证最坏情况的情形下，选择次数最少的一层，所以最外层是min

- 选择，在那一层抛下鸡蛋

- 状态，k，n

- base，当k=1时，鸡蛋只有一个，则只能从第一层慢慢扔，所以n层的最坏情况是n次抛鸡蛋。当n=0时，层数为0，不需要抛，直接return 0。

**复杂度**

- 由于每个子问题都需要O(N)的时间，所以整体时间是O(KN^2)
- 空间复杂度O(KN)

````java
/*
Time Limit Exceeded
*/
class Solution {
    private int[][] memo;
    public int superEggDrop(int K, int N) {
        memo = new int[K + 1][N + 1];
        return dp(K, N);
    }

    private int dp(int k, int n) {
        if (k == 1) return n;
        if (n == 0) return 0;
        if (memo[k][n]!=0) return memo[k][n];
        memo[k][n] = n;
        for (int i = 1; i <= n; i++)
            memo[k][n] = Math.min(memo[k][n], Math.max(dp(k - 1, i - 1), dp(k, n - i)) + 1);
        return memo[k][n];
    }
}
````

##### 解法一改进：二分搜索优化

该解法改进自上一个解法，它的思想是
$$
\mathrm{dp}(K, N)=\min _{1 \leq X \leq N}(\max (\mathrm{dp}(K-1, X-1), \mathrm{dp}(K, N-X)))
$$
该公式中$\mathrm{dp}(K-1, X-1), \mathrm{dp}(K, N-X)$，关于X的搜索过程，前者是单调递减的函数，后者是单调递增的函数，而从整体来看，目标min等于图中的交叉点——必然可以找到一点，使得前者等于后者。使用二分搜索搜索谷底的值。

![图片](../assets/img/640)

**复杂度**

- 时间复杂度，和上一个版本是一致的，但是在每个子问题的求解，只需要O(logN)，所以，整体时间复杂度下降为O(K N log N)
- 空间复杂度O(K N)

````JAVA
/*

Runtime: 29 ms, faster than 36.04% of Java online submissions for Super Egg Drop.
Memory Usage: 39.4 MB, less than 60.06% of Java online submissions for Super Egg Drop.

*/

class SolutionV1_1 {
    private int[][] memo;
    public int superEggDrop(int K, int N) {
        memo = new int[K + 1][N + 1];
        return dp(K, N);
    }

    private int dp(int k, int n) {
        if (k == 1) return n;
        if (n == 0) return 0;
        if (memo[k][n]!=0) return memo[k][n];

        int l,r, mid;
        l = 1; r = n+1;
        while (l < r) {
            mid = (l + r) >> 1;
            if ((dp(k-1, mid - 1)) < (dp(k, n - mid)))  l = mid + 1;
            else r = mid;
        }

        memo[k][n] = 1 + Math.max(dp(k - 1, l - 1), dp(k, n - r));
        // memo[k][n] = 1 + Math.max(t1, t2);
        return memo[k][n];
    }
}
````

#####  解法一改进进阶，决策单调性

![T1, T2 diagram](../assets/img/sketch2.png)

对于每个K，都有可以画出上面的图像。在上面两个解法中，对于每个N，我们都需要在所有X中搜索最好的解（不管是遍历还是二分）。但事实上，
$$
\max (\mathrm{dp}(K-1, X-1), \mathrm{dp}(K, N-X))
$$
在某一个K中，上述公式的前半部分关于N是常数，即是，不随N的变化而变化，而后半部分则是单调递增函数，因而，从整体来看，这个max必定为随着N的增大和整体单调递增。从图像的角度来理解，上图的蓝色部分，N的增大，函数整体增大。而在这个基础上，最优解(交点)也同时单调递增。

有了这个结论，对于任意的K，遍历N，我们只需要不断增大x，使得满足交点单调递增即可。

这个思想叫做决策单调性，思想很有意思，但是实现并不重要。

##### 解法二，动态规划方法二

该方法比较难想，但对于理解该问题却很有帮助。

- 定义dp(k, m)为k个鸡蛋，扔m次，最多能确定多少层。
  $$
  dp(k,m) = dp(k-1, m-1) + dp(k, m-1)
  $$
  上述递推式的正确理解是，在dp(k-1, m-1) 之后的第一层扔下一个鸡蛋，若没碎，则上面的层等于dp(k, m-1)，该层下面的层等于dp(k-1,m-1)，相加即可得到k个鸡蛋扔m次最多能确定多少层。

  这其实不是很好理解。以dp(2,6)为例，dp(2,6) = dp(1,5)+dp(2,5)，我们知道dp(1,5)实际上等于6——当剩下一个鸡蛋的时候，只能线性地一层一层扔，最多确定0-5层（包括0层，例如在1扔了，若碎了则确定为0）。所以，dp(2,6)实际上是在dp(1,5)+1 = 6层进行测试，第6层进行测试，考虑鸡蛋没碎才能确定更多的层次，多以dp(2,6) = dp(1,5) + dp(2,5)

- 状态，k，m

- 选择，在极限层抛鸡蛋

- base，当k==1时，只有一个鸡蛋，最多确定m+1层，当m为0时，最多确定0层。

**复杂度**

- 时间空间都是O(KN), (实际上是O(KM)比O(KN)要快很多）

改递推式可以用数学知识优化到O(KlogN)，但这种优化没有普遍意义，这里不进行讨论。

````java
/*
递归
Runtime: 1 ms, faster than 87.50% of Java online submissions for Super Egg Drop.
        Memory Usage: 35.9 MB, less than 91.40% of Java online submissions for Super Egg Drop.
*/

public class SolutionV2 {
    public int superEggDrop(int K, int N) {
        int m = 1;
        while (dp(K, m) < N + 1) ++m;
        return m;
    }

    private int dp(int k, int m) {
        if (k == 1 || m == 1) return m + 1;
        return dp(k - 1, m - 1) + dp(k, m - 1);
    }
}
/*
递归，备忘录
Runtime: 1 ms, faster than 87.50% of Java online submissions for Super Egg Drop.
Memory Usage: 39.8 MB, less than 38.47% of Java online submissions for Super Egg Drop.
*/

public class SolutionV2_1 {
    private int[][] memo;

    public int superEggDrop(int K, int N) {
        memo = new int[K+1][N+1];
        int m = 1;
        while (dp(K, m) < N + 1) ++m;
        return m;
    }

    private int dp(int k, int m) {
        if (k == 1 || m == 1) return m + 1;
        if (memo[k][m]!=0) return memo[k][m];
        memo[k][m] = dp(k - 1, m - 1) + dp(k, m - 1);
        return memo[k][m];
    }
}
/*
迭代
Runtime: 1 ms, faster than 87.50% of Java online submissions for Super Egg Drop.
Memory Usage: 39.6 MB, less than 44.64% of Java online submissions for Super Egg Drop.
 */

public class SolutionV2_2 {


    public int superEggDrop(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        int m = 1;

        for (m = 1; ; m++) {
            for (int k = 1; k <= K; k++) {
                memo[k][m] = memo[k - 1][m - 1] + memo[k][m - 1] + 1;
            }
            System.out.println(memo[K][m]);
            if (memo[K][m] >= N) break;
        }
        return m;
    }

    public static void main(String[] args) {
        new SolutionV2_2().superEggDrop(1, 4);
    }

}
````

##### Ref

一个比较体系化的讲解，但是进阶解法不是很好理解。

- https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484675&idx=1&sn=4a4ac1c0f1279530b42fedacc6cca6e6&chksm=9bd7fb0baca0721dda1eaa1d00b9a520672dc9d5c3be762eeca869be35d7ce232922ba8e928b&scene=21#wechat_redirect
- https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484690&idx=1&sn=eea075701a5d96dd5c6e3dc6a993cac5&chksm=9bd7fb1aaca0720c58c9d9e02a8b9211a289bcea359633a95886d7808d2846898d489ce98078&scene=21#wechat_redirect

官方题解，同样不好理解，但是提供了决策单调性的思路，有用

- https://leetcode-cn.com/problems/super-egg-drop/solution/ji-dan-diao-luo-by-leetcode-solution-2/

补充了第一个题解进阶解法的另一种角度。该进阶解法可以用数学知识进行改造，但是意义不大。

- https://leetcode-cn.com/problems/super-egg-drop/solution/887-by-ikaruga/



#### 10. Regular Expression Matching

```
* 10. Regular Expression Matching
* 正则匹配 *的策略
* https://leetcode.com/problems/regular-expression-matching/
```

不是很好写的动态规划，基值条件和dp函数实现细节出错就没了。

字符串匹配，d(i, j)定义为s[:i], p[:j]是可以匹配
````
p[j] = '.'或s[i] = p[j]，则dp(i,j) = dp(i-1, j-1)
p[j] = '*'则共有两种情况
    零匹配，丢弃'x*'，dp(i,j) = dp(i, j-2)
    匹配正确，s缩小，p[j-1]=s[i] 或 p[j-1]='.'，则 dp(i, j) = dp(i-1,j)
base dp(0,0) = true，当i=0，s为空，p[j] = '*'，则dp(0,j) = dp(0,j-2)
````



```java
/*
不是很好写的动态规划，基值条件和dp函数实现细节出错就没了。

字符串匹配，d(i, j)定义为s[:i], p[:j]是可以匹配
p[j] = '.'或s[i] = p[j]，则dp(i,j) = dp(i-1, j-1)
p[j] = '*'则共有两种情况
    零匹配，丢弃'x*'，dp(i,j) = dp(i, j-2)
    匹配正确，s缩小，p[j-1]=s[i] 或 p[j-1]='.'，则 dp(i, j) = dp(i-1,j)
base
dp(0,0) = true，当i=0，s为空，p[j] = '*'，则dp(0,j) = dp(0,j-2)

Runtime: 2 ms, faster than 90.30% of Java online submissions for Regular Expression Matching.
Memory Usage: 37.4 MB, less than 96.35% of Java online submissions for Regular Expression Matching.
*/
class Solution {
    private static boolean [][] memo;

    public boolean isMatch(String s, String p) {
        char c;
        int n = s.length(), m = p.length();
        memo = new boolean[n + 1][m + 1];
        memo[0][0] = true;
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j-1) == '*') memo[0][j] = memo[0][j - 2];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if ((c = p.charAt(j-1)) == '*') {
                    memo[i][j] = ((p.charAt(j-2) == '.'||p.charAt(j-2) == s.charAt(i-1)) && memo[i-1][j]) || memo[i][j-2];
                } else if (c == '.' || c == s.charAt(i-1)) {
                    memo[i][j] = memo[i - 1][j - 1];
                }
            }
        }
        return memo[n][m];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aa", "a*"));
        ArrayTools.disp2DArray(memo);
    }
}
```

#### 28. Implement strStr()

```
/*
* 28. Implement strStr()
* 字符串匹配，KMP
* https://leetcode.com/problems/implement-strstr/
*
* */
```

```java
/*
KMP算法
https://www.bilibili.com/video/BV18k4y1m7Ar?from=search&seid=15700617897526094886
Runtime: 3 ms, faster than 34.27% of Java online submissions for Implement strStr().
Memory Usage: 39.4 MB, less than 6.59% of Java online submissions for Implement strStr().
* */
class Solution {
    public int strStr(String haystack, String needle) {
        int i, j, n, m;
        i = j = 0; n = haystack.length();m = needle.length();
        int[] next = genNext(needle);
        while (i < n && j < m) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;j++;
            } else {
                if (j==0) i++;
                else j = next[j - 1];
            }
        }
        if (j==m) return i - m;
        return -1;
    }

    public int[] genNext(String pat) {
        int n, i, j;
        n = pat.length(); i = 0; j = 1;
        int next[] = new int[n];
        while (j < n) {
            if (pat.charAt(i) == pat.charAt(j)) {
                next[j++] = i++ + 1;
            } else {
                if (i == 0) {
                    next[j++] = 0;
                    i = 0;
                }else
                    i = next[i - 1];
            }
        }
        return next;
    }
}
```

####

```
/*
* 312. Burst Balloons
* 戳气球，最高分
* https://leetcode.com/problems/burst-balloons/
* */
```

这道题动态规划很难想，主要是定义比较巧妙。暴力的解法可以比较容易想到全排列的解法。
动态规划的解法体现了子问题的独立性特征，动态规划要求子问题和最优子结构的子问题必须相互独立。
- dp(i,j)，定义为开区间内(i,j)中能取得的最大分数，开区间是一个精彩的技巧，保证了子问题的独立性。
    $$
    dp(i,j) = max(dp(i,k) + dp(k,j) + point(i,k, j)), where \\i+ 1 <= k < j
    $$
   对于每一组开区间i，j，遍历其中的所有k，k代表最后被戳破的气球，这也是极为精彩的定义
- 状态，i，j，k
- 选择， 戳破i到j中的哪个气球
- base，当i + 1 = j的时候，则此时开区间内没有任何气球，直接返回0

**复杂度**
- 时间复杂度为 O(N N N),由于子问题的求解为O(N)
- 空间，O(N N)

```java
/*

Runtime: 219 ms, faster than 7.52% of Java online submissions for Burst Balloons.
Memory Usage: 40.2 MB, less than 6.32% of Java online submissions for Burst Balloons.
*/

class Solution {
    private int[][] memo;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int nums2[] = new int[n + 2];
        memo = new int[n + 2][n + 2];

        for (int i = 0; i < n; i++) nums2[i + 1] = nums[i];
        nums2[0] = nums2[n + 1] = 1;
        return dp(0, n + 1, nums2);
    }

    private int dp(int i, int j, int[] nums) {
        if (i == j - 1) return 0;
        if (memo[i][j]!=0) return memo[i][j];
        for (int k = i+1; k < j; k++) {
            memo[i][j] = Math.max(memo[i][j],dp(i,k,nums) + dp(k,j,nums) +
                    nums[i] * nums[k] * nums[j]);
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        int nums[] = {3, 1, 5, 8};
        new Solution().maxCoins(nums);
    }
}

/*
迭代解法，从base和目标倒推遍历方向问题。
Runtime: 99 ms, faster than 53.93% of Java online submissions for Burst Balloons.
Memory Usage: 39.6 MB, less than 14.14% of Java online submissions for Burst Balloons.
*/



class SolutionV2 {
    private int[][] memo;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int nums2[] = new int[n + 2];
        memo = new int[n + 2][n + 2];
        for (int i = 0; i < n; i++) nums2[i + 1] = nums[i];
        nums2[0] = nums2[n + 1] = 1;
        int x = 1;
        for (int i = n-1; i >= 0; i--) {
            for (int j = i + 2; j < n + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    System.out.println(i + " " + j + " " + k);
                    memo[i][j] = Math.max(memo[i][j], memo[i][k] + memo[k][j] + nums2[i] * nums2[k] * nums2[j]);
                }
            }
        }

        return memo[0][n+1];
    }
}
```

#### 1312. Minimum Insertion Steps to Make a String Palindrome

```
/*
* 1312. Minimum Insertion Steps to Make a String Palindrome
* 最小步数回文串
* https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
*
* */
```

- dp(i, j)定义为[i,j]转变为回文需要的最小变动步数，可以将dp(i,j)的子问题视为已经是回文，当s[i]!=s[j]时
    $$
    dp(i, j) = min(dp(i + 1, j), dp(i, j - 1)) + 1
    $$
    上述公式是该问题的关键。当s[i]=s[j]时，dp(i, j) = dp(i+1, j-1)。而当s[i]!=s[j]时，情况比较难考虑。例如
    会出现aaab，这种情况，它并不是直接dp(i+1, j-1)+2，而是1。所以，该递推式的是将这个问题委托给两个子问题dp(i + 1, j), dp(i, j - 1)
- 状态i，j
- 选择，选择哪个子问题
- base，当j<=i时，说明此时没有任何字母，return 0

```java
/*
Runtime: 12 ms, faster than 97.83% of Java online submissions for Minimum Insertion Steps to Make a String Palindrome.
Memory Usage: 39.9 MB, less than 89.13% of Java online submissions for Minimum Insertion Steps to Make a
* */

class Solution {
    private int[][] memo;
    public int minInsertions(String s) {
        int n = s.length();
        memo = new int[n][n];
        return dp(0, n - 1, s.toCharArray());
    }

    private int dp(int i, int j, char[] s) {
        if (j <= i) return 0;
        if (memo[i][j]!=0) return memo[i][j];
        memo[i][j] = s[i] == s[j] ? dp(i + 1, j - 1, s) :
                Math.min(dp(i + 1, j, s), dp(i, j - 1, s)) + 1;
        return memo[i][j];
    }
}

/*
迭代
Runtime: 14 ms, faster than 93.75% of Java online submissions for Minimum Insertion Steps to Make a String Palindrome.
Memory Usage: 40.4 MB, less than 47.96% of Java online submissions for Minimum Insertion Steps to Make a
* */

class SolutionV2 {
    public int minInsertions(String s) {
        int[][] memo;
        int n = s.length();
        memo = new int[n][n];
        for (int i = n-2; i >= 0; --i) {
            for (int j = i+1; j < n; j++) {
                memo[i][j] = s.charAt(i) == s.charAt(j) ? memo[i+1][j-1] :
                        Math.min(memo[i+1][j], memo[i][j-1]) + 1;
            }
        }
        return memo[0][n-1];
    }

}
```

#### 1411. Number of Ways to Paint N × 3 Grid

```
/*
* 1411. Number of Ways to Paint N × 3 Grid
* 上色，阿里笔试题
* https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
* */
```

第n行只和n-1行有关，所以这是一个动态规划的题目。

121类型，随后只能跟着5种上色方法，212 231 232 312，其中两种是121类，两种是123类

123类型，随后只能跟着4种上色方法，212 213 232 312 313，其中三种是121类，两种是123类

当n=1时，121类为a=6，123类为b=6，

n=2时，121类为$3*a+2*b$，123类为$2*a+2*b$

依此类推。

```java
/*


Runtime: 2 ms, faster than 98.72% of Java online submissions for Number of Ways to Paint N × 3 Grid.
Memory Usage: 35.4 MB, less than 93.08% of Java online submissions for Number of Ways to Paint N × 3 Grid.
* */
class Solution {
    public int numOfWays(int n) {
        long a = 6, b = 6, t, mod = (long)(1e9+7);
        for (int i = 1; i < n; ++i){
            t = (a * 3 + b * 2) % mod;
            b = (a * 2 + b * 2) % mod;
            a = t;
        }
        return (int)((a + b) % mod);
    }
}
```

### 阿里面试题

#### 1539. Kth Missing Positive Number

```
/*
* 1539. Kth Missing Positive Number
* 寻找确实的正数
* https://leetcode.com/problems/kth-missing-positive-number/
*
*
* */
```

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int j = 1, n = arr.length, i = 0;
        for (;k>0;){
            if(i < n && arr[i] == j) ++i;
            else --k;
            ++j;
        }
        return j-1;
    }
}
```

#### 815. Bus Routes

最短路径问题，关键是建图的过程，把车当成边。

```java
/*
 * 815. Bus Routes
 * 图， bfs
 * https://leetcode.com/problems/bus-routes/
 *
 *
 * */

/*
BFS
多个循环被折叠起来，算法复杂度不是很好算。
每个bus都只会访问一次，每个bus能到达的城市至少访问一次，所以整体上应该是O(MN)
Runtime: 76 ms, faster than 31.09% of Java online submissions for Bus Routes.
Memory Usage: 93.1 MB, less than 11.33% of Java online submissions for Bus Routes.
* */
class SolutionV2 {


    public int numBusesToDestination(int[][] routes, int source, int target) {
        int step = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> vis = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < routes.length; ++i){
            for(int j = 0; j < routes[i].length; ++j){
                Set<Integer> cur = map.getOrDefault(routes[i][j], null);
                if(cur == null){
                    cur = new HashSet<>();
                    map.put(routes[i][j], cur);
                }
                cur.add(i);
            }
        }
        q.add(source);
        while(!q.isEmpty()){
            int size = q.size();
            while( size-- > 0 ){
                int city = q.poll();
                if(city == target) return step;
                Set<Integer> buses = map.getOrDefault(city, null);
                if(buses==null) continue;
                for (int bus : buses){
                    if(vis.contains(bus)) continue;
                    for(int arrCity : routes[bus]){
                        q.add(arrCity);
                    }
                    vis.add(bus);
                }

            }
            ++step;
        }

        return -1;
    }
}
```

#### 879. Profitable Schemes

```java
/*
* 879. Profitable Schemes
* 选择盈利模式
* https://leetcode.com/problems/profitable-schemes/
*
* */
/*
这个动态规划有点难，01背包问题

不是很懂为何最后要累加
* */
class SolutionV2 {
    private int MOD = (int)(1e9 + 7);
    private int memo[][][];

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int gn = group.length, p, g;
        memo = new int[gn+1][n+1][minProfit+1];
        memo[0][0][0] = 1;
        for(int k = 1; k <= gn; ++k){
            for(int i = 0; i <= n; ++i){
                g = group[k-1];
                p = profit[k-1];
                for(int j = 0; j <= minProfit; ++j){
                    memo[k][i][j] = memo[k-1][i][j];
                    if(g <= i){
                        memo[k][i][j] = (memo[k-1][i][j] + memo[k-1][i-g][Math.max(0, j - p)])%MOD ;
                    }
                }
            }
        }
                
       int sum = 0;                                                       
        for(int i = 0; i <= n; i++){
            sum = (sum + memo[gn][i][minProfit])%MOD;
        }
        return sum;
    }
    



}
```