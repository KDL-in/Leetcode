## 博弈游戏

### 石头游戏

由两人分别取石头，假定二人每次都是最优策略，在一定的规则下，求最终结果的一类题目。这类题目体现了动态规划的特点：

- 本质上是暴力求解所有子问题，但是不重复
- 需要良好的dp定义，保证子问题不重叠

#### 框架

这是一种通用的博弈游戏的套路。虽然效率比较低。

````java
    int[][][] memo;

    int[] dp(int i, int j, int[] piles) {
        if (j <= i) return memo[i][j];
        if (memo[i][j][0] != 0) return memo[i][j];
        // 选择，
        int left, right;
        // dp(i，j)先手 = 当前选择i获得值 + dp(i+1, j)后手
        // dp(i, j)后手 = dp(i+1, j)先手
        if ((left = piles[i] + dp(i + 1, j, piles)[1]) > (right = piles[j] + dp(i, j - 1, piles)[1])) {
            memo[i][j][0] = left;
            memo[i][j][1] = dp(i + 1, j, piles)[0];
        } else {
            memo[i][j][0] = right;
            memo[i][j][1] = dp(i, j - 1, piles)[0];
        }
        return memo[i][j];
    }
````

#### 877. Stone Game

>  * 石头游戏，博弈游戏，取首尾石堆
>  * https://leetcode.com/problems/stone-game/

dp分析

- dp(i,j)定义为[i,j]Alex先手能获得的最大分数
    $$
    dp(i,j) = max(p[i] + min(dp(i+2, j), dp(i+1,j-1),
                    p[j] + min(dp(i+1, j-1),dp(i,j-2)))
    $$
    其含义是，Alex能选择左右两个堆，即i和j，他的最优化目标是max，而lee则是在alex先手之后，继续选择，它的目标是min让Alex能获得的dp()最小
    
- 状态i，j

- 选择，i或j，lee的选择

- base，当j < i的是时候，说明二者之间不存在任何石头，直接return 0，而从题目规定石头堆数为偶数，从递推公式来看，不可能出现i=j的情况，不需处理

**解法一**

````java
/*
Runtime: 11 ms, faster than 27.04% of Java online submissions for Stone Game.
Memory Usage: 44.8 MB, less than 18.94% of Java online submissions for Stone Game.

*/

class Solution {
    private int[][] memo;
    public boolean stoneGame(int[] piles) {
        int n = piles.length, sum = 0;
        for (int p : piles) {
            sum += p;
        }
        memo = new int[n][n];
        return dp(0, n - 1, piles) * 2 > sum ;
    }

    private int dp(int i, int j, int[] piles) {
        if (j < i) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] = Math.max(
                piles[i] + Math.min(dp(i + 2, j, piles), dp(i + 1, j - 1, piles)),
                piles[j] + Math.min(dp(i + 1, j - 1, piles), dp(i, j - 2, piles))
        );
        return memo[i][j];
    }

}

````

**解法二，经典解法**

直观的博弈动态规划。
- dp(i, j)定义为先手后手分别能得到的最高分数，先手可以选择左边或者右边其中更大的部分，而后手则取决于先手的选择。


````java
        // 先手选择更大者
        if ((begin = piles[i] + dp(i + 1, j)[1]) > (end = piles[j] + dp(i, j - 1)[1])) {
            memo[i][j][0] = begin;
            // 后手等于下一步的先手
            memo[i][j][1] = dp(i + 1, j)[0];
        } else {
            memo[i][j][0] = end;
            // 后手等于下一步的先手
            memo[i][j][1] = dp(i, j - 1)[0];
        }
````

- 状态，i，j
- 选择，先手选择左或右，影响后手
- base，当i=j时，只剩下一个，此时必定轮到lee，Alex得到的分数为0， return memo[i][j]****

````java
/*


Runtime: 52 ms, faster than 9.18% of Java online submissions for Stone Game.
Memory Usage: 49.3 MB, less than 10.75% of Java online submissions for Stone Game.

*/

class SolutionV2 {
    private int[][][] memo;

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        memo = new int[n][n][2];

        return dp(0, n - 1, piles)[0] > dp(0, n - 1, piles)[1];
    }

    private int[] dp(int i, int j, int[] piles) {
        if (j <= i) return memo[i][j];
        if (memo[i][j][0] != 0) return memo[i][j];
        int left, right;
        if ((left = piles[i] + dp(i + 1, j, piles)[1]) > (right = piles[j] + dp(i, j - 1, piles)[1])) {
            memo[i][j][0] = left;
            memo[i][j][1] = dp(i + 1, j, piles)[0];
        } else {
            memo[i][j][0] = right;
            memo[i][j][1] = dp(i, j - 1, piles)[0];
        }
        return memo[i][j];
    }

}
````
````java
/*
先手必赢，由于总数为奇数，堆数为偶数
也就是说，任意石堆都是“奇偶奇偶奇偶”，可以选择拿走所有奇，也可以选择拿走所有偶。
因为所有奇！=所有偶，所以先手选择的必定获胜
* */
public class SolutionV3 {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}

