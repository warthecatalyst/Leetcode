//
// Created by Admin on 2024/7/9.
//

#ifndef LEETCODE_PRO3102_H
#define LEETCODE_PRO3102_H
#include "global.h"

namespace P3102 {
class Solution {
public:
    int minimumDistance(vector<vector<int>>& points) {
        multiset<int> sx, sy;
        for (auto& p : points) {
            sx.emplace(p[0]-p[1]);
            sy.emplace(p[0]+p[1]);
        }
        auto res = INT32_MAX;
        for (auto &p : points) {
            sx.erase(sx.find(p[0] - p[1]));
            sy.erase(sy.find(p[0] + p[1]));
            res = min(res, max(*sx.rbegin() - *sx.begin(), *sy.rbegin() - *sy.begin()));
            sx.emplace(p[0] - p[1]);
            sy.emplace(p[0] + p[1]);
        }
        return res;
    }
};
}


#endif //LEETCODE_PRO3102_H
