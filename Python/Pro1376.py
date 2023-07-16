from typing import List


class Solution:
    def numOfMinutes(self, n: int, headID: int, manager: List[int], informTime: List[int]) -> int:
        graph = [[] for _ in range(n)]
        for index in range(len(manager)):
            if manager[index] == -1:
                continue
            else:
                man = manager[index]
                print(man)
                graph[man].append(index)
                print(graph)
        print(graph)
        queue = []
        maxTime = 0
        queue.append((headID, 0))
        while len(queue) > 0:
            cur = queue[0]
            if cur[1] > maxTime:
                maxTime = cur[1]
            queue = queue[1:]
            for neighbour in graph[cur[0]]:
                queue.append((neighbour,cur[1]+informTime[cur[0]]))
        return maxTime


if __name__ == '__main__':
    solution = Solution()
    solution.numOfMinutes(6, 2, [2, 2, -1, 2, 2, 2], [2, 2, -1, 2, 2, 2])
