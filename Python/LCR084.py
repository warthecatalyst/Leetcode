from typing import List


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        def backTrack(x):
            if x == len(nums) - 1:
                res.append(list(nums))  # 添加排列方案
                return
            dic = set()
            for i in range(x, len(nums)):
                if nums[i] in dic:
                    continue  # 重复，因此剪枝
                dic.add(nums[i])
                nums[i], nums[x] = nums[x], nums[i]  # 交换，将 nums[i] 固定在第 x 位
                backTrack(x + 1)  # 开启固定第 x + 1 位元素
                nums[i], nums[x] = nums[x], nums[i]  # 恢复交换

        res = []
        backTrack(0)
        return res
