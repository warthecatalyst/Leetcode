//
// Created by smile on 2024/1/18.
//

#ifndef LEETCODE_PRO2171_H
#define LEETCODE_PRO2171_H
#include "global.h"

namespace p2171 {
class Solution {
    using ll = long long;
public:
    long long minimumRemoval(vector<int>& beans) {
        int n = beans.size();
        std::sort(beans.begin(), beans.end());
        ll sum = 0;
        for (const auto& bean : beans) {
            sum += bean;
        }
        ll ans = sum;
        for (int i = 0; i < n; i++) {
            ll tmp = sum - (n-i) * (ll)beans[i];
            ans = min(ans, tmp);
        }
        return ans;
    }
};
}



#endif //LEETCODE_PRO2171_H
