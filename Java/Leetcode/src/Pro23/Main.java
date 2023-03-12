package Pro23;

public class Main {
    public static void main(String[] args) {

    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        ListNode[] pointers = new ListNode[n];
        System.arraycopy(lists, 0, pointers, 0, n);
//        for (ListNode pointer : pointers) {
//            System.out.println(pointer.val);
//        }
        int res = getPointer(pointers);
        while(res != -1){
            cur.next = pointers[res];
            cur = cur.next;
            pointers[res] = pointers[res].next;
            res = getPointer(pointers);
        }
        return dummyHead.next;
    }

    public int getPointer(ListNode[] pointers){
        int minVal = Integer.MAX_VALUE;
        int idx = -1;
        for(int i = 0;i<pointers.length;i++){
            if(pointers[i] == null){
                continue;
            }
            if(pointers[i].val < minVal){
                idx = i;
                minVal = pointers[i].val;
            }
        }
        return idx;
    }
}
