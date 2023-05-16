//
// Created by 18317 on 2023/3/29.
//

#ifndef LEETCODE_TENCENT_H
#define LEETCODE_TENCENT_H
#include "global.h"

class Tencent {
public:
    struct Problem1{
        void* my_memcpy(const void* src,void* dest,size_t cnt){
            if(dest== nullptr||src== nullptr||cnt<0){
                return nullptr;
            }
            if(src==dest){
                return dest;
            }
            char* psrc = nullptr;
            char* pdest = nullptr;
            if(src<dest){
                //此时可能重叠，反向copy
                psrc = (char*)src+cnt-1;
                pdest = (char*)dest+cnt-1;
                while(cnt-->0){
                    *pdest-- = *psrc--;
                }
            }else{
                psrc = (char*)src;
                pdest = (char*)dest;
                while(cnt-->0){
                    *pdest++ = *psrc++;
                }
            }
            return dest;
        }
    };

    struct Problem2{
        int getMaxSubNum(const vector<int>& nums){
            int n = nums.size();
            int maxNum = INT32_MIN,minNum = INT32_MAX;
            for(const auto num:nums){
                maxNum = std::max(maxNum,num);
                minNum = std::min(minNum,num);
            }
            if(maxNum== minNum){
                return 0;
            }
            vector<pair<int,int>> buckets(n,{INT32_MIN,INT32_MAX});    //每个bucket存放最大最小值，划分为n个bucket
            int buck = ceil((double)(maxNum-minNum)/n);
            for(int num:nums){
                int st = (num-minNum)/buck; //划分到第几个桶
                buckets[st].first = max(buckets[st].first,num);
                buckets[st].second = min(buckets[st].second,num);
            }
            int largeBuck = n-1;
            int ans = -1;
            while(largeBuck>0){
                while(largeBuck>0&&buckets[largeBuck].second>buckets[largeBuck].first){
                    largeBuck--;
                }
                int smallBuck = largeBuck-1;
                while(smallBuck>0&&buckets[smallBuck].second>buckets[smallBuck].first){
                    smallBuck--;
                }
                ans = max(ans,buckets[largeBuck].second-buckets[smallBuck].first);
                largeBuck = smallBuck;
            }
            return max(ans,buck);
        }
    };

    struct Problem3{
        class Singleton{
        private:
            Singleton(){};
            ~Singleton(){};
            Singleton(const Singleton&);
            Singleton& operator=(const Singleton&);
        public:
            void print(){
                cout << this << endl;
            }
            static Singleton& getInstance(){
                static Singleton instance;
                return instance;
            }
        };
    };

    struct Problem4{
        //旋转链表K个位置
        struct ListNode{
            int val;
            ListNode* next;
            ListNode()=default;
            ListNode(int val):val(val),next(nullptr){}
            ListNode(int val,ListNode* next):val(val),next(next){}
        };

        ListNode* moveListRightK(ListNode* head,int k){
            vector<ListNode*> listNodes;
            ListNode* cur = head;
            while(cur!= nullptr){
                listNodes.push_back(cur);
                cur = cur->next;
            }
            k = k%listNodes.size();
            auto dummyHead = new ListNode(0);
            cur = dummyHead;
            auto start = (listNodes.size()-k)%listNodes.size();
            for(auto i = start;;i=(i+1)%listNodes.size()){
                if(i==start&&cur!=dummyHead){
                    break;
                }
                cur->next = listNodes[i];
                cur = cur->next;
            }
            return dummyHead->next;
        }
    };
};


#endif //LEETCODE_TENCENT_H
