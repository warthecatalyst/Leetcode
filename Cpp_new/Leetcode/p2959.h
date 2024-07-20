//
// Created by Admin on 2024/7/17.
//

#ifndef LEETCODE_P2959_H
#define LEETCODE_P2959_H
#include "global.h"

namespace P2959 {
class Solution {
public:
    int numberOfSets(int n, int maxDistance, vector<vector<int>>& roads) {
        int res = 0;
        vector<int> opened(n, 0);
        vector<vector<int>> d(n, vector<int>(n, 0x3f3f3f3f));

        for (int mask = 0; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                opened[i] = mask & (1 << i);
            }
            fill(d.begin(), d.end(), vector<int>(n, 0x3f3f3f3f));
            for (const auto& road: roads) {
                int i = road[0], j = road[1], w = road[2];
                if (opened[i] > 0 && opened[j] > 0) {
                    d[i][j] = d[j][i] = min(d[i][j], w);
                }
            }

            // floyd算法
            for (int k = 0; k < n; ++k) {
                if (opened[k] > 0) {
                    for (int i = 0; i < n; ++i) {
                        if (opened[i] > 0) {
                            for (int j = i + 1; j < n; ++j) {
                                if (opened[j] > 0) {
                                    d[i][j] = d[j][i] = min(d[i][j], d[i][k] + d[k][j]);
                                }
                            }
                        }
                    }
                }
            }

            // Validate
            int good = 1;
            for (int i = 0; i < n; ++i) {
                if (opened[i] > 0) {
                    for (int j = i + 1; j < n; ++j) {
                        if (opened[j] > 0 && d[i][j] > maxDistance) {
                            good = 0;
                            break;
                        }
                    }
                    if (!good) {
                        break;
                    }
                }
            }
            res += good;
        }
        return res;
    }
};
}


#endif //LEETCODE_P2959_H
