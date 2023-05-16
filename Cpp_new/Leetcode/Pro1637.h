//
// Created by 18317 on 2023/3/30.
//

#ifndef LEETCODE_PRO1637_H
#define LEETCODE_PRO1637_H
#include "global.h"

namespace p1637{
class Solution {
public:
    int maxWidthOfVerticalArea(vector<vector<int>>& points) {
        sort(points.begin(),points.end(),[](const vector<int>& a,const vector<int>& b){
            return a[0]<b[0];
        });
        int ans = INT32_MIN;
        for(auto i = 0;i<points.size()-1;i++){
            const auto& point = points[i],nextpoint = points[i+1];
            if(nextpoint[0]-point[0]>=ans){
                ans = nextpoint[0]-point[0];
            }
        }
        return ans;
    }
};
}

#endif //LEETCODE_PRO1637_H