````
#### Stone Game II
拿走特定规则的堆
* 1140. Stone Game II
* 石头游戏II
* https://leetcode.com/problems/stone-game-ii/

这道题的突破口依然是“选择”，从哪里划堆的问题
- dp(i,M)定义为面对[i:)的石头，前手后手分别能得到的最高分数

        M' = max(M, (j - i) + 1)
        dp(i,M)[0] = max(sum(i, j) + dp(j + 1, M')[1]), where i <= j < i + 2M, while
        dp(i,M)[1] = dp(j + 1, M')[0]

- 选择，选择从哪里划堆，划分点1 <= x <= 2M
- 状态， i，M
- base， 基值条件比较特殊，当剩下的石头能够一次取完，则直接取完即可

````java
/*
这道题的突破口依然是“选择”，从哪里划堆的问题
- dp(i,M)定义为面对[i:)的石头，前手后手分别能得到的最高分数
        M' = max(M, (j - i) + 1)
        dp(i,M)[0] = max(sum(i, j) + dp(j + 1, M')[1]), where i <= j < i + 2M, while
        dp(i,M)[1] = dp(j + 1, M')[0]
- 选择，选择从哪里划堆，划分点1 <= x <= 2M
- 状态， i，M
- base， 基值条件比较特殊，当剩下的石头能够一次取完，则直接取完即可

Runtime: 24 ms, faster than 19.72% of Java online submissions for Stone Game II.
        Memory Usage: 39.1 MB, less than 25.51% of Java online submissions for Stone Game II.

*/

class Solution {
    private int[][][] memo;
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        memo = new int[n][n+1][2];
        return dp(0, 1, piles)[0];
    }

    private int[] dp(int i, int M, int[] piles) {
        int N;
        if ((N = (i + (M << 1))) >= piles.length) {
            for (int j = i; j < piles.length; j++) memo[i][M][0] += piles[j];
            return memo[i][M];
        }
        if (memo[i][M][1] != 0) return memo[i][M];
        int sum = 0, x, fir;
        for (int j = i; j < N; ++j) {
            sum += piles[j];
            x = Math.max(M, (j - i) + 1);
            if ((fir = sum + dp(j + 1, x, piles)[1]) > memo[i][M][0]) {
                memo[i][M][0] = fir;
                memo[i][M][1] = memo[j+1][x][0];
            }
        }
        return memo[i][M];
    }

}
````

#### 1406. Stone Game III

```
/*
* 1406. Stone Game III
* 石头游戏三，选择1，2，3堆
* https://leetcode.com/problems/stone-game-iii/
*
* */
```

这道题的突破口依然是“选择”，每次可选择1到3堆
- dp(i)定义为面对[i:)的石头，前手后手分别能得到的最高分数
    $$
    dp(i)[0] = max(sum(i, j) + dp(j + 1)[1]), 
    where \\
    i <= j < i + 3, while\\
        dp(i)[1] = dp(j + 1)[0]
    $$
    
- 选择，选择从哪里划堆，划分点1 2 3

- 状态， i

- base， 当i = 石头堆数的时候，表示已经没有石堆，能得到的分数为0

```java
/*
Runtime: 84 ms, faster than 17.71% of Java online submissions for Stone Game III.
Memory Usage: 58 MB, less than 30.95% of Java online submissions for Stone Game III.
*/


class Solution {
    private static int[][] memo;
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n+1][2];
        dp(0, stoneValue);
        return memo[0][0] > memo[0][1] ? "Alice" : ((memo[0][0] == memo[0][1]) ? "Tie" : "Bob");
    }

    private int[] dp(int i, int[] stoneValue) {
        if (i  == stoneValue.length) {
            return memo[i];
        }
        if (memo[i][0]!=0) return memo[i];
        int sum = 0, fir;
        memo[i][0] = memo[i][1] = Integer.MIN_VALUE;
        for (int k = 1; k <= 3 && (i + k) <= stoneValue.length; ++k) {
            sum += stoneValue[i + k - 1];
            if ((fir = dp(i + k, stoneValue)[1] + sum) > memo[i][0]) {
                memo[i][0] = fir;
                memo[i][1] = memo[i + k][0];
            }
        }
        return memo[i];
    }
}
```

#### Stone Game IV

```
/*
 * 1510. Stone Game IV
 * 石头游戏四，取平方数，取不了输
 * https://leetcode.com/problems/stone-game-iv/
 * */
```

- dp(n)定义为n个石头时先手能否获胜
    $$
        dp(n) = !dp(n-sq), where sq <= n, sq = i * i, i = 1,2,3,\cdots
    $$
  对于dp(n)，先手胜出的唯一条件是找到dp(n-i*i)先手失败的子问题，则取对应的石子，对该子问题，先手必输
  
- 状态n

- 选择，找到先手失败的子问题

- base，dp(0)，没有石头可取，return false

**解法一**

```java
/*
- dp(n)定义为n个石头时先手能否获胜
    $$
        dp(n) = !dp(n-sq), where sq <= n, sq = i * i, i = 1,2,3,\cdots
    $$
  对于dp(n)，先手胜出的唯一条件是找到dp(n-i*i)先手失败的子问题，则取对应的石子，对该子问题，先手必输
- 状态n
- 选择，找到先手失败的子问题
- base，dp(0)，没有石头可取，return false
Runtime: 16 ms, faster than 65.41% of Java online submissions for Stone Game IV.
Memory Usage: 35.9 MB, less than 90.88% of Java online submissions for Stone Game IV.* */
class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[]memo = new boolean[n + 1];
        int i, sq;
        for (int k = 1; k <= n ; k++) {
            i = 1;
            while ((sq = i*i++) <= k && (memo[k] = memo[k-sq])) ;
            memo[k] = !memo[k];
        }
        return memo[n];
    }
}
```

**解法二**

```java
/*
官方解，类似素数筛选法，效率要高很多。
* */
public class SolutionV2 {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            if (dp[i]) {
                continue;
            }
            for (int k = 1; i + k * k <= n; k++) {
                dp[i + k * k] = true;
            }
        }
        return dp[n];
    }
}
```

#### Stone Game V

```
/*
 * 1563. Stone Game V
 * 石头游戏V，划堆
 * https://leetcode.com/problems/stone-game-v/
 * */
```
选择，很容易看出来，就是从哪里划分为两个堆。
- dp(i,j)定义为[i，j]能得到的最高分数
        
        ````java
            if (l > r)
                dp(i, j) = max(r + dp(k + 1, j))
            else if (l < r)
                dp(i, j) = max(l + dp(i, k));
            else
                dp(i, j) = max(max(dp(k + 1, j) + r, dp(i, k) + l) );
            where i <= k < j
        ````
        
- 状态 i，j

- 选择，从哪里划分为两个堆

- base，当i==j时，说明此时只剩下一个石头，直接return 0

  

  时间复杂度，n个子问题，每个子问题都需要sqrt(n)次，因此，O(n sqrt(n))

````java
package com.leetcode.dp.game.q1563;

import com.leetcode.common.array.ArrayTools;

/*
 * 1563. Stone Game V
 * 石头游戏V，划堆
 * https://leetcode.com/problems/stone-game-v/
 *
 * */
/*
选择，很容易看出来，就是从哪里划分为两个堆。
- dp(i,j)定义为[i，j]能得到的最高分数
            if (l > r)
                dp(i, j) = max(r + dp(k + 1, j))
            else if (l < r)
                dp(i, j) = max(l + dp(i, k));
            else
                dp(i, j) = max(max(dp(k + 1, j) + r, dp(i, k) + l) );
            where i <= k < j
- 状态 i，j
- 选择，从哪里划分为两个堆
- base，当i==j时，说明此时只剩下一个石头，直接return 0
时间复杂度，n个子问题，每个子问题都需要sqrt(n)次，因此，O(n sqrt(n))
Runtime: 180 ms, faster than 46.58% of Java online submissions for Stone Game V.
        Memory Usage: 40 MB, less than 71.99% of Java online submissions for Stone Game V.

*/

class Solution {
    private static int[][] memo;
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length, sum = 0;
        memo = new int[n][n];
        for (int v : stoneValue) sum += v;
        return dp(0, n-1, sum, stoneValue);
    }

    private int dp(int i, int j, int r, int[] stoneValue) {
        if (j == i) return 0;
        if (memo[i][j]!=0) return memo[i][j];
        int l = 0;
        for (int k = i; k < j; k++) {
            l += stoneValue[k];
            r -= stoneValue[k];
            System.out.println(i + " " + k +  " " + (k+1) + " " + j);
            if (l > r) {
                memo[i][j] = Math.max(memo[i][j], r + dp(k + 1, j, r, stoneValue));
            } else if (l < r) {
                memo[i][j] = Math.max(memo[i][j], l + dp(i, k, l, stoneValue));
            } else {
                memo[i][j] = Math.max(memo[i][j], Math.max(dp(k + 1, j, r, stoneValue), dp(i, k, l, stoneValue)) + l);
            }
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        int[] input = {6, 2, 3, 4, 5, 5};
        new Solution().stoneGameV(input);
        ArrayTools.disp2DArray(memo);
    }
}
````

#### Stone Game VI

```
/*
 * 1686. Stone Game VI
 * 石头游戏六
 * https://leetcode.com/problems/stone-game-vi/
 *
 * */
```

贪心，贪心策略为，每次选择v1+v2最大的石头，因为选择某一石头，除了得到v1还使得对方减少v2

从动态规划的角度考虑，最高分数 = 当前选择石子 +　递归求最高分数（剩余石头）
由于当前选择石子有ｎ种选择，该方法复杂度会很高，而且也不好实现。

从贪心角度考虑，不需要遍历ｎ种选择，直接确定最佳选择。

**证明：**

假设当前分数为　S1, S2，当前贪心选择最高得分v = v1 +　v2，
若最优选择为贪心选择以外的任意选择v'=v1'+v2'，v > v',
则有贪心选择A,B二人得分为，$s1+v1, s2+v2$。最优选择二人得分为$s1'+v1', s2'+v2'$，则两种选择之后A,B获得总值差为
$$
(s1+v1)-(s2+v2') = (s1-s2) + (v1-v2') \\
    (s1+v1')-(s2+v2) = (s1-s2) + (v1'-v2)
$$
上式-下式，得到
$$
    (v1-v2')-(v1'-v2) = (v1+v2)-(v1'+v2')
$$
结果大于0，说明A使用贪心选择得到更优解，则与假设相反，证明完毕。

```java
/*

Runtime: 71 ms, faster than 89.27% of Java online submissions for Stone Game VI.
        Memory Usage: 49.3 MB, less than 88.41% of Java online submissions for Stone Game VI.
*/

class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length, s1 = 0, s2 = 0;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].idx = i;
            nodes[i].val = aliceValues[i] + bobValues[i];
        }
        Arrays.sort(nodes, (a, b) -> (b.val-a.val));
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) s1 += aliceValues[nodes[i].idx];
            else s2 += bobValues[nodes[i].idx];
        }
        return s1 > s2 ? 1 : ((s1 == s2) ? 0 : -1);
    }

    class Node {
        int idx, val;
    }

}
```

#### Stone Game VII

```
* 1690. Stone Game VII
* 石头游戏七，最大差值
* https://leetcode.com/problems/stone-game-vii/
```

博弈先手后手的框架，只不过优化目标改为最大化差值，注意，最大化差值，不是最大化获得值
- dp(i, j)定义为[i:j]的石头能获得的最大差值时的分数，包含两值，先手和后手
    $$
    dp(i, j).fir = max(sum_l + dp(i+1, j).sec - dp(i+1, j).fir, sum_r + dp(i, j-1).sec - dp(i, j-1).fir)
    $$
   当前先手得到的分数，等于选择左（或右）得到的分数（剩余石子和）+ 子问题.后手得到的分数。选择左右中差值最大的。
- 状态i，j
- 选择，选择左或右中差值更大的
- base，当i==j时，此时必为Bob的轮次，只剩下一个，去除后只能得到0，return 0

```java

/*


Runtime: 481 ms, faster than 11.09% of Java online submissions for Stone Game VII.
Memory Usage: 107.7 MB, less than 13.77% of Java online submissions for Stone Game VII.
*/

class Solution {
    private int[][][] memo;
    public int stoneGameVII(int[] stones) {
        int n = stones.length, sum = 0;
        memo = new int[n][n][2];
        for (int stone : stones) sum += stone;

        dp(0, n - 1, stones, sum);
        return memo[0][n - 1][0] - memo[0][n - 1][1];
    }

    private int[] dp(int i, int j, int[] stones, int sum) {
        if (i == j)  return memo[i][j];

        if (memo[i][j][0]!=0) return memo[i][j];
        int l_fir, r_fir, l_sec, r_sec;
        l_fir = (sum - stones[i]) + dp(i + 1, j, stones, (sum - stones[i]))[1];
        r_fir = (sum - stones[j]) + dp(i, j - 1, stones, (sum - stones[j]))[1];
        l_sec = memo[i+1][j][0];
        r_sec = memo[i][j-1][0];
        if (l_fir - l_sec > r_fir - r_sec) {
            memo[i][j][0] = l_fir;
            memo[i][j][1] = l_sec;
        } else {
            memo[i][j][0] = r_fir;
            memo[i][j][1] = r_sec;
        }
        // System.out.println(i + " " + j + " " + memo[i][j][0] + " " + memo[i][j][1]);
        return memo[i][j];
    }

    // public static void main(String[] args) {
    //     int[] input = {5, 3, 1, 4, 2};
    //     System.out.println(new Solution().stoneGameVII(input));
    //
    // }

}
```