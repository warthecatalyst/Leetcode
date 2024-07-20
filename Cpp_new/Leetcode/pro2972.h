//
// Created by Admin on 2024/7/11.
//

#ifndef LEETCODE_PRO2972_H
#define LEETCODE_PRO2972_H
#include "global.h"

namespace p2972 {
class Solution {
public:
    long long incremovableSubarrayCount(vector<int>& nums) {
        long long ans = 0;
        int len = nums.size();
        int l = 0;
        while(l < len - 1) {
            if (nums[l] >= nums[l + 1]) {
                break;
            }
            l++;
        }
        if (l == len - 1) {
            return (long long)len * (len + 1) / 2;
        }
        ans += l + 2;
        for (int r = len - 1; r > 0; r--) {
            if (r < len - 1 && nums[r] >= nums[r + 1]) {
                break;
            }
            while (l >= 0 && nums[l] >= nums[r]) {
                l--;
            }
            ans += l + 2;
        }
        return ans;
    }
};
}

#endif //LEETCODE_PRO2972_H
