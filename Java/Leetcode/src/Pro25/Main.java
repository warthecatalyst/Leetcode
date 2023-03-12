package Pro25;

import ListNode.ListNode;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode pre = dummyHead;
        ListNode end = dummyHead;
        while(end.next!=null){
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummyHead.next;
    }

    public ListNode reverse(ListNode root){
        ListNode pre = null;
        ListNode cur = root;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}



