//
// Created by 18317 on 2023/3/12.
//

#ifndef LEETCODE_BYTEDANCE_H
#define LEETCODE_BYTEDANCE_H
#include "global.h"

class ByteDance {
public:
    struct AILab_first{
        //AILab一面，知道前序和后序，如何快速知道中序，不行，无法确定根节点的位置
        string getMidFromFrontAndReverse(string front,string reverse){
            return "";
        }

        // 知道前序和中序，求后序
        string getReverseFromFrontAndMid(const string& front, const string& mid){
            if(front.length()!=mid.length()){
                return "";
            }
            cout << "front = " << front << ", mid = " << mid << endl;
            if(front.length()<=1){
                return front;
            }
            char root = front[0];
            int idx = mid.find(root);
            string midLeftTree = mid.substr(0,idx);
            string midRightTree = mid.substr(idx+1,mid.length()-idx-1);
            string frontLeftTree = front.substr(1,idx);
            string frontRightTree = front.substr(1+idx,front.length()-idx-1);
            return getReverseFromFrontAndMid(frontLeftTree,midLeftTree)+ getReverseFromFrontAndMid(frontRightTree,midRightTree) + root;
        }

        // 知道后序和中序，求前序
        string getFrontFromMidAndReverse(const string& mid,const string& reverse){
            if(mid.length() != reverse.length()){
                return "";
            }
            cout << "mid = " << mid << ", reverse = " << reverse << endl;
            if(mid.length()<=1){
                return mid;
            }
            char root = reverse.back();
            int idx = mid.find(root);
            string midLeftTree = mid.substr(0,idx);
            string midRightTree = mid.substr(idx+1,mid.length()-idx-1);
            string reverseLeftTree = reverse.substr(0,idx);
            string reverseRightTree = reverse.substr(idx,reverse.length()-idx-1);
            return root + getFrontFromMidAndReverse(midLeftTree,reverseLeftTree)+ getFrontFromMidAndReverse(midRightTree,reverseRightTree);
        }

        int main(){
            string front = "ABCEDFGHI";
            string mid = "ECBFDAHGI";
            string reverse = getReverseFromFrontAndMid(front,mid);
            cout << reverse << endl;
            auto ans = getFrontFromMidAndReverse(mid,reverse);
            cout << ans << endl;
            cout << (front == ans) << endl;
            return 0;
        }
    };

    //带有TTL的LRUCache
    class LRUCache{
        static const int TTL = 5;  //假设一次操作消耗1点时间
        struct Node{
            int key,val,ttl;
            Node *next;
            Node *prev;
            Node():val(0),ttl(TTL),next(nullptr),prev(nullptr){};
            Node(int _key,int _val):key(_key),val(_val),ttl(TTL),next(nullptr),prev(nullptr){};
        };
    public:
        LRUCache(int cap):capacity(cap),size(0){
            head = new Node();
            tail = new Node();
            head->next = tail;
            tail->prev = head;
        }

        void set(int key,int value){
            if(!key2value.count(key)){
                Node* node = new Node(key,value);
                key2value[key] = node;
                addToHead(node);
                ++size;
                if (size > capacity) {
                    Node* removed = removeTail();
                    key2value.erase(removed->key);
                    // 防止内存泄漏
                    delete removed;
                    --size;
                }
            }else{
                Node *node = key2value[key];
                node->val = value;
                node->ttl = TTL;    //更新ttl
                moveToHead(node);
            }
            updateTTL();
        }

        int get(int key){
            if(!key2value.count(key)){
                return -1;
            }
            Node* node = key2value[key];
            cout << "in get:" << node->ttl << ","<< node->val << endl;
            //惰性删除
            if(node->ttl<0){
                removeNode(node);
                key2value.erase(key);
                delete node;
                size--;
                return -1;
            }
            moveToHead(node);
            updateTTL();
            return node->val;
        }

        void printCache(){
            for(auto it = head->next;it!=tail;it=it->next){
                cout << " key = " << it->key << ", value = " << it->val << ", ttl = " << it->ttl << endl;
            }
        }

    private:
        //让每个ttl -1
        void updateTTL(){
            for(auto & it : key2value){
                it.second->ttl--;
            }
        }

        void addToHead(Node* node) {
            node->prev = head;
            node->next = head->next;
            head->next->prev = node;
            head->next = node;
        }

        void removeNode(Node* node) {
            node->prev->next = node->next;
            node->next->prev = node->prev;
        }

        void moveToHead(Node* node) {
            removeNode(node);
            addToHead(node);
        }

        Node* removeTail() {
            Node* node = tail->prev;
            removeNode(node);
            return node;
        }

        Node* head;
        Node* tail;
        int capacity;
        int size;
        unordered_map<int,Node*> key2value;
    };

    int main(){
        auto* lruCache = new LRUCache(3);
        lruCache->set(1,1);
        cout << lruCache->get(1) << endl;
        lruCache->set(2,2);
        lruCache->set(3,3);
        cout << lruCache->get(1) << endl;
        cout << lruCache->get(1) << endl;
        cout << lruCache->get(1) << endl;   //此时超时了，应该返回-1
        lruCache->printCache();
        lruCache->set(2,2);
        lruCache->set(3,3);
        lruCache->set(1,1);
        cout << lruCache->get(2) << endl;
        lruCache->printCache();
        lruCache->set(4,4);
        lruCache->printCache();
        cout << lruCache->get(3) << endl;   //3被淘汰，返回-1
        return 0;
    }
};


#endif //LEETCODE_BYTEDANCE_H
