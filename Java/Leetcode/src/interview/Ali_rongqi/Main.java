package interview.Ali_rongqi;

class ListNode{
    int val;
    ListNode next;
    ListNode(){}
    ListNode(int val){this.val = val;}
}

public class Main {
    public static void main(String[] args) {
        ListNode head = buildList(new int[]{1,2,3,4,5});
        printList(head);
        deleteLastN(head,2);
        printList(head);
    }

    //删除链表的倒数第N个节点
    public static ListNode deleteLastN(ListNode head,int n){
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead, cur = head;
        //总共有多少个节点
        int all = 0;
        while(cur!=null){
            all++;
            cur = cur.next;
        }
        cur = head;
        for(int curCnt = 0;curCnt<all-n;curCnt++){
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = cur.next;  //删除
        return dummyHead.next;
    }

    public static void printList(ListNode head){
        for(ListNode cur = head;cur!=null;cur=cur.next){
            System.out.print(cur.val+"->");
        }
        System.out.println();
    }

    public static ListNode buildList(int[] nums){
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        for(int num:nums){
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
