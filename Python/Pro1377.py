from typing import List


class Solution:
    def frogPosition(self, n: int, edges: List[List[int]], t: int, target: int) -> float:
        graph = [[] for _ in range(n)]
        target -= 1
        for edge in edges:
            fro, to = edge[0] - 1, edge[1] - 1
            graph[fro].append(to)
            graph[to].append(fro)

        # print(graph)
        # bfs找一条到达target的路径
        seen = [False] * n

        def dfs(i, t):
            nxt = len(graph[i])
            if i >= 1:
                nxt -= 1
            if nxt == 0 or t == 0:
                return 1.0 if i == target else 0.0
            seen[i] = True
            for j in graph[i]:
                if not seen[j]:
                    p = dfs(j, t - 1)
                    if p > 0:
                        return p / nxt
            return 0.0

        return dfs(0, t)


if __name__ == '__main__':
    solution = Solution()
    solution.frogPosition(7, [[1, 2], [1, 3], [1, 7], [2, 4], [2, 6], [3, 5]], 2, 4)
