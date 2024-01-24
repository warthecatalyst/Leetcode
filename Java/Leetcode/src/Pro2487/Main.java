package Pro2487;

import java.util.*;
import ListNode.ListNode;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        if(head.next != null && head.val < head.next.val) {
            return head.next;
        } else {
            return head;
        }
    }
}
