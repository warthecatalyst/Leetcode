//
// Created by 18317 on 2023/3/30.
//

#ifndef LEETCODE_PRO3_H
#define LEETCODE_PRO3_H
#include "global.h"
namespace p3{
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        if(s.size() == 0) return 0;
        int ans = 0;
        int left = 0;
        unordered_set<char> charSet;
        for(int i = 0;i<s.size();i++){
            while(charSet.find(s[i])!=charSet.end()){
                charSet.erase(s[left]);
                left++;
            }
            ans = max(ans,i-left+1);
            charSet.insert(s[i]);
        }
        return ans;
    }
};
}
#endif //LEETCODE_PRO3_H
