### 贪心

#### 区间调度

区间调度贪心性质证明。

假设任意子问题$S_{ij}$ 最早结束的任务为 $a_m$，则$a_m$必定在其中一个最优解之中。

证明：

若$A_{ij}$为$S_{ij}$ 的一个最优解，$a_k$为其中最早结束的任务。如果$a_m=a_j$，则符合上述定理。如果$a_m\neq a_j$，则由于前者为结束最早的任务，则必定可以用前者取代后者，成为新的最优解，该最优解符合定理。证明完成。

由于以上证明，对于任何时刻的子问题必定有，最早结束的任务，必然在该子问题的解中。

##### 435 删除重叠区间

> * 435. Non-overlapping Intervals
> * 删除重叠的区间
> * https://leetcode.com/problems/non-overlapping-intervals/

````java
/*
贪心
Runtime: 3 ms, faster than 59.83% of Java online submissions for Non-overlapping Intervals.
        Memory Usage: 38.6 MB, less than 92.34% of Java online submissions for Non-overlapping Intervals.
*/

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int res = 0, x = 0;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[x][1] > intervals[i][0]) ++res;
            else x = i;
        }
        return res;
    }
} 
````

##### 452 射气球

>  * 452. Minimum Number of Arrows to Burst Balloons
>  * 射气球
>  * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

````java
/*
贪心
Runtime: 14 ms, faster than 99.53% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
Memory Usage: 46.7 MB, less than 54.97% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
* */
class Solution {
    public int findMinArrowShots(int[][] points) {
        int res = 1;
        Arrays.sort(points, (a, b) -> a[1] > b[1] ? 1 : -1);
        if (points.length == 0) return 0;
        int[] x = points[0];
        for (int[] point : points) {
            if (point[0] > x[1]) {
                x = point;
                ++res;
            }
        }
        return res;
    }
}
````

#### 其他

##### 45 跳到最后的步数

> * 45. Jump Game II
> * 跳跃游戏
> * https://leetcode.com/problems/jump-game-ii/

**动态规划**

先来看动态规划的分析

- dp[i]定义为到达该点所需要的最小步数
      dp[i] = min(dp[i], dp[j] + 1), where 0 <= j < i and j + nums[j] >= i
      需要遍历之前的状态找到最优解
- 状态为，i，dp[i]
- 选择，选择0-i中的哪个子问题最优解来得到当前最优解
- base，dp[0] = 0，不需要任何移动。

- 时间复杂度，O(N^2)，空间O(N)，需要考虑i之前的所有状态。

**贪心**

每次需要考虑所有子问题的最优解才能得出最优解吗？答案是否定的

该问题具备贪心性质，即，当前的可选集合需要跳到未知集合，只需要选择跳的最远的步数即可，因为跳得最远的未知集合，必然包含其他选择得到的未知集合。

**解法一，动态规划**

````java
/*
超时，动态规划。
dp[i]定义为到达该点所需要的最小步数
    dp[i] = min(dp[i], dp[j] + 1), where 0 <= j < i and j + nums[j] >= i
    需要遍历之前的状态找到最优解
时间复杂度，O(N^2)，空间O(N)
* */
class Solution {
    public int jump(int[] nums) {
        int N = nums.length;
        int dp[] = new int[N];
        for (int i = 1; i < N; i++) {
            dp[i] = N;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[N-1];
    }
}
/*
* 45. Jump Game II
* 跳跃游戏
* https://leetcode.com/problems/jump-game-ii/
* */
class SolutionV1_1 {
    public int jump(int[] nums) {
        int N = nums.length;
        int dp[] = new int[N],maxJump[] = new int[N];
        for (int i = 0; i < N; i++) maxJump[i] = nums[i] + i;
        for (int i = 1; i < N; i++) {
            dp[i] = N;
            for (int j = 0; j < i; j++) {
                if (maxJump[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[N-1];
    }
}
````

**解法二，其他解法，倒着跳**

````java
/*
倒着跳，节省常数时间，但该解法在1111111，这种序列的时候表现为O(N^2)
Runtime: 399 ms, faster than 11.88% of Java online submissions for Jump Game II.
        Memory Usage: 41.5 MB, less than 26.16% of Java online submissions for Jump Game II.
*/

public class SolutionV1_2 {
    public int jump(int[] nums) {
        int N = nums.length, step = 0,x;
        int maxJump[] = new int[N];
        for (int i = 0; i < N; i++) maxJump[i] = nums[i] + i;
        x = N - 1;
        while (x != 0) {
            for (int i = 0; i < x; i++) {
                if (maxJump[i] >= x) {
                    x = i;
                    ++step;
                    break;
                }
            }
        }
        return step;
    }
}
````

**解法三，贪心**

````java
/*
贪心选择，每次只需要选择可选位置中跳得最远的。因为跳得最远的必然包含其他所有可选跳法的所有下一次可选集合。
Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game II.
Memory Usage: 41.3 MB, less than 32.90% of Java online submissions for Jump Game II.

*/
public class SolutionV2 {

    public int jump(int[] nums) {
        int step, l, r, n, maxr;
        step = r = 0;n = nums.length; l = -1;
        while (r < n - 1){
            maxr = r;
            for (int i = l + 1; i <= r; i++) {
                maxr = Math.max(maxr, i + nums[i]);
            }
            l = r;
            r = maxr;
            step++;
        }

        return step;
    }
}
/*
非常漂亮的实现。
Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game II.
Memory Usage: 41.5 MB, less than 26.16% of Java online submissions for Jump Game II.
* */

public class SolutionV3 {
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}

````



##### 55 是否可能跳到最后

> * 55. Jump Game
> * 跳远游戏
> * https://leetcode.com/problems/jump-game/

````java
/*

Runtime: 1 ms, faster than 86.85% of Java online submissions for Jump Game.
Memory Usage: 41 MB, less than 64.09% of Java online submissions for Jump Game.
*/

class Solution {
    public boolean canJump(int[] nums) {
        int step, l, r, n, maxr;
        step = r = 0;n = nums.length; l = -1;
        while (r < n - 1){
            maxr = r;
            for (int i = l + 1; i <= r; i++) {
                maxr = Math.max(maxr, i + nums[i]);
            }
            if (maxr == r) return false;
            l = r;
            r = maxr;
            step++;
        }

        return true;
    }
}
````

