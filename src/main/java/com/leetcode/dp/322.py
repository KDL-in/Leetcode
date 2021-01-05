
# 动态规划算法框架
# # 初始化 base case
# dp[0][0][...] = base
# # 进行状态转移
# for 状态1 in 状态1的所有取值：
#     for 状态2 in 状态2的所有取值：
#         for ...
#             dp[状态1][状态2][...] = 求最值(选择1，选择2...)




# 暴力
# 1. base: n = 0(amount)，基本情况，目标amount为0时，结束
# 2. state: n(amount)，状态，当前目标值
# 3. chose: chose coin，策略，选择硬币，影响状态
# 4. dp(n) -> res(硬币数量)，dp定义，输入目标值，返回硬币数量
def dp(n):
    if n == 0: return 0
    if n < 0: return -1
    res = float('INF')
    for coin in coins:
        t = dp(n-coin)
        if t == -1: continue
        res = min(res, t+1)
    return res if res != float('INF') else -1


# memo
memo = {}
def dp(n):
    # memo
    if n in memo: return memo[n]
    # base
    if n == 0: return 0
    if n < 0: return -1
    # init res
    res = float('INF')
    # chose coin
    for coin in coins:
        t = dp(n-coin)
        # invalid
        if t == -1: continue
        res = min(res, t+1)
    memo[n] = res if res != float('INF') else -1
    return memo[n]

# memo 2
MAXN = int(1e4 + 5)
memo = [0] * MAXN
def dp(n):
    # memo
    if memo[n] != 0: return memo[n]
    # base
    if n == 0: return 0
    if n < 0: return -1
    # init res
    res = float('INF')
    # chose coin
    for coin in coins:
        t = dp(n-coin)
        # invalid
        if t == -1: continue
        res = min(res, t+1)
    memo[n] = res if res != float('INF') else -1
    return memo[n]

# iter

memo = [0]

for n in range(1, amount+1):
    memo.append(amount+1)
    for coin in coins:
        # 检查越界
        if n - coin >= 0:
            memo[n] = min(memo[n-coin]+1, memo[n])
return memo[n] if memo[n] != (amount+1) else -1


coins = [2]
amount = 11
dp(amount)

