# 可以引⼊的库和版本相关请参考 “环境说明”
# 请通过输出方法打印代码运行结果，“运行”后在控制台查看输出信息
# def main():入口函数请勿修改，以防执行失败
from typing import List

# Please refer to the "Environmental Notes" for the libraries and versions that can be introduced.
# Please print the results of your code using the output method and check the output on the console after "run".
# Don't modify the def main() entry function as it may fail to execute.
import pandas as pd
import random
def create_maze():
    edges = [[] for _ in range(20)]
    cnt = 0
    while cnt < 200:
        fr = random.randint(0, 19)
        to = random.randint(0, 19)
        if fr == to or edges[to].count(fr):
            continue
        edges[fr].append(to)
        cnt+=1
    return edges

def isDag(edges):
    def dfs(node, visited: set, stack: List[int]):
        visited.add(node)
        stack.append(node)

        for neigh in edges[node]:
            if neigh in visited:
                return False
            if neigh not in stack:
                if not dfs(neigh, visited, stack):
                    return False
        visited.remove(node)
        stack.pop()
        return True

    visited = set()
    stack = []
    for node in range(20):
        if node not in visited:
            if not dfs(node, visited, stack):
                return False
    return True

def main():
    maze = create_maze()
    while not isDag(maze):
        maze = create_maze()


if __name__ == "__main__":
    main()
