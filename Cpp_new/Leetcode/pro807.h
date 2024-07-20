//
// Created by Admin on 2024/7/14.
//

#ifndef LEETCODE_PRO807_H
#define LEETCODE_PRO807_H
#include "global.h"

namespace p807 {
class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int ans = 0;
        int n = grid.size(), m = grid[0].size();    //n行m列
        vector<int> rowMax(n, INT32_MIN), colMax(m, INT32_MIN);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowMax[i] = max(rowMax[i], grid[i][j]);
                colMax[j] = max(colMax[j], grid[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += min(rowMax[i],colMax[j]) - grid[i][j];
            }
        }
        return ans;
    }
};
}

#endif //LEETCODE_PRO807_H
