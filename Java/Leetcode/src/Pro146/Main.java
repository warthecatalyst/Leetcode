package Pro146;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}


class LRUCache {
    class DLinkNode{
        int key,val;
        DLinkNode next,prev;
        DLinkNode(){}
        DLinkNode(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    private Map<Integer,DLinkNode> dLinkNodeMap;
    private int size;
    private int capacity;
    private DLinkNode head,tail;

    public LRUCache(int capacity) {
        dLinkNodeMap = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkNode node = dLinkNodeMap.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        DLinkNode node = dLinkNodeMap.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkNode newNode = new DLinkNode(key, value);
            // 添加进哈希表
            dLinkNodeMap.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkNode tail = removeTail();
                // 删除哈希表中对应的项
                dLinkNodeMap.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.val = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkNode removeTail() {
        DLinkNode res = tail.prev;
        removeNode(res);
        return res;
    }
}