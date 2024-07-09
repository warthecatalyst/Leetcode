//
// Created by Admin on 2024/7/8.
//

#ifndef LEETCODE_PRO724_H
#define LEETCODE_PRO724_H
#include "global.h"

class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        int sum = accumulate(nums.begin(), nums.end(), 0);
        int leftSum = 0, rightSum = sum - nums[0];
        for (int idx = 0; idx < nums.size() - 1; idx++) {
            if (leftSum == rightSum) {
                return idx;
            }
            leftSum += nums[idx];
            rightSum -= nums[idx + 1];
        }
        if (leftSum == rightSum) {
            return nums.size() - 1;
        }
        return -1;
    }
};

#endif //LEETCODE_PRO724_H
