package interview.tencent.Pro1;
import java.util.*;


class ListNode {
    int val;
    ListNode next = null;
}


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode reorderList (ListNode head) {
        // write code here
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode last = dummyHead;
        while(last!=null){
            ListNode[] back = getChange(last);
            ListNode cur1 = back[0],cur2 = back[1];
            if(cur1==null||cur2==null){
                break;
            }
            if(cur2.next!=null){
                cur2 = cur2.next;
            }
            cur1.next.next = cur2.next;
            last.next = back[1];
            cur2.next = cur1;
            last = cur1.next;
        }
        return dummyHead.next;
    }

    public ListNode[] getChange(ListNode last){
        int cnt = 0;
        ListNode cur = last;
        ListNode r1 = null,r2 = null;
        while(cur!=null&&cnt<4){
            cur = cur.next;
            cnt++;
            if(cnt==1){
                r1 = cur;
            }
            if(cnt==3){
                r2 = cur;
            }
        }
        return new ListNode[]{r1,r2};
    }
}
