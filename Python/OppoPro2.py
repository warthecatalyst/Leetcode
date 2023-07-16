from typing import List

if __name__ == '__main__':
    n = int(input())
    graph = [[] for _ in range(n)]
    for i in range(n - 1):
        line = input()
        fr, to = int(line.split(" ")[0]) - 1, int(line.split(" ")[1]) - 1
        graph[fr].append(to)
        graph[to].append(fr)
    root = int(input()) - 1
    visited = [False] * n
    visited[root] = True
    res = len(graph[root])
    lengths = []
    for start in graph[root]:
        # bfs遍历获取
        cnt = 0
        queue = [start]
        visited[start] = True
        while len(queue) > 0:
            cur = queue[0]
            cnt += 1
            queue = queue[1:]
            for neigh in graph[cur]:
                if not visited[neigh]:
                    queue.append(neigh)
                    visited[neigh] = True
        lengths.append(cnt)
    print(res)
    lengths.sort()
    for i in lengths:
        print(i, end=' ')
