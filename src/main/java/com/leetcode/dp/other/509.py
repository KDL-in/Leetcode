class com.leetcode.dp.q91.Solution:
    def fib(self, N: int) -> int:
        return N

def fib(N):
    if N==0:return 0
    
    a = 0
    b = 1
    for i in range(N-1):
        a,b = b,a+b
    return b

def fib(N):
    a=[]
    a.append(0)
    a.append(1)
    for i in range(2,N+1):
        a.append(a[i-1]+a[i-2])
    return a[N]

fib(5)