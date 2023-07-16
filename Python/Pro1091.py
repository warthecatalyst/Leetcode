from typing import List


class Solution:
    def __init__(self):
        self.directions = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]
        self.m = 0
        self.n = 0

    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        self.m, self.n = len(grid), len(grid[0])
        visited = [[False] * self.n for _ in range(self.m)]
        visited[0][0] = True
        queue = []
        if grid[0][0] == 0:
            queue.append([0, 0, 1])
        while len(queue) > 0:
            cur = queue[0]
            # print(cur)
            queue = queue[1:]
            x, y = cur[0], cur[1]
            if x == self.m - 1 and y == self.n - 1:
                return cur[2]
            for k in range(0, 8):
                newx, newy = x + self.directions[k][0], y + self.directions[k][1]
                if self.isIn(newx, newy) and grid[newx][newy] == 0 and not visited[newx][newy]:
                    queue.append([newx, newy, cur[2] + 1])
                    visited[newx][newy] = True
        return -1

    def isIn(self, x, y):
        return 0 <= x < self.m and 0 <= y < self.n


if __name__ == '__main__':
    solution = Solution()
    solution.shortestPathBinaryMatrix([[0, 0, 0], [1, 1, 0], [1, 1, 0]])
