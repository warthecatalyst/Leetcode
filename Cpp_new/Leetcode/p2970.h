//
// Created by Admin on 2024/7/10.
//

#ifndef LEETCODE_P2970_H
#define LEETCODE_P2970_H
#include "global.h"

namespace p2970 {
class Solution {
public:
    int incremovableSubarrayCount(vector<int>& nums) {
        int n = nums.size();
        int res = 0;
        int l = 1;
        while (l < n && nums[l-1] < nums[l]) {
            l++;
        }
        res += l + (l < n);
        for (int r = n - 2; r >= 0; r--) {
            while (l > 0 && nums[l - 1] >= nums[r + 1]) {
                l--;
            }
            res += l + (l <= r);
            if (nums[r] >= nums[r + 1]) {
                break;
            }
        }
        return res;
    }
};
}

#endif //LEETCODE_P2970_H
