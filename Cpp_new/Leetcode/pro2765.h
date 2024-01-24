//
// Created by smile on 2024/1/23.
//

#ifndef LEETCODE_PRO2765_H
#define LEETCODE_PRO2765_H
#include "global.h"

namespace p2765 {
class Solution {
public:
    int alternatingSubarray(vector<int>& nums) {
        int n = nums.size();
        int ans = -1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i+1] != nums[i] + 1) {
                continue;
            }
            int j = i+2;
            for (; j < n; j++) {
                if(nums[j] != nums[i] + (j-i) % 2) {
                    break;
                }
            }
            ans = max(ans, j - i);
        }
        return ans;
    }
};
}

#endif //LEETCODE_PRO2765_H
