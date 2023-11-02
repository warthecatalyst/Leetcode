//
// Created by 周鑫宜 on 2023/9/26.
//

#ifndef CPP_NEW_PRO1993_H
#define CPP_NEW_PRO1993_H
#include "global.h"

class LockingTree {
public:
    LockingTree(vector<int>& parent) {
        int n = parent.size();
        this->parent = parent;
        this->lockedUsers = vector<int>(n, -1);
        this->children = vector<vector<int>>(n);
        for(int i = 0;i < n;i++) {
            if(parent[i] >= 0) {
                children[parent[i]].push_back(i);
            }
        }
    }

    bool lock(int num, int user) {
        if (lockedUsers[num] == -1) {
            lockedUsers[num] = user;
            return true;
        }
        return false;
    }

    bool unlock(int num, int user) {
        if (lockedUsers[num] == user) {
            lockedUsers[num] = -1;
            return true;
        }
        return false;
    }

    bool upgrade(int num, int user) {
        if (lockedUsers[num] == -1) {
            // dfs查询其所有上锁的子孙节点 和 未上锁的祖先节点
            if (dfs(num) && dfsParent(num)) {
                unlockDfs(num);
                lockedUsers[num] = user;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

private:
    bool dfs(int node) {
        if (lockedUsers[node] >= 0) {
            return true;
        }
        for (int child: children[node]) {
            if(dfs(child)) {
                return true;
            }
        }
        return false;
    }

    bool dfsParent(int node) {
        if (node == -1) {
            return true;
        }
        if(lockedUsers[node] != -1) {
            return false;
        }
        return dfsParent(parent[node]);
    }

    void unlockDfs(int node) {
        lockedUsers[node] = -1;
        for (int child : children[node]) {
            unlockDfs(child);
        }
    }

    vector<int> parent;
    vector<vector<int>> children;
    vector<int> lockedUsers;
};


#endif //CPP_NEW_PRO1993_H
