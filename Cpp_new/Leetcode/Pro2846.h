//
// Created by smile on 2024/1/26.
//

#ifndef LEETCODE_PRO2846_H
#define LEETCODE_PRO2846_H
#include "global.h"

namespace p2846 {
class Solution {
    static constexpr int W = 26;
    int find(vector<int>& uf, int i) {
        if (uf[i] == i) {
            return i;
        }
        uf[i] = find(uf, uf[i]);
        return uf[i];
    }
public:
    vector<int> minOperationsQueries(int n, vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int m = queries.size();
        vector<unordered_map<int, int>> neighbours(n);
        for(auto& edge : edges) {
            neighbours[edge[0]][edge[1]] = edge[2];
            neighbours[edge[1]][edge[0]] = edge[2];
        }
        vector<vector<pair<int, int>>> queryArr(n);
        for (int i = 0; i < m; i++) {
            queryArr[queries[i][0]].push_back({queries[i][1], i});
            queryArr[queries[i][1]].push_back({queries[i][0], i});
        }
        vector<vector<int>> count(n, vector<int>(W+1));
        vector<int> visited(n), uf(n), lca(m);
        function<void(int, int)> tarjan = [&](int node, int parent) {
            if (parent != -1) {
                count[node] = count[parent];
                count[node][neighbours[node][parent]]++;
            }
            uf[node] = node;
            for (auto [neigh, _] : neighbours[node]) {
                if (neigh == parent) {
                    continue;
                }
                tarjan(neigh, node);
                uf[neigh] = node;
            }
            for (auto [node1, index] : queryArr[node]) {
                if (node != node1 && !visited[node1]) {
                    continue;
                }
                lca[index] = find(uf, node1);
            }
            visited[node] = 1;
        };
        tarjan(0, -1);
        vector<int> res(m);
        for (int i = 0; i < m; i++) {
            int totalCount = 0, maxCount = 0;
            for (int j = 1; j <= W; j++) {
                int t = count[queries[i][0]][j] + count[queries[i][1]][j] - 2 * count[lca[i]][j];
                maxCount = max(maxCount, t);
                totalCount += t;
            }
            res[i] = totalCount - maxCount;
        }
        return res;
    }
};
}


#endif //LEETCODE_PRO2846_H
