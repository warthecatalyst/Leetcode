package Pro707;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.printLinkList();
        linkedList.addAtTail(3);
        linkedList.printLinkList();
        linkedList.addAtIndex(1,2);
        linkedList.printLinkList();
        linkedList.deleteAtIndex(0);
        linkedList.printLinkList();
    }
}


class MyLinkedList {
    public static class LinkNode{
        int val;
        LinkNode next;
        LinkNode prev;

        public LinkNode(){
            this(0);
        }

        public LinkNode(int val){
            this(val,null,null);
        }

        public LinkNode(int val,LinkNode next,LinkNode prev){
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    LinkNode head;
    LinkNode tail;

    public MyLinkedList() {
        this.head = new LinkNode();
        this.tail = new LinkNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        int curidx = 0;
        for(LinkNode cur = head.next;cur!=tail;cur = cur.next,curidx++){
            if(curidx==index){
                return cur.val;
            }
        }
        return -1;
    }

    public void addAtHead(int val) {
        LinkNode p = head.next;
        head.next = new LinkNode(val,p,head);
        p.prev = head.next;
    }

    public void addAtTail(int val) {
        LinkNode p = tail.prev;
        p.next = new LinkNode(val,tail,p);
        tail.prev = p.next;
    }

    public void addAtIndex(int index, int val) {
        if(index<0){
            addAtHead(val);
        }
        int curidx = 0;
        LinkNode cur = head.next,prev = head;
        for(;cur!=tail;cur = cur.next,prev = prev.next,curidx++){
            if(curidx==index){
                prev.next = new LinkNode(val,cur,prev);
                cur.prev = prev.next;
                return;
            }
        }
        if(curidx==index){
            addAtTail(val);
        }
    }

    public void deleteAtIndex(int index) {
        int curidx = 0;
        LinkNode prev = head,cur = head.next;
        for(;cur!=tail;cur = cur.next,prev = prev.next,curidx++){
            if(curidx==index){  //delete cur
                prev.next = cur.next;
                cur.next.prev = prev;
                return;
            }
        }
    }

    public void printLinkList(){
        for(LinkNode cur = head.next;cur!=tail;cur=cur.next){
            if(cur.next!=tail){
                System.out.print(cur.val+"->");
            }else{
                System.out.print(cur.val);
            }
        }
        System.out.println();
    }
}