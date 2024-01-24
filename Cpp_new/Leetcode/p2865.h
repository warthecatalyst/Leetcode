//
// Created by smile on 2024/1/24.
//

#ifndef LEETCODE_P2865_H
#define LEETCODE_P2865_H
#include "global.h"

namespace p2865 {
class Solution {
public:
    long long maximumSumOfHeights(vector<int>& maxHeights) {
        int n = maxHeights.size();
        long long res = 0;
        vector<long long> prefix(n), suffix(n);
        vector<int> stack_front, stack_back;
        for (int i = 0; i < n; i++) {
            while(!stack_front.empty() && maxHeights[stack_front.back()] > maxHeights[i]) {
                stack_front.pop_back();
            }
            if (stack_front.empty()) {
                prefix[i] = (long long)(i + 1) * maxHeights[i];
            } else {
                prefix[i] = prefix[stack_front.back()] + (long long)(i - stack_front.back()) * maxHeights[i];
            }
            stack_front.push_back(i);
        }
        for (int i = n - 1; i >= 0; i--) {
            while(!stack_back.empty() && maxHeights[stack_back.back()] > maxHeights[i]) {
                stack_back.pop_back();
            }
            if (stack_back.empty()) {
                suffix[i] = (long long) (n - i) * maxHeights[i];
            } else {
                suffix[i] = suffix[stack_back.back()] + (long long)(stack_back.back() - i) * maxHeights[i];
            }
            stack_back.push_back(i);
            res = max(res, prefix[i] + suffix[i] - maxHeights[i]);
        }
        return res;
    }
};
}

#endif //LEETCODE_P2865_H
