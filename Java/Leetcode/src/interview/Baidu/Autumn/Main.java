package interview.Baidu.Autumn;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = buildList(new int[]{1,2,3,4,5,6});
        ListNode ans = solution.reverseKGroup(head, 3);
        printList(ans);
    }

    private static ListNode buildList(int[] nums) {
        ListNode dummyHead = new ListNode(), cur = dummyHead;
        for(int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummyHead.next;
    }

    private static void printList(ListNode head) {
        ListNode cur = head;
        while(cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next;}
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = dummyHead, end = dummyHead;
        while(end.next != null) {
            // 找到K个并反转
            int count = k;
            while(end != null && count-->0) {
                end = end.next;
                //System.out.println(count);
            }
            if(end == null) {
                break;
            }
            ListNode next = end.next;
            // System.out.println("end.val = " + end.val);
            end.next = null;
            ListNode newPart = reverseListNode(cur.next);
            ListNode nextRound = cur.next;
            cur.next = newPart;
            cur = nextRound;
            cur.next = next;
            end = nextRound;
        }
        return dummyHead.next;
    }

    private ListNode reverseListNode(ListNode head) {
        ListNode pre = null, cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
