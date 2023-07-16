from typing import List


class Solution:
    def averageValue(self, nums: List[int]) -> int:
        n = 0
        sum = 0
        for num in nums:
            if num % 6 == 0:
                num += 1
                sum += num
        return sum//n