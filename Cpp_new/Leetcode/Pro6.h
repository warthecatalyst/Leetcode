//
// Created by 18317 on 2023/4/13.
//

#ifndef LEETCODE_PRO6_H
#define LEETCODE_PRO6_H

#include "global.h"

namespace Pro6 {
class Solution {
public:
    string convert(string s, int numRows) {
        if(numRows==1){
            return s;
        }
        vector<string> transform(numRows,string(s.size(),' '));
        int i = 0,j = 0,k = 0;
        for(int idx = 0;idx<s.size();idx++){
            char c = s[idx];
            transform[i][j] = c;
            if(k==0){
                i = i+1;
                if(i==numRows-1){
                    k = 1;
                }
            }else{
                i = i-1;
                j = j+1;
                if(i==0){
                    k = 0;
                }
            }
        }
        string ans;
        for(const auto& line:transform){
            for(const char& c:line){
                if(c!=' '){
                    ans += c;
                }
            }
        }
        return ans;
    }
};

} // Pro6

#endif //LEETCODE_PRO6_H
