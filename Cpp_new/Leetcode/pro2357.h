//
// Created by 18317 on 2023/3/31.
//

#ifndef LEETCODE_PRO2357_H
#define LEETCODE_PRO2357_H
#include "global.h"

namespace p2357{
class Solution {
public:
    int arithmeticTriplets(vector<int>& nums, int diff) {
        int n = nums.size();
        int ans = 0;
        for(int i = 0;i<n;i++){
            int numi = nums[i];
            for(int j = i+1;j<n;j++){
                if(nums[j]-numi==diff){
                    for(int k = j+1;k<n;k++){
                        if(nums[k]-nums[j]==diff){
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }
};
}

#endif //LEETCODE_PRO2357_H
