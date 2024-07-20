//
// Created by Admin on 2024/7/18.
//

#ifndef LEETCODE_P3112_H
#define LEETCODE_P3112_H
#include "global.h"

namespace p3112 {
class Solution {
public:
    struct ComparePair {
        bool operator()(const std::pair<int, int>& lhs, const std::pair<int, int>& rhs) const {
            return lhs.second > rhs.second; // 选择较小的second元素作为优先级高的
        }
    };

    vector<int> minimumTime(int n, vector<vector<int>>& edges, vector<int>& disappear) {
        // 访问节点消失的最短时间
        vector<int> ans(n, -1);
        vector<vector<pair<int, int>>> adj(n);
        for (const auto& edge : edges) {
            int from = edge[0], to = edge[1], length = edge[2];
            adj[from].emplace_back(to, length);
            adj[to].emplace_back(from, length);
        }
        priority_queue<pair<int, int>, vector<pair<int, int>>, ComparePair> pq;
        pq.emplace(0, 0);
        ans[0] = 0;
        while(!pq.empty()) {
            auto [u, t] = pq.top();
            pq.pop();
            if (t != ans[u]) {
                continue;
            }
            for (const auto& [v, length] : adj[u]) {
                if (t + length < disappear[v] && (ans[v] == -1 || t + length < ans[v])) {
                    pq.emplace(v, t + length);
                    ans[v] = t + length;
                }
            }
        }
        return ans;
    }
};
}

#endif //LEETCODE_P3112_H
