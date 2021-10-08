### 买股票系列

有限状态机。一种区别与序列dp定义的动态规划定义方法。

#### 通用框架

````java
int n = prices.length, K = k;
int memo[][][] = new int[n + 1][K + 1][2];
for (int i = 0; i <= n; i++) memo[i][0][1] = Integer.MIN_VALUE;
for (int i = 0; i <= K; i++) memo[0][i][1] = Integer.MIN_VALUE;

for (int i = 1; i <= n; i++) {
    for ( k = 1; k <= K; k++) {
        memo[i][k][0] = Math.max(memo[i - 1][k][0], memo[i - 1][k][1] + prices[i-1]);
        memo[i][k][1] = Math.max(memo[i - 1][k][1], memo[i - 1][k - 1][0] - prices[i-1]);
    }
}
````

**状态压缩**

````java
int n = prices.length;
int memo[][] = new int[k + 1][2];
for (int i = 0; i <= k; i++) memo[i][1] = Integer.MIN_VALUE;

for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= k; j++) {
        memo[j][0] = Math.max(memo[j][0], memo[j][1] + prices[i-1]);
        memo[j][1] = Math.max(memo[j][1], memo[j - 1][0] - prices[i-1]);
    }
}
````

#### 问题

##### Best Time to Buy and Sell Stock

```
/*
* 121. Best Time to Buy and Sell Stock
* 买股票一，一次
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
*
* */
```

**解法一：one pass**

```java
/*
原理，高个子排队，one pass
Runtime: 1 ms, faster than 98.84% of Java online submissions for Best Time to Buy and Sell Stock.
Memory Usage: 52 MB, less than 47.42% of Java online submissions for Best Time to Buy and Sell Stock.
* */
class com.leetcode.dp.q91.Solution {
    public int maxProfit(int[] prices) {
        int res, max;
        res = max = 0;
        for (int i = prices.length - 1; i >= 0; --i) {
            if (max > prices[i]) res = Math.max(res, max - prices[i]);
            else max = prices[i];
        }
        return res;
    }
}
```

**解法二：动态规划**

- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])\\
    dp(n)[1] = max(dp(n-1)[1], - prices[n-1])
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。

```java
class SolutionV2 {
    private static int[][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length;
        memo = new int[n+1][2];
        return dp(n, prices)[0];
    }

    private int[] dp(int n, int[] prices) {
        if (n == 0) {
            memo[n][1] = Integer.MIN_VALUE;
            return memo[n];
        }
        if(memo[n][0]!=0) return memo[n];
        memo[n][0] = Math.max(dp(n - 1, prices)[0], dp(n - 1, prices)[1] + prices[n - 1]);
        memo[n][1] = Math.max(dp(n - 1, prices)[1],  - prices[n - 1]);
        return memo[n];
    }

    public static void main(String[] args) {
        int[] input = {7, 1, 5, 3, 6, 4};
        System.out.println(new SolutionV2().maxProfit(input));
        ArrayTools.disp2DArray(memo);

    }
}
```

**解法二改进，状态压缩**

```java
/*

- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])//
    dp(n)[1] = max(dp(n-1)[1], - prices[n-1])
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。
Runtime: 2 ms, faster than 50.63% of Java online submissions for Best Time to Buy and Sell Stock.
Memory Usage: 52 MB, less than 42.96% of Java online submissions for Best Time to Buy and Sell Stock.
* */
class SolutionV2_1 {
    private static int[][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length, state0 = 0, state1 = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            state0 = Math.max(state0, state1 + prices[i-1]);
            state1 = Math.max(state1, -prices[i - 1]);
        }
        return state0;
    }

}
```

###### Best Time to Buy and Sell Stock II

```
* 122. Best Time to Buy and Sell Stock II
* 股票问题二，多次买卖
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
*
```

**解法一：贪心**

one pass, 降价之前卖出，降价的瞬间发生重新购入，
这个直觉是正确的，假设
$$
p[i] < p[i-1] < p[i + k]
$$
此时，以p[i-1]作为最高价格卖出，然后以p[i]购入。
因为
$$
p[i + k] - pre < (p[i+k] - p[i]) + (p[i-1] - pre) \\
= (p[i+k] - pre) + (p[i-1] - p[i])
$$

```java
/*
one pass, 降价之前卖出，降价的瞬间发生重新购入，
这个直觉是正确的，假设
$$
p[i] < p[i-1] < p[i + k]
$$
此时，以p[i-1]作为最高价格卖出，然后以p[i]购入。
因为
$$
p[i + k] - pre < (p[i+k] - p[i]) + (p[i-1] - pre) //
= (p[i+k] - pre) + (p[i-1] - p[i])
$$
Runtime: 1 ms, faster than 71.52% of Java online submissions for Best Time to Buy and Sell Stock II.
        Memory Usage: 38.8 MB, less than 43.47% of Java online submissions for Best Time to Buy and Sell Stock II.
*/

class com.leetcode.dp.q91.Solution {
    public int maxProfit(int[] prices) {
        int n, prof, curProf, curCost;
        n = prices.length; prof = curProf = 0; curCost = prices[0];
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                curProf = prices[i] - curCost;
            } else {
                curCost = prices[i];
                prof += curProf;
                curProf = 0;
            }
        }
        return prof += curProf;
    }
}
```

