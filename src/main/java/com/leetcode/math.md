## Math

### 位运算技巧

#### 136. Single Number

```
/*
* 136. Single Number
* 查找单独的数
* https://leetcode.com/problems/single-number/
* */
```

```java



/*
抑或技巧，不同等于相加，相同等于相减
Runtime: 1 ms, faster than 96.29% of Java online submissions for Single Number.
Memory Usage: 47.5 MB, less than 11.27% of Java online submissions for Single Number.
* */
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }
}
```

#### 191. Number of 1 Bits

```
/*
* 191. Number of 1 Bits
* 1的个数，汉明重量
* https://leetcode.com/problems/number-of-1-bits/
*
* */
```

```java
/*
位运算技巧，n&(n-1)必定能消除最后的1，包括符号位
Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of 1 Bits.
Memory Usage: 35.7 MB, less than 83.47% of Java online submissions for Number of 1 Bits.
* */
public class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            ++res;
        }
        return res;
    }
}
```

#### 231. Power of Two

```
/*
* 231. Power of Two
* 2的次方数
* https://leetcode.com/problems/power-of-two/
*
*
* */
```

```java
/*
负数不是2的次方，只有一个1
Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Two.
        Memory Usage: 38.2 MB, less than 8.77% of Java online submissions for Power of Two.
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n-1)) == 0;
    }
}
```

### 阶乘

#### 172. Factorial Trailing Zeroes

```
/*
* 172. Factorial Trailing Zeroes
* 阶乘尾0的个数
* https://leetcode.com/problems/factorial-trailing-zeroes/
* */
```

得到一个0其实取决于因子2和5.下面是暴力解法。
事实上，2的因子总是多于5的因子，所以有多少对2和5取决于5的数量。

时间复杂度：

对于任意n，需要计算k次，5^k = n，因而k = log n，所以整体时间复杂度为O(N log N)

```java
/*

Runtime: 35 ms, faster than 5.10% of Java online submissions for Factorial Trailing Zeroes.
Memory Usage: 36.1 MB, less than 38.90% of Java online submissions for Factorial Trailing Zeroes.
* */

class Solution {
    public int trailingZeroes(int n) {
        int two, five, cur;
        two = five = 0;
        for (int i = 1; i <= n; i++) {
            cur = i;
            while (cur % 5 == 0) {
                cur /= 5;
                ++five;
            }
            while (cur % 2 == 0) {
                cur /= 2;
                ++two;
            }
        }
        return Math.min(two, five);
    }
}
```

```java
/*
以125为例

5，10，15，20... 5的倍数都能提供 1个5， 总共有125/5个

25,50,75,100,125... 25的倍数还能额外提供1个人5，总共125/25个

125，... 125的倍数还能额外提供一个5，总共125/125个

相加即可

算法复杂度分析：

取决于while循环的次数，k次，5^k = n，则k = log n，所以算法复杂度为O(log n)
Runtime: 0 ms, faster than 100.00% of Java online submissions for Factorial Trailing Zeroes.
Memory Usage: 35.7 MB, less than 80.01% of Java online submissions for Factorial Trailing Zeroes.
* */

class SolutionV2 {
    public int trailingZeroes(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }
}
```

#### 793. Preimage Size of Factorial Zeroes Function

```
/*
* 793. Preimage Size of Factorial Zeroes Function
* 阶乘尾0进阶
* https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/
*
* */
```

```java
/*
* 793. Preimage Size of Factorial Zeroes Function
* 阶乘尾0进阶
* https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/
*
* */
/*
超时
* */
class Solution {
    public int preimageSizeFZF(int K) {
        int res = 0, t;
        for (int i = 0; ; i++) {
            if ((t = trailingZeroes(i)) == K) ++res;
            else if (t>K) break;
        }
        return res;
    }

    public int trailingZeroes(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().preimageSizeFZF(80502705));
    }
}

/*
二分搜索，因为K和f(x)是单调不减函数，所以可以应用二分搜索
Runtime: 0 ms, faster than 100.00% of Java online submissions for Preimage Size of Factorial Zeroes Function.
        Memory Usage: 35.8 MB, less than 46.34% of Java online submissions for Preimage Size of Factorial Zeroes Function.
*/

class SolutionV2 {
    public int preimageSizeFZF(int K) {
        int l, r, mid;
        l = 0; r = 5 * K + 1;
        while (l < r) {
            mid = (l + r) >> 1;
            if (f(mid) < K) l = mid + 1;
            else r = mid;
        }
        return f(l) > K ? 0 : 5;
    }

    public int f(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }

}
```

### 素数

#### 204. Count Primes

```
/*
 * 204. Count Primes
 * 素数筛选法
 * https://leetcode.com/problems/count-primes/
 *
 * */
```

```java
/*
时间复杂度很难计算
* */
/*
素数筛选法效率版本
Runtime: 12 ms, faster than 88.99% of Java online submissions for Count Primes.
Memory Usage: 37.6 MB, less than 46.00% of Java online submissions for Count Primes.
*/

class SolutionV1 {
    public int countPrimes(int n) {
        int res = 0;
        boolean isPrime[] = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i])
                for (int j = i * i; j < n; j += i)isPrime[j] = false;
        }
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) ++res;
        }
        return res;
    }

    public static void main(String[] args) {
        new SolutionV1().countPrimes(10);
    }
}
```