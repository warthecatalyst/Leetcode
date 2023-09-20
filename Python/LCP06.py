from typing import List

class Solution:
    def minCount(self, coins: List[int]) -> int:
        res = 0
        for coin in coins:
            if coin % 2 == 0:
                res += coin//2
            else:
                res += coin//2 + 1
        return res