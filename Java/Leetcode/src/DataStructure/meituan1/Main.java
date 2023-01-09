package DataStructure.meituan1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ListNode list = generateList(new int[]{1,1,2,3,3,4});
        ListNode.printList(list);
        Solution solution = new Solution();
        list = solution.removeDuplicate(list);
        ListNode.printList(list);
    }



    public static ListNode generateList(int[] vals){
        ListNode dummyhead = new ListNode(0);
        ListNode cur = dummyhead;
        for(int val:vals){
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return dummyhead.next;
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode(){val = 0;next = null;}
    public ListNode(int val){
        this.val = val;
        next = null;
    }
    public ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val;
    }

    public static void printList(ListNode node){
        if(node == null){
            System.out.println("null");
            return;
        }
        for(ListNode cur = node;cur!=null;cur=cur.next){
            System.out.print(cur.val+"->");
        }
        System.out.println();
    }
}

class Solution{
    public ListNode removeDuplicate(ListNode head){
        ListNode dummyHead = new ListNode(-1,head);
        for(ListNode cur = head, prev = dummyHead; cur != null;){
            ListNode sameEnd = cur.next;
            while(sameEnd!=null&&sameEnd.val==cur.val){
                sameEnd = sameEnd.next;
            }
            if(sameEnd==cur.next){
                prev = prev.next;
                cur = cur.next;
            }else{
                prev.next = sameEnd;
                cur = sameEnd;
            }
        }
        return dummyHead.next;
    }
}
