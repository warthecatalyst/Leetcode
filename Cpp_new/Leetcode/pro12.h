//
// Created by 18317 on 2023/4/24.
//

#ifndef LEETCODE_PRO12_H
#define LEETCODE_PRO12_H

#include "global.h"

namespace pro12 {

class Solution {
private:
    const map<int,string> intToRom{{1,"I"},{4,"IV"},{5,"V",},{9,"IX"},
                                   {10,"X"},{40,"XL"},{50,"L"},{90,"XC"},{100,"C"}
                                   ,{400,"CD"},{500,"D"},{900,"CM"},{1000,"M"}};

public:
    string intToRoman(int num) {
        string res;
        while(num>0){
            for(auto it=intToRom.rbegin();it!=intToRom.rend();it++){
                if(num>=it->first){
                    res += it->second;
                    num -= it->first;
                    break;
                }
            }
        }
        return res;
    }
};

} // pro12

#endif //LEETCODE_PRO12_H
