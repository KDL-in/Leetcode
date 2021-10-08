## 常见面试题

#### 吃葡萄

```
/*
* 吃葡萄
* 有意思的题目，三个人需要吃完所有三种葡萄，但要求尽可能平均
* 每个人只能吃期中两种
* https://www.nowcoder.com/questionTerminal/14c0359fb77a48319f0122ec175c9ada?f=discussion
* 使用三角形来解
* https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247486666&idx=1&sn=08edde4151b296ae2871ad89142a6ad3&chksm=9bd7f2c2aca07bd4c22cce610c862fc8e4432901bae6f67655e241af5e6940a512bd7216caac&scene=21#wechat_redirect
* */
```

```java
public class EatFruit {
    public static void main(String[] args) {
        int N;
        long a, b, c;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            N = sc.nextInt();
            while (N-- > 0) {
                a = sc.nextLong();
                b = sc.nextLong();
                c = sc.nextLong();
                System.out.println(eatFruit(new long[]{a, b, c}));
                ;
            }
        }
    }

    private static long eatFruit(long[] num) {
        Arrays.sort(num);
        if (2*(num[0] + num[1]) < num[2] ) return (num[2] + 1) / 2;
        return (num[0]+num[1]+num[2]+2)/3;
    }
}
```

#### 43. Multiply Strings

```
/*
 * 43. Multiply Strings
 * 模拟乘法
 * https://leetcode.com/problems/multiply-strings/
 * */
```

```java
/*
反向计算是比较好的实现。

应该使用int数组来模拟比较方便点，先申请足够的位置，就不必考虑add增加位置的问题。
Runtime: 7 ms, faster than 38.57% of Java online submissions for Multiply Strings.
Memory Usage: 38.6 MB, less than 95.77% of Java online submissions for Multiply Strings.
* */
class com.leetcode.dp.q91.Solution {
    public String multiply(String num1, String num2) {
        int n, m, a1, a2, t;
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder res = new StringBuilder();
        n = num1.length();
        m = num2.length();
        for (int i = 0; i < m; i++) res.append(0);
        for (int i = 0; i < n; i++) {
            a1 = a2 = 0;
            for (int j = 0; j < m; j++) {
                t = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + a1;
                a1 = t / 10;
                t = t % 10 + a2 + (res.charAt(i + j) - '0');
                res.setCharAt(i + j, (char) ((t % 10) + '0'));
                a2 = t / 10;
            }
            if (a1 != 0 || a2 != 0) res.append((char)(a1  + a2 + '0'));
            else res.append('0');

            // System.out.println(res.toString());
        }
        int i;
        for (i = res.length()-1; i > 0; --i) {
            if (res.charAt(i)!='0') break;
        }
        res.setLength(i + 1);
        return res.reverse().toString();
    }
}
```

#### 969. Pancake Sorting

```
/*
* 969. Pancake Sorting
* 翻烧饼排序
* https://leetcode.com/problems/pancake-sorting/
*
* */
```

```java
/*
思路，由于只能从开头翻转数组，考虑每次使得最大的值有序。

需要先将最大值翻转到0处，再将其翻转到n正确位置。
Runtime: 1 ms, faster than 100.00% of Java online submissions for Pancake Sorting.
Memory Usage: 39.1 MB, less than 63.48% of Java online submissions for Pancake Sorting.
* */
class com.leetcode.dp.q91.Solution {
    public List<Integer> pancakeSort(int[] arr) {
        int n,j, max;
        List<Integer> res = new ArrayList<>();
        n = arr.length;
        while (n-- > 0) {
            j = n;
            max = arr[j];
            for (int i = 0; i < n; i++) {
                if (arr[i] > max) {
                    j = i;
                    max = arr[j];
                }
            }
            flip(j, arr);
            res.add(j + 1);
            flip(n, arr);
            res.add(n + 1);
        }
        return res;
    }

    private void flip(int j, int[] arr) {
        int i = 0, t;
        while (i < j) {
            t = arr[i];
            arr[i++] = arr[j];
            arr[j--] = t;
        }
    }
}
```

#### 序列和

