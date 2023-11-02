//
// Created by 周鑫宜 on 2023/10/22.
//

#ifndef LEETCODE_PRO70_H
#define LEETCODE_PRO70_H
#include "global.h"

class Solution {
public:
    int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int step0 = 1, step1 = 1;
        for (int i = 2;i <= n;i++) {
            int step2 = step0 + step1;
            step0 = step1;
            step1 = step2;
        }
        return step1;
    }
};

#endif //LEETCODE_PRO70_H
