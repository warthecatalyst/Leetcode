//
// Created by 18317 on 2023/4/24.
//

#ifndef LEETCODE_PRO28_H
#define LEETCODE_PRO28_H

#include "global.h"

namespace pro28 {

class Solution {
public:
    int strStr(string haystack, string needle) {
        for(int i = 0;i<haystack.size();i++){
            int k = 0;
            while(k<needle.size()&&haystack[i+k]==needle[k]){
                k++;
            }
            if(k==needle.size()){
                return i;
            }
        }
        return -1;
    }
};

} // pro28

#endif //LEETCODE_PRO28_H
