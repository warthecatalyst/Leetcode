from typing import List


class Solution:
    def __init__(self):
        self.vowelSet = ('a', 'e', 'i', 'o', 'u')

    def vowelStrings(self, words: List[str], queries: List[List[int]]) -> List[int]:
        n = len(words)
        presum = [0] * (n + 1)
        for i in range(0, n):
            word = words[i]
            if word[0] in self.vowelSet and word[-1] in self.vowelSet:
                presum[i + 1] = presum[i] + 1
            else:
                presum[i + 1] = presum[i]
        ans = []
        for query in queries:
            start, end = query[0], query[1]
            curRes = presum[end + 1] - presum[start]
            ans.append(curRes)
        return ans