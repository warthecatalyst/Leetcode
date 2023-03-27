//
// Created by 18317 on 2023/3/14.
//

#ifndef LEETCODE_PRO1605_H
#define LEETCODE_PRO1605_H
#include "global.h"

namespace p1605 {
class Solution {
public:
    vector<vector<int>> restoreMatrix(vector<int> &rowSum, vector<int> &colSum) {
        int n = rowSum.size(), m = colSum.size();
        vector<vector<int>> ans(n,vector<int>(m,0));
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                ans[i][j] = std::min(rowSum[i],colSum[j]);
                rowSum[i] -= ans[i][j];
                colSum[j] -= ans[i][j];
            }
        }
        return ans;
    }
};
}

#endif //LEETCODE_PRO1605_H