````java
/*
* 序列和
* https://www.nowcoder.com/practice/46eb436eb6564a62b9f972160e1699c9?tab=answerKey
* 
* */
public class SeqSum {
    public static void main(String[] args){
        int N, L;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        N -= (L-1)*(L-2)/2;
        for (; N > 0 || L <= 100; ++L){
            N -= L-1;
            if((N % L )== 0)break;
        }

        N /= L;
        if(N < 0 || L > 100) System.out.println("No");
        else{
            System.out.print(N);
            for (int i = 1; i < L; ++i) { System.out.print(" " + (N + i)); }

            System.out.println();
        }
    }

}
````

#### 5. Longest Palindromic Substring

```java
/*
* 5. Longest Palindromic Substring
* 最长回文字串
* https://leetcode.com/problems/longest-palindromic-substring/
*
* */
class com.leetcode.dp.q91.Solution {
    public String longestPalindrome(String s) {
        String r, s1, s2;
        int n = s.length();
        r = "";
        for (int i = 0; i < n; ++i){
            s1 = paliStr(i, i, s);
            s2 = paliStr(i, i+1, s);
            if(r.length() < s1.length()) r = s1;
            if(r.length() < s2.length()) r = s2;
        }
        return r;
    }
    
    private String paliStr(int i, int j, String s){
        int n = s.length();
        while(i >= 0 && j < n && s.charAt(i) == s.charAt(j)){--i; ++j;}
        return s.substring(i+1, j);
    }
```
#### 计算器题目

没有负数的情况，可以用逆波兰表达式，这个非常简洁。每次对比op栈顶和当前操作符的优先级。根据优先级来决定运算顺序。

可以用一个`prio[256][256]`来标记操作符，这个的好处很多，你可以任意定制两个操作符的顺序。

模板
```java
    private int postCal(String s, char[][] prio) {
        int i = 0, n = s.length(), t, a, b;
        char o;
        Stack<Character> op = new Stack();
        Stack<Integer> num = new Stack<>();
        op.push(s.charAt(i++));
        while (i < n) {
            o = s.charAt(i);
            if (o == ' ') {
                ++i;
                continue;
            }
            if (Character.isDigit(o)) {
                t = 0;
                do {
                    t = t * 10 + (s.charAt(i) - '0');
                } while (Character.isDigit(s.charAt(++i)));
                num.push(t);
                continue;
            }
            if (prio[op.peek()][o] == '=') {
                op.pop();
                ++i;
            } else if (prio[op.peek()][o] == '>') {
                b = num.pop();
                a = num.pop();
                num.push(cal(a, b, op.pop()));
            } else {
                op.push(o);
                ++i;
            }
        }

```

#### 227. Basic Calculator II

```
/*
* 227. Basic Calculator II
* 计算器
* https://leetcode.com/problems/basic-calculator-ii/
*
* */
```

模板可以直接解决的问题

```java
/*
* 227. Basic Calculator II
* 计算器
* https://leetcode.com/problems/basic-calculator-ii/
*
* */
class com.leetcode.dp.q91.Solution {
    public int calculate(String s) {
        String tmp[] = {
                "=<<<<",
                ">>><<",
                ">>><<",
                ">>>>>",
                ">>>>>"
        };
        String ops = "#+-*/";
        char prio[][] = new char[256][256];
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                prio[ops.charAt(i)][ops.charAt(j)] = tmp[i].charAt(j);
            }
        }
        return postCal("#" + s + "#", prio);
    }

    private int postCal(String s, char[][] prio) {
        int i = 0, n = s.length(), t, a, b;
        char o;
        Stack<Character> op = new Stack();
        Stack<Integer> num = new Stack<>();
        op.push(s.charAt(i++));
        while (i < n) {
            o = s.charAt(i);
            if (o == ' ') {
                ++i;
                continue;
            }
            if (Character.isDigit(o)) {
                t = 0;
                do {
                    t = t * 10 + (s.charAt(i) - '0');
                } while (Character.isDigit(s.charAt(++i)));
                num.push(t);
                continue;
            }
            if (prio[op.peek()][o] == '=') {
                op.pop();
                ++i;
            } else if (prio[op.peek()][o] == '>') {
                b = num.pop();
                a = num.pop();
                num.push(cal(a, b, op.pop()));
            } else {
                op.push(o);
                ++i;
            }
        }
        return num.empty() ? 0 : num.peek();
    }

    private int cal(int a, int b, char o) {
        System.out.println(a + " " + o + " " + b);
        switch (o) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return -1;
    }

}
```

