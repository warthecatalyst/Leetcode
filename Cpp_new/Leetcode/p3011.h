//
// Created by Admin on 2024/7/13.
//

#ifndef LEETCODE_P3011_H
#define LEETCODE_P3011_H
#include "global.h"
#include <bitset>

namespace P3011 {
class Solution {
public:
    bool canSortArray(vector<int>& nums) {
        // 判断相邻的元素能否交换
        // 将相邻的元素分组
        int lastGroupCnt = 0, lastGroupMax = 0, curGroupMax = 0;
        for (int i = 0; i < nums.size(); i++) {
            int num = nums[i];
            int curCnt = std::bitset<32>(num).count();
            if (curCnt == lastGroupCnt) {
                curGroupMax = max(curGroupMax, num);
            } else {
                lastGroupCnt = curCnt;
                lastGroupMax = curGroupMax;
                curGroupMax = num;
            }
            if (num < lastGroupMax) {
                return false;
            }
        }
        return true;
    }
};
}

#endif //LEETCODE_P3011_H
