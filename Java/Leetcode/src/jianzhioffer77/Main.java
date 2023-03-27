package jianzhioffer77;

import java.util.*;
import ListNode.ListNode;


public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public ListNode sortList(ListNode head) {
        return sortList(head,null);
    }

    private ListNode sortList(ListNode head,ListNode tail){
        if(head==null){
            return null;
        }
        if(head.next==tail){
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return mergeSortedLists(list1, list2);
    }

    private ListNode mergeSortedLists(ListNode list1,ListNode list2){
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while(list1!=null&&list2!=null){
            if(list1.val<=list2.val){
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            }else{
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        while(list1!=null){
            cur.next = list1;
            cur = cur.next;
            list1 = list1.next;
        }
        while(list2!=null){
            cur.next = list2;
            cur = cur.next;
            list2 = list2.next;
        }
        return dummyHead.next;
    }
}