#### 849. 基础计算器 III

```
/*
* 849. 基础计算器 III
* https://www.lintcode.com/problem/basic-calculator-iii
*
*
* */
```

```java
class com.leetcode.dp.q91.Solution {
    public int calculate(String s) {
        String tmp[] = {
                "=<<<<<<",
                ">>><<<>",
                ">>><<<>",
                ">>>>><>",
                ">>>>><>",
                "<<<<<<=",
                "#######"
        };
        String ops = "#+-*/()";
        char prio[][] = new char[256][256];
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 7; ++j) {
                prio[ops.charAt(i)][ops.charAt(j)] = tmp[i].charAt(j);
            }
        }
        return postCal("#" + s + "#", prio);
    }

    private int postCal(String s, char[][] prio) {
        int i = 0, n = s.length(), t, a, b;
        char o;
        Stack<Character> op = new Stack();
        Stack<Integer> num = new Stack<>();
        op.push(s.charAt(i++));
        while (i < n) {
            o = s.charAt(i);
            if (o == ' ') {
                ++i;
                continue;
            }
            if (Character.isDigit(o)) {
                t = 0;
                do {
                    t = t * 10 + (s.charAt(i) - '0');
                } while (Character.isDigit(s.charAt(++i)));
                num.push(t);
                continue;
            }
            if (prio[op.peek()][o] == '=') {
                op.pop();
                ++i;
            } else if (prio[op.peek()][o] == '>') {
                b = num.pop();
                a = num.pop();
                num.push(cal(a, b, op.pop()));
            } else {
                op.push(o);
                ++i;
            }
        }
        return num.empty() ? 0 : num.peek();
    }

    private int cal(int a, int b, char o) {
        switch (o) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return -1;
    }

    public static void main(String[] args) {
        new com.leetcode.dp.q91.Solution().calculate("2*(5+5*2)/3+(6/2+8)");
    }
}
```

#### 224. Basic Calculator

```
/*
 * 224. Basic Calculator
 * 基础计算器
 * https://leetcode.com/problems/basic-calculator/
 * */
```

