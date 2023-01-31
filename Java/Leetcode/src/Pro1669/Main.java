package Pro1669;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ListNode list1 = createList(new int[]{0,1,2,3,4,5});
        ListNode list2 = createList(new int[]{10000,10001,10002});
        Solution solution = new Solution();
        printList(list1);
        printList(list2);
        list1 = solution.mergeInBetween(list1,3,4,list2);
        printList(list1);
    }

    public static void printList(ListNode head){
        for(ListNode cur = head;cur!=null;cur = cur.next){
            System.out.print(cur.val+"->");
        }
        System.out.println();
    }

    public static ListNode createList(int[] nums){
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        for(int num:nums){
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummyHead.next;
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
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = list1;
        ListNode pre = dummyHead, cur = list1;
        int cnt = 0;
        while (cur!=null) {
            if(cnt>=a){
                while(cnt<=b){
                    cur = cur.next;
                    cnt++;
                }
                pre.next = list2;
                ListNode iter2 = list2;
                for(;iter2.next!=null;iter2=iter2.next);
                iter2.next = cur;
                break;
            }else{
                cnt++;
                pre = cur;
                cur = cur.next;
            }
        }
        return list1;
    }
}
