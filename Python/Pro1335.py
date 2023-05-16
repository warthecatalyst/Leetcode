from typing import List


class Solution:
    def minDifficulty(self, jobDifficulty: List[int], d: int) -> int:
        n = len(jobDifficulty)
        if n < d:
            return -1
        dp = [0] * n
        j = 0
        for i in range(0, n):
            j = max(j, jobDifficulty[i])
            dp[i] = j
        for i in range(1, d):
            ndp = [0x3f3f3f3f] * n
            for j in range(i, n):
                ma = 0
                for k in range(j, i - 1, -1):
                    ma = max(ma, jobDifficulty[k])
                    ndp[j] = min(ndp[j], ma + dp[k - 1])
            ndp, dp = dp, ndp
        return dp[n-1]