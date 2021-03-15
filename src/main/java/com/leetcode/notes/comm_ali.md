#### 815. Bus Routes

```java
/*
 * 815. Bus Routes
 * 图， bfs
 * https://leetcode.com/problems/bus-routes/
 *
 *
 * */

/*
切换访问时机，探测时访问
Runtime: 71 ms, faster than 32.80% of Java online submissions for Bus Routes.
Memory Usage: 97.2 MB, less than 8.98% of Java online submissions for Bus Routes.
* */
class SolutionV2_1 {
    
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
        if(source == target) return step;
        while(!q.isEmpty()){
            int size = q.size();
            ++step;
            while( size-- > 0 ){
                int city = q.poll();
                
                Set<Integer> buses = map.getOrDefault(city, null);
                if(buses==null) continue;
                for (int bus : buses){
                    if(vis.contains(bus)) continue;
                    for(int arrCity : routes[bus]){
                        if(arrCity == target) return step;
                        q.add(arrCity);
                    }
                    vis.add(bus);
                }
                
            }
            
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

#### 1388. Pizza With 3n Slices

刚刚看这道题有点像打家劫舍二，但是这题有限状态机反而不好做，原因是因为你一旦原则，后续的数组本身会发生变化，这时候
很难决定当前两个状态(选或不选)来源于之前的哪些状态，当然这是我的判断而已啦。

然后这道题需要转换为等价问题 = 给定3n数组，找到n个数不相邻和最大。

为什么可以这么转换呢？
- 对于n=1，即有3个元素的时候，选一个，另外两个必为空，在环型数组上，必定有两个相邻连续的没选的为0.
- 对于n>1，必定能找到一个元素，相邻的连续两个没选，例如xx1x或x1xx，为什么呢，你可以手动模拟一下，
当消去一定元素之后，一定会剩下3个元素的case，这时候同第一个说明，即必定存在xx1x或x1xx这种情形
- 有了上述结论，每次消去x1x或x1x，那么n=n-1，则问题规模变小，一直消去，消去过程一定有，1选中，相邻的两个元素为x
即，选择的数彼此不相邻。

然后动态规划就好做了，以下是无状态优化的递归形式，最好理解了

$$
dp(i, j) = max(dp(i-2, j-1, slices)+slices[j], dp(i-1, j, slices));
$$

然后另外一个问题，环型数组该怎么办，和打家劫舍二一样，考虑首尾不可能同时取到，只需要
计算一遍0:n-1和1:n即可。

```java
/*
* 1388. Pizza With 3n Slices
* 3n披萨
* https://leetcode-cn.com/problems/pizza-with-3n-slices/
* */
/*

Runtime: 7 ms, faster than 46.55% of Java online submissions for Pizza With 3n Slices.
Memory Usage: 38.4 MB, less than 87.07% of Java online submissions for Pizza With 3n Slices.
Next challenges:
* */
class Solution {
    private int [][] memo;
    public int maxSizeSlices(int[] slices) {
        int n, n3, s1, s2;
        n3 = slices.length; n = n3 / 3;
        memo = new int[n3][n+1];
        s1 = dp(n3-2, n, slices);
        for (int i = 0; i < n3; i++) Arrays.fill(memo[i],0);
        s2 = dp(n3-2, n, Arrays.copyOfRange(slices, 1, n3));
        return Math.max(s1, s2);
    }
    private int dp(int i, int j , int[] slices){
        if(i < 0||j < 0) return 0;
        if(memo[i][j]!=0) return memo[i][j];
        memo[i][j] = Math.max(dp(i-2, j-1, slices)+slices[j], dp(i-1, j, slices));
        return memo[i][j];
    }
}
```

#### 1539. Kth Missing Positive Number

```java
/*
* 1539. Kth Missing Positive Number
* 寻找确实的正数
* https://leetcode.com/problems/kth-missing-positive-number/
*
*
* */
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

#### 模拟题

```java
/*
模拟题，只能朝着一个方向走，撞墙停止，四个方向
读取输入不太习惯，nextInt完需要nextLine读掉\n，-_-
input:
3 4 4
@...
.#..
....
EAST
SOUTH
WEST
NORTH
output:
1 3
* */
public class p1_3_10 {

    public static void main(String[] args) {
        String dir;
        int k, n, m, s, x = 0, y = 0;
        char map[][];
        int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        map = new char[n][m];
        sc.nextLine();
//        System.out.println(n +" "+m+" ");
        for (int i = 0; i < n; i++) {
            dir = sc.nextLine();
            for (int j = 0; j < m; ++j) {
                map[i][j] = dir.charAt(j);
                if (map[i][j] == '@') {
                    x = i;
                    y = j;
                }
            }
        }

        while (k-- > 0) {
            dir = sc.nextLine();
            s = getIdx(dir);
//            System.out.println(dir +" "+s);
            while (check(x + dirs[s][0], y + dirs[s][1], n, m,map)) {
                x += dirs[s][0];
                y += dirs[s][1];
//                System.out.println(x +" " +y);
            }
        }
//        System.out.println((x + 1) + " " + (y + 1));
    }

    private static boolean check(int x, int y, int n, int m, char[][] map) {
        return x >= 0 && x < n && y >= 0 && y < m && map[x][y] != '#';
    }

    private static int getIdx(String dir) {
        if (dir.equals("EAST")) return 0;
        if (dir.equals("SOUTH")) return 1;
        if (dir.equals("WEST")) return 2;
        if (dir.equals("NORTH")) return 3;
        return -1;
    }
}
```