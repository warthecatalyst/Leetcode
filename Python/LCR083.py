from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        ans = []

        def backTrack(first=0):
            if first == n:
                ans.append(nums[:])
            for i in range(first, n):
                nums[first], nums[i] = nums[i], nums[first]
                backTrack(first + 1)
                nums[first], nums[i] = nums[i], nums[first]

        backTrack()
        return ans
