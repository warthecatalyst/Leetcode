//
// Created by 18317 on 2023/4/24.
//

#ifndef LEETCODE_PRO11_H
#define LEETCODE_PRO11_H

#include "global.h"

namespace pro11 {

class Solution {
public:
    int maxArea(vector<int>& height) {
        int i = 0, j = height.size() - 1, res = 0;
        while(i < j) {
            int maxHeight = height[i] < height[j]? height[i] : height[j];
            res = max(res,maxHeight*(j-i));
            if(height[i]<height[j]){
                i++;
            }else{
                j--;
            }
        }
        return res;
    }
};

} // pro11

#endif //LEETCODE_PRO11_H
