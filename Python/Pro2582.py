class Solution:
    def passThePillow(self, n: int, time: int) -> int:
        time %= (n - 1) * 2
        if time < n:
            return time + 1
        else:
            return n * 2 - time - 1

if __name__ == '__main__':
    solution = Solution()
    print(solution.passThePillow(3, 2))
