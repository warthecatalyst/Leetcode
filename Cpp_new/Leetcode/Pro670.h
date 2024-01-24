//
// Created by smile on 2024/1/22.
//

#ifndef LEETCODE_PRO670_H
#define LEETCODE_PRO670_H
#include "global.h"

namespace p670 {
class Solution {
public:
    int maximumSwap(int num) {
        vector<int> values;
        int tmp = num;
        while(tmp > 0) {
            values.push_back(tmp % 10);
            tmp /= 10;
        }
        reverse(values.begin(), values.end());
        int n = values.size();
        //把最大的交换到越前面越好
        for(int i = 0; i < n - 1; i++) {
            int idx = i + 1;
            int tmp = values[i + 1];
            for (int j = i + 2; j < n; j++) {
                if (values[j] >= tmp) {
                    tmp = values[j];
                    idx = j;
                }
            }
            if (tmp > values[i]) {
                std::swap(values[i], values[idx]);
                break;
            }
        }
        int ans = 0;
        for(int val : values) {
            ans = ans * 10 + val;
        }
        return ans;
    }
};
}


#endif //LEETCODE_PRO670_H
