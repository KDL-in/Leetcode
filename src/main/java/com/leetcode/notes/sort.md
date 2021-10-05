## 排序相关的专题

### 区间

#### 56. Merge Intervals

```
/*
 * 56. Merge Intervals
 * 合并区间
 * https://leetcode.com/problems/merge-intervals/
 *
 * */
```

思路，对区间进行排序，遍历区间，考察它与前一个是否相交，
相交则两区间相容，改变当前区间。

当前只需要考察前一个区间的原因在于，
- 若与前一个区间相融，则直接融合即可，不需要考察再之前的区间（它们与前一个区间不相容）
- 若与前一个区间不相容，它不可能与更前面的区间相容，更前面的区间与前一个区间不相容（相容则已经被融合再前面一个区间里）

```java
/*


Runtime: 5 ms, faster than 94.78% of Java online submissions for Merge Intervals.
Memory Usage: 41.7 MB, less than 55.95% of Java online submissions for Merge Intervals.

* */
class com.leetcode.dp.q91.Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<Integer> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]));
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] <= intervals[i - 1][1]) {
                // 相交的情况
                intervals[i][0] = intervals[i - 1][0];
                if (intervals[i][1] < intervals[i - 1][1]) intervals[i][1] = intervals[i - 1][1];
            } else {
                // 前一个和当前无相交，则它不可能与之后的有相交
                res.add(i - 1);
            }
        }
        res.add(n - 1);
        int[][] resArray = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = intervals[res.get(i)];
        }
        return resArray;
    }

}
```

看看官方解

```java
/*
官方的解，写得很漂亮
* */
class SolutionV2 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                // 这里是巧用引用
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        // 二维数组的toArray
        return merged.toArray(new int[merged.size()][]);
    }
}
```

#### 986. Interval List Intersections

```
/*
* 986. Interval List Intersections
* 两有序区间列表求交集
* https://leetcode.com/problems/interval-list-intersections/
*
* */
```

不聪明的解法

思路，由于两个列表是有序区间，且区间内不相交。则遍历列表1，每次只需要考察列表2当前的区间即可。

然后就是有点乱的逻辑判断。假设a和b分别为两列表当前的区间。

```java
/*
Runtime: 3 ms, faster than 65.36% of Java online submissions for Interval List Intersections.
        Memory Usage: 40 MB, less than 48.55% of Java online submissions for Interval List Intersections.

*/

class com.leetcode.dp.q91.Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i, j, n, m;
        int[] a, b;
        List<int []> res = new ArrayList<>();
        n = firstList.length; m = secondList.length;
        i = j = 0;
        while (i < n && j < m) {
            a = firstList[i];
            b = secondList[j];
            if (a[0] <= b[1]) {
                //a[0]<=b[1]，满足了相交第一个条件
                if (a[1] >= b[0]) {
                    // a[1] >= b[0] a[0] <= b[1]，必然产生交集
                    res.add(new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])});
                    if (a[1] <= b[1]) i++; // 当前a和当前的b有交集，但不可能与之后的b有交集
                    else j++; // 当前a和b有交集，但b不可能与之后的a有交集
                } else {
                    // a[1] < b[0], a不可能与之后的b有相交
                    i++;
                }
            } else {
                //a[0]>b[1]，可确定当前b不可能和之后的a有交集
                j++;
            }
        }
        int [][]resArray = new int[res.size()][];
        for (int k = 0; k < res.size(); k++) {
            resArray[k] = res.get(k);
        }
        return resArray;
    }
}
```

```java
/*
官方解，极为简洁

这件事只要考虑两区间，相交与不相交即可。
* */
class SolutionV2 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi)
                ans.add(new int[]{lo, hi});

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1])
                i++;
            else
                j++;
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
```

#### 1288. Remove Covered Intervals

```
/*
* 1288. Remove Covered Intervals
* 删除被完全覆盖的区间
* https://leetcode.com/problems/remove-covered-intervals/
*
* */
```

先排序，再贪心。排序需要按第一关键字升序，第二关键字降序。

证明贪心：

排序后已经和第一关键字无关（左区间）——i的左区间一定可以被i-1的左区间包括。

只考虑第二关键字。假设a为0到i-1中，第二关键字最大的，即a[1]最大，则

若当前b[1] > a[1]，则后序a所能覆盖所有区间，必定可以被b覆盖。所以后序所有区间只需要检查b即可。

```java
/*


Runtime: 4 ms, faster than 98.21% of Java online submissions for Remove Covered Intervals.
Memory Usage: 39.3 MB, less than 74.78% of Java online submissions for Remove Covered Intervals.
* */
class com.leetcode.dp.q91.Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int count, n, i, j;
        count = i = 0; j = 1;
        n = intervals.length;
        Arrays.sort(intervals, (a, b) -> (a[0] == b[0]) ? b[1] - a[1] : a[0] - b[0]);
        while (j < n) {
            if (intervals[j][1] <= intervals[i][1]) count++;
            else i=j;
            j++;
        }
        return n - count;
    }
}
```