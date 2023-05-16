#include "global.h"
#include "DynamicProgramming.h"
#include "ByteDance.h"
#include "Tencent.h"
#include "TencentMusic.h"

int main() {
    string str = "11223344";
    do{
        vector<vector<int>> indexes(4);
        for(int i = 0;i<str.size();i++){
            char c = str[i];
            indexes[c-'1'].push_back(i);
        }
        if(indexes[3][1]-indexes[3][0]==5&&indexes[2][1]-indexes[2][0]==4
        &&indexes[1][1]-indexes[1][0]==3&&indexes[0][1]-indexes[0][0]==2){
            cout << str <<endl;
        }
    }
    while(std::next_permutation(str.begin(), str.end()));
}
