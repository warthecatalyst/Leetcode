package Pro210;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    //邻接链表作为边
    List<Integer>[] edges;
    //存储入度
    int[] inDegree;
    //存储答案
    int[] result;
    //存储下标
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new List[numCourses];
        inDegree = new int[numCourses];
        result = new int[numCourses];
        index = 0;
        for (int[] info : prerequisites) {
            edges[info[1]].add(info[0]);
            ++inDegree[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        // 将所有入度为 0 的节点放入队列中
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 从队首取出一个节点
            int u = queue.poll();
            // 放入答案中
            result[index++] = u;
            for (int v: edges[u]) {
                --inDegree[v];
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (index != numCourses) {
            return new int[0];
        }
        return result;
    }
}
