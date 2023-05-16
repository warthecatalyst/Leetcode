//
// Created by 18317 on 2023/4/24.
//

#ifndef LEETCODE_PRO1727_H
#define LEETCODE_PRO1727_H
#include "global.h"

namespace pro1727 {

class Solution {
public:
    int largestSubmatrix(vector<vector<int>>& matrix) {
        int h = matrix.size(), w = matrix[0].size();
        vector<vector<int>> up(h,vector<int>(w,0));
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] == 1) {
                    up[i][j] = (i == 0 ? 0 : up[i-1][j]) + 1;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < h; i++) {
            vector<int> buf;
            for (int j = 0; j < w; j++) {
                buf.push_back(up[i][j]);
            }
            sort(buf.begin(), buf.end());
            for (int j = 0; j < w; j++) {
                ret = max(ret, buf[j] * (w - j));
            }
        }
        return ret;
    }
};

} // pro1727

#endif //LEETCODE_PRO1727_H
