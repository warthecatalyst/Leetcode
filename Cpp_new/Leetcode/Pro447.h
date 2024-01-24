//
// Created by smile on 2024/1/8.
//

#ifndef LEETCODE_PRO447_H
#define LEETCODE_PRO447_H
#include "global.h"

namespace p447{
class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int ans = 0;
        for (auto& p : points) {
            unordered_map<int, int> cnt;
            for(auto &q : points) {
                int dis = (p[0]-q[0]) * (p[0]-q[0]) + (p[1]-q[1]) * (p[1]-q[1]);
                ++cnt[dis];
            }
            for(const auto& it: cnt) {
                ans += (it.second-1)*it.second / 2;
            }
        }
        return ans;
    }
};
}


#endif //LEETCODE_PRO447_H
