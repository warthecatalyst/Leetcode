//
// Created by 18317 on 2023/2/13.
//

#ifndef LEETCODE_PRO1234_H
#define LEETCODE_PRO1234_H
#include "global.h"

namespace P1234{
class Solution {
    int idx(const char& c){
        return c-'A';
    }
public:
    int balancedString(string s) {
        vector<int> cnt(26);
        for(auto c:s){
            cnt[idx(c)]++;
        }
        int partial = s.size()/4;
        auto check = [&](){
            return !(cnt[idx('Q')]>partial||cnt[idx('W')]>partial||cnt[idx('E')]>partial||cnt[idx('R')]>partial);
        };
        if(check()){
            return 0;
        }
        int res = s.size();
        for(int l = 0,r = 0;l<s.size();l++){
            while(r<s.size()&&!check()){
                cnt[idx(s[r])]--;
                r++;
            }
            if (!check()){
                break;
            }
            res = min(res,r-l);
            cnt[idx(s[l])]++;
        }
        return res;
    }
};
}

#endif //LEETCODE_PRO1234_H