**解法二动态规划**

- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])\\
    dp(n)[1] = max(dp(n-1)[1], dp(n-1)[0] - prices[n-1])
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。

```java
/*
Runtime: 1 ms, faster than 71.52% of Java online submissions for Best Time to Buy and Sell Stock II.
Memory Usage: 39 MB, less than 27.09% of Java online submissions for Best Time to Buy and Sell Stock II.
* */
class SolutionV2 {
    private static int[][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length, state0 = 0, state1 = Integer.MIN_VALUE,t;
        for (int i = 1; i <= n; i++) {
            t = state0;
            state0 = Math.max(state0, state1 + prices[i-1]);
            state1 = Math.max(state1, t - prices[i - 1]);
        }
        return state0;
    }
```

##### Best Time to Buy and Sell Stock III

```
/*
* 123. Best Time to Buy and Sell Stock III
* 买股票三，买三次
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
*
* */
```

- dp(n, k)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润, n代表天数，k代表可交易的次数
    $$
    dp(n, k)[0] = max(dp(n-1, k)[0], dp(n-1, k)[1] + prices[n-1])\\
    dp(n, k)[1] = max(dp(n-1, k)[1], dp(n-1, k-1)[0] - prices[n-1])
    $$
    
- 状态n

- 选择，当前两个状态从何处发展而来

- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。

**解法一：动态规划，迭代**

```java
/*
Time Limit Exceeded

* */
class SolutionV1 {
    private static int[][][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length;
        memo = new int[n+1][2+1][2];
        return dp(n, 2, prices)[0];
    }

    private int[] dp(int n, int k, int[] prices) {
        if (n == 0 || k == 0) {
            memo[n][k][1] = Integer.MIN_VALUE;
            return memo[n][k];
        }
        if(memo[n][k][0]!=0) return memo[n][k];
        memo[n][k][0] = Math.max(dp(n - 1, k, prices)[0], dp(n - 1, k, prices)[1] + prices[n - 1]);
        memo[n][k][1] = Math.max(dp(n - 1, k, prices)[1],  dp(n - 1, k - 1, prices)[0] - prices[n - 1]);
        return memo[n][k];
    }
}
```

**解法一改进，迭代**

```java
/*
迭代解法
Runtime: 96 ms, faster than 5.40% of Java online submissions for Best Time to Buy and Sell Stock III.
Memory Usage: 65.9 MB, less than 22.71% of Java online submissions for Best Time to Buy and Sell Stock III.
* */
public class SolutionV1_1 {

    public int maxProfit(int[] prices) {
        int n = prices.length, K = 2;
        int memo[][][] = new int[n + 1][K + 1][2];
        for (int i = 0; i <= n; i++) memo[i][0][1] = Integer.MIN_VALUE;
        for (int i = 0; i <= K; i++) memo[0][i][1] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= K; k++) {
                memo[i][k][0] = Math.max(memo[i - 1][k][0], memo[i - 1][k][1] + prices[i-1]);
                memo[i][k][1] = Math.max(memo[i - 1][k][1], memo[i - 1][k - 1][0] - prices[i-1]);
            }
        }
        return memo[n][K][0];
    }
}
```

**解法一改进，状态压缩**

```java
/*
迭代解法, 状态压缩
Runtime: 4 ms, faster than 67.78% of Java online submissions for Best Time to Buy and Sell Stock III.
Memory Usage: 55.5 MB, less than 36.15% of Java online submissions for Best Time to Buy and Sell Stock III.
* */
public class SolutionV1_2 {
    public int maxProfit(int[] prices) {
        int n, K, dp_i_1_0, dp_i_1_1, dp_i_2_0, dp_i_2_1, dp_ip_0_0, dp_ip_1_0;
        n = prices.length; K = 2; dp_i_1_0 = dp_i_2_0 = dp_ip_0_0 = dp_ip_1_0 = 0;dp_i_1_1 = dp_i_2_1 = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            dp_i_1_0 = Math.max(dp_i_1_0,dp_i_1_1 + prices[i-1]);
            dp_i_1_1 = Math.max(dp_i_1_1, dp_ip_0_0 - prices[i-1]);
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + prices[i-1]);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_ip_1_0 - prices[i-1]);
            dp_ip_1_0 = dp_i_1_0;
        }
        return dp_i_2_0;
    }
}
```

