//
// Created by 18317 on 2023/4/13.
//

#ifndef LEETCODE_TENCENTMUSIC_H
#define LEETCODE_TENCENTMUSIC_H

#include "global.h"

namespace TencentMusic {

class TencentMusic {
public:
    int32_t trans(int32_t value) {
        // 大端字节序 转 小端字节序
        //首先转换为16进制数
        char* buffer = (char *)&(value);
        char* new_buffer = new char[4];
        new_buffer[0] = buffer[3];
        new_buffer[1] = buffer[2];
        new_buffer[2] = buffer[1];
        new_buffer[3] = buffer[0];
        return *(int32_t*)new_buffer;
    }

    void test1(){
        auto ans = trans(278136412);
        cout << ans << endl;
        ans = trans(134217728);
        cout << ans << endl;
        ans = trans(8);
        cout << ans << endl;
    }

    void print_buffer(char buffer[14]) {
        int32_t num1;
        int16_t num2;
        double num3;
        void* temp1 = buffer;
        void* temp2 = buffer+4;
        void* temp3 = buffer+6;
        num1 = *(int32_t*)temp1;
        cout << "num1 = " << num1 << endl;
        num2 = *(int16_t*)temp2;
        cout << "num2 = " << num2 << endl;
        num3 = *(double*)temp3;
        cout << "num3 = " << num3 << endl;
    }

    void test2(){
        cout << "start_test2----" << endl;
        char testbuf[14] = {'\xD5', '\xAF', '\x34', '\x01', '\x39', '\x30', '\x5D', '\x1D', '\x5B', '\x2A', '\xCA', '\xC0', '\xF3', '\x3F'};
        print_buffer(testbuf);
    }

    //双指针
    void merge(int* a, int sizeA, int* b, int sizeB, int* output) {
        int i = 0,j = 0;
        while(i<sizeA&&j<sizeB){
            if(a[i]<=b[j]){
                output[i+j] = a[i];
                i++;
            }else{
                output[i+j] = b[j];
                j++;
            }
        }
        while(i<sizeA){
            output[i+j] = a[i];
            i++;
        }
        while(j<sizeB){
            output[i+j] = b[j];
            j++;
        }
    }

    void test3(){
        cout << "test3-----------" << endl;
        auto a = new int[]{1,3,6,8,10};
        auto b = new int[]{2,4,5,7,9};
        auto output = new int[10];
        merge(a,5,b,5,output);
        for(int i = 0;i<10;i++){
            cout << output[i] << " , ";
        }
        cout << endl;
    }

    vector<string> allSubsets;

    void printAllSubSets(){
        string chars = "ABCDEF";
        for(int i = 1;i<=6;i++){
            string ans;
            dfs(chars,0,i,ans);
        }
        for(const string& str:allSubsets){
            cout << str << endl;
        }
        cout << allSubsets.size() << endl;
    }

    void dfs(const string& chars,int index,const int number,string& curStr){
        if(curStr.size()==number){
            allSubsets.push_back(curStr);
            return;
        }
        else if(index >= chars.size()){
            return;
        }
        curStr.push_back(chars[index]);
        dfs(chars,index+1,number,curStr);
        curStr.pop_back();
        dfs(chars,index+1,number,curStr);
    }
};

} // TencentMusic

#endif //LEETCODE_TENCENTMUSIC_H
