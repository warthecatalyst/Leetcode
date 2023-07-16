from typing import List


class Solution:
    def __init__(self):
        self.map = {'2': "abc", '3': "def", '4': "ghi", '5': "jkl", '6': "mno", '7': "pqrs", '8': 'tuv', '9': "wxyz"}

    def letterCombinations(self, digits: str) -> List[str]:
        ans = []

        def dfs(i: int, currentStr: str):
            if i == len(digits):
                ans.append(currentStr)
                return
            string = self.map[digits[i]]
            for c in string:
                currentStr += c
                dfs(i + 1, currentStr)
                currentStr = currentStr[:-1]

        dfs(0, "")
        return ans
