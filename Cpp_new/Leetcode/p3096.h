//
// Created by Admin on 2024/7/19.
//

#ifndef LEETCODE_P3096_H
#define LEETCODE_P3096_H
#include "global.h"

namespace p3096 {
class Solution {
public:
    int minimumLevels(vector<int>& possible) {
        int n = possible.size();
        vector<int> preSum(n+1, 0);
        for (int i = 0; i < n; i++) {
            preSum[i+1] = preSum[i] + (possible[i] == 0 ? -1 : 1);
        }
        int allSum = preSum[n];
        for (int i = 1; i < n; i++) {
            int aliceSum = preSum[i];
            int bobSum = allSum - aliceSum;
            if (aliceSum > bobSum) {
                return i;
            }
        }
        return -1;
    }
};
}

#endif //LEETCODE_P3096_H