终于对了…………核心思路是。这道题用其他思路非常难写，卡了我一天。
- 边遍历边求和，负号则改变sign符号标志，读取随后的完整数字
- 遇到(，则先保存当前的res，和括号前的符号然后进入（）内计算括号内的子问题和，

```java
public class SolutionV4 {
    public int calculate(String s) {
        int n = s.length(), res = 0, sign = 1, tmp;
        char o;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            o = s.charAt(i);
            if (o == ' ') continue;
            else if (Character.isDigit(o)) {
                tmp = 0;
                while (i < n && Character.isDigit(o = s.charAt(i))) {
                    tmp = tmp * 10 + (o - '0');
                    ++i;
                }
                i--;
                res += tmp * sign;
            } else if (o == '+') sign = 1;
            else if (o == '-') sign = -1;
            else if (o == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (o == ')') {
                res = res * stack.pop() + stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SolutionV4().calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
```

#### 42. Trapping Rain Water

```java
/*
* 42. Trapping Rain Water
* 接雨水
* https://leetcode.com/problems/trapping-rain-water/
*
* */
/*
这个题目可以很容易想到二次方复杂度的解法，横向遍历。直觉上，应该能够在O(N)复杂度解决。

用空间换时间，只要知道每一个点是否后面有比它高的元素即可。求覆盖数组。
* */
class com.leetcode.dp.q91.Solution {
    public int trap(int[] height) {
        int l = 0, r,t, sum = 0, n = height.length, max=0;
        int c[] = new int[n];
        for(int i = n-1; i >=0; --i) {
            if(height[i] <= max) c[i] = max;
            else {
                c[i] = max;
                max = height[i];
            }
            //System.out.print(c[i] + " ");            
        }

        while(l < n){
            r = l + 1;
            if (c[l]!=0){
                t =Math.min(height[l], c[l]);
                while(r < n && t > height[r]){
                    sum -= height[r++];
                }
                sum += t * (r - l -1);
                //System.out.println(l + " " + r + " " + sum);
            }
            //System.out.print(sum + " ");
            l = r;
        }
        return sum;
    }
}
```

#### 43. Multiply Strings

```java
/*
 * 43. Multiply Strings
 * 模拟乘法
 * https://leetcode.com/problems/multiply-strings/
 * */

/*
反向计算是比较好的实现。

应该使用int数组来模拟比较方便点，先申请足够的位置，就不必考虑add增加位置的问题。
Runtime: 7 ms, faster than 38.57% of Java online submissions for Multiply Strings.
Memory Usage: 38.6 MB, less than 95.77% of Java online submissions for Multiply Strings.
* */
class com.leetcode.dp.q91.Solution {
    public String multiply(String num1, String num2) {
        int n, m, a1, a2, t;
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder res = new StringBuilder();
        n = num1.length();
        m = num2.length();
        for (int i = 0; i < m; i++) res.append(0);
        for (int i = 0; i < n; i++) {
            a1 = a2 = 0;
            for (int j = 0; j < m; j++) {
                t = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + a1;
                a1 = t / 10;
                t = t % 10 + a2 + (res.charAt(i + j) - '0');
                res.setCharAt(i + j, (char) ((t % 10) + '0'));
                a2 = t / 10;
            }
            if (a1 != 0 || a2 != 0) res.append((char)(a1  + a2 + '0'));
            else res.append('0');

            // System.out.println(res.toString());
        }
        int i;
        for (i = res.length()-1; i > 0; --i) {
            if (res.charAt(i)!='0') break;
        }
        res.setLength(i + 1);
        return res.reverse().toString();
    }
}
```

#### 110. Balanced Binary Tree

```java
/*
* 110. Balanced Binary Tree
* 判断一棵树是否是二叉树
* https://leetcode.com/problems/balanced-binary-tree/
*
* */
/*
Runtime: 0 ms, faster than 100.00% of Java online submissions for Balanced Binary Tree.
Memory Usage: 39.1 MB, less than 62.27% of Java online submissions for Balanced Binary Tree.
* */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class com.leetcode.dp.q91.Solution {
    public boolean isBalanced(TreeNode root) {
        return trav(root) >= 0;
    }
    
    private int trav(TreeNode root){
        if(root == null) return 0;
        
        int left = trav(root.left);
        int right = trav(root.right);
        //System.out.println(root.val + " " + left + " " + right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        
        return Math.max(left,  right) + 1;
    }
}
```

#### 391. Perfect Rectangle

```java
/*
* 391. Perfect Rectangle
* 完美覆盖
* https://leetcode.com/problems/perfect-rectangle/
*
* */

/*
O(N)的解法，顶点数为4，内部顶点，完美覆盖会自动消掉
覆盖问题，先检测面积，再检测碰撞
Runtime: 35 ms, faster than 84.33% of Java online submissions for Perfect Rectangle.
Memory Usage: 48.9 MB, less than 26.73% of Java online submissions for Perfect Rectangle.
* */
class SolutionV2 {
    public boolean isRectangleCover(int[][] rectangles) {
        int sum, x1, x2, y1, y2, n;
        int [] a;
        Set<String> corner = new HashSet<>();
        String cur;

        sum = x2 = y2 = 0;
        x1 = y1 = Integer.MAX_VALUE;
        n = rectangles.length;

        for (int i = 0; i < n; ++i) {
            a = rectangles[i];
            sum += (a[2] - a[0]) * (a[3] - a[1]);
            x1 = min(x1, a[0]);
            x2 = max(x2, a[2]);
            y1 = min(y1, a[1]);
            y2 = max(y2, a[3]);
            for (int j = 0; j < 4; j+=2) {
                for (int k = 1; k < 4; k+=2) {
                    if (corner.contains(cur = (a[j] + " " + a[k]))) corner.remove(cur);
                    else corner.add(cur);
                }
            }
        }
        if (corner.size()==4 && corner.contains(x1 + " " + y1) && corner.contains(x1 + " " + y2)
            && corner.contains(x2 + " " + y1) && corner.contains(x2 + " " + y2) && sum == (x2 - x1) * (y2 - y1))
            return true;

        return false;
    }
}
```

#### 392. Is Subsequence

```java
/*
* 392. Is Subsequence
* 子序列？
* https://leetcode.com/problems/is-subsequence/
*
* */
class com.leetcode.dp.q91.Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        char a, b;
        while (i < s.length() && j < t.length()){
            if (s.charAt(i)==t.charAt(j)) {++i; ++j;}
            else ++j;
        }
        return i == s.length();
    }
}
```

#### 855. Exam Room

```java
/*
 * 855. Exam Room
 * 座位调度
 * https://leetcode.com/problems/exam-room/
 *
 *
 * */

/*
朴素的想法，需要对线段排序，每次取中值距离最短的
这里不能用堆（priority）的原因：
堆的删除需要去先定位元素，O(N)。平衡二叉树set的删除是O(logN)
TreeSet不能add null的原因
TreeSet的底层是TreeMap，是红黑树。
需要比较才能插入，如果没有传入对比器，默认抛出空指针异常，如果传入对比器，对比器需要处理空这种情况（这不是红黑树的责任）。

算法复杂度，seat为平衡树插入，O(logN)，删除为平衡树删除O(logN）
Runtime: 12 ms, faster than 86.07% of Java online submissions for Exam Room.
        Memory Usage: 39.4 MB, less than 84.25% of Java online submissions for Exam Room.
*/

class ExamRoom {
    private TreeSet<int[]> treeSet;
    private Map<Integer, int[]> start, end;
    private int N;

    public ExamRoom(int N) {
        treeSet = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                int d = dist(a) - dist(b);
                return d == 0 ? b[0] - a[0] : d;
            }
        });
        start = new HashMap<>();
        end = new HashMap<>();
        this.N = N;
        addIntv(-1, N);

    }

    private int dist(int[] intv) {
        if (intv[0] == -1) return intv[1];
        if (intv[1] == N) return intv[1] - intv[0] - 1;
        return (intv[1] - intv[0]) / 2;
    }

    private void addIntv(int i, int j) {

        int intv[] = {i, j};
        treeSet.add(intv);
        start.put(i, intv);
        end.put(j, intv);

    }

    public int seat() {
        int intv[];
        int i, j, seat;
        intv = treeSet.last();
        i = intv[0];
        j = intv[1];
        if (i == -1) seat = 0;
        else if (j == N) {
            seat = N - 1;
        } else {
            seat = (j - i) / 2 + i;// 防止越界
        }
        removeIntv(intv);
        addIntv(i, seat);
        addIntv(seat, j);
        return seat;
    }

    private void removeIntv(int[] intv) {
        treeSet.remove(intv);
        start.remove(intv[0]);
        end.remove(intv[1]);
    }

    public void leave(int p) {
        int[] left, right, intv;
        left = end.remove(p);
        right = start.remove(p);
        treeSet.remove(left);
        treeSet.remove(right);
        addIntv(left[0], right[1]);
    }

    public static void main(String[] args) {
        ExamRoom obj = new ExamRoom(10);
    }


}
```

#### 969. Pancake Sorting

```java
/*
* 969. Pancake Sorting
* 翻烧饼排序
* https://leetcode.com/problems/pancake-sorting/
*
* */

/*
思路，由于只能从开头翻转数组，考虑每次使得最大的值有序。

需要先将最大值翻转到0处，再将其翻转到n正确位置。
Runtime: 1 ms, faster than 100.00% of Java online submissions for Pancake Sorting.
Memory Usage: 39.1 MB, less than 63.48% of Java online submissions for Pancake Sorting.
* */
class com.leetcode.dp.q91.Solution {
    public List<Integer> pancakeSort(int[] arr) {
        int n,j, max;
        List<Integer> res = new ArrayList<>();
        n = arr.length;
        while (n-- > 0) {
            j = n;
            max = arr[j];
            for (int i = 0; i < n; i++) {
                if (arr[i] > max) {
                    j = i;
                    max = arr[j];
                }
            }
            flip(j, arr);
            res.add(j + 1);
            flip(n, arr);
            res.add(n + 1);
        }
        return res;
    }

    private void flip(int j, int[] arr) {
        int i = 0, t;
        while (i < j) {
            t = arr[i];
            arr[i++] = arr[j];
            arr[j--] = t;
        }
    }
}
```