#####  Best Time to Buy and Sell Stock IV

```
/*
* 188. Best Time to Buy and Sell Stock IV
* 购买股票四，k次购买
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
*
* */
```

**解法一：动态规划迭代**

```java
/*

Runtime: 7 ms, faster than 35.77% of Java online submissions for Best Time to Buy and Sell Stock IV.
        Memory Usage: 41.8 MB, less than 11.50% of Java online submissions for Best Time to Buy and Sell Stock IV.
*/

class com.leetcode.dp.q91.Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length, K = k;
        int memo[][][] = new int[n + 1][K + 1][2];
        for (int i = 0; i <= n; i++) memo[i][0][1] = Integer.MIN_VALUE;
        for (int i = 0; i <= K; i++) memo[0][i][1] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for ( k = 1; k <= K; k++) {
                memo[i][k][0] = Math.max(memo[i - 1][k][0], memo[i - 1][k][1] + prices[i-1]);
                memo[i][k][1] = Math.max(memo[i - 1][k][1], memo[i - 1][k - 1][0] - prices[i-1]);
            }
        }


        return memo[n][K][0];
    }
}
```

**解法一改进，动态规格，状态压缩**

```java
/*
状态压缩，通用形式
Runtime: 4 ms, faster than 82.54% of Java online submissions for Best Time to Buy and Sell Stock IV.
Memory Usage: 36.5 MB, less than 90.44% of Java online submissions for Best Time to Buy and Sell Stock IV.
* */
class SolutionV2 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int memo[][] = new int[k + 1][2];
        for (int i = 0; i <= k; i++) memo[i][1] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                memo[j][0] = Math.max(memo[j][0], memo[j][1] + prices[i-1]);
                memo[j][1] = Math.max(memo[j][1], memo[j - 1][0] - prices[i-1]);
            }
        }
        return memo[k][0];
    }
}
```

##### Best Time to Buy and Sell Stock with Cooldown

```
/*
* 309. Best Time to Buy and Sell Stock with Cooldown
* 买股票，不限次数，冷冻期
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
* */
```

q122拓展
- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])\\
    dp(n)[1] = max(dp(n-1)[1], dp(n-2)[0] - prices[n-1])
    $$
    
- 状态n

- 选择，当前两个状态从何处发展而来

- base，基值情况比较复杂，当n = 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。
当n = -1时，与n=0作一块处理即可。因为主要是用到了dp[1][1]计算第一天，持有股票的可能，由于第一次买不可能有冷冻期，所以想n=0一样返回0即可

```java
/*
Runtime: 1 ms, faster than 58.45% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
        Memory Usage: 39 MB, less than 14.53% of Java online submissions for Best Time to Buy and Sell Stock with
*/

class com.leetcode.dp.q91.Solution {
    private static int[][] memo;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        memo = new int[n + 1][2];
        return dp(n, prices)[0];
    }

    private int[] dp(int n, int[] prices) {
        if (n <= 0) {
            memo[0][1] = Integer.MIN_VALUE;
            return memo[0];
        }
        if(memo[n][0]!=0) return memo[n];
        memo[n][0] = Math.max(dp(n - 1, prices)[0], dp(n - 1, prices)[1] + prices[n-1]);
        memo[n][1] = Math.max(dp(n - 1, prices)[1],  dp(n-2,prices)[0] - prices[n - 1]);

        return memo[n];
    }
}
```

```java
/*
迭代，状态压缩
Runtime: 0 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
Memory Usage: 36.8 MB, less than 75.51% of Java online submissions for Best Time to Buy and Sell Stock with
* */
public class SolutionV1_1 {
    public int maxProfit(int[] prices) {
        int n = prices.length, dp_i_0, dp_i_1,dp_ip2_0, t;
        dp_i_0 = dp_ip2_0 = 0;dp_i_1 = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i - 1]);
            dp_i_1 = Math.max(dp_i_1, dp_ip2_0 - prices[i - 1]);
            dp_ip2_0 = t;
        }
        return dp_i_0;
    }
}
```

##### Best Time to Buy and Sell Stock with Transaction Fee

```
/*
* 714. Best Time to Buy and Sell Stock with Transaction Fee
* 买股票，多次购买，交易费
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
* */
```

q122拓展
- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])\\
    dp(n)[1] = max(dp(n-1)[1], dp(n-1)[0] - prices[n-1] - fee)
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。

```java
/*
Runtime: 3 ms, faster than 93.90% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
        Memory Usage: 48.2 MB, less than 68.41% of Java online submissions for Best Time to Buy and Sell Stock with
*/

class com.leetcode.dp.q91.Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length, dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE,t;
        for (int i = 1; i <= n; i++) {
            t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i-1]);
            dp_i_1 = Math.max(dp_i_1, t - prices[i - 1] - fee);
        }
        return dp_i_0;
    }
}
```