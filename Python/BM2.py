class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def reverseBetween(self, head: ListNode, m: int, n: int) -> ListNode:
        # write code here
        dummyHead = ListNode(0)
        dummyHead.next = head
        prev = dummyHead
        for i in range(0, m-1):
            prev = prev.next
        cur = prev.next
        for j in range(0, n-m):
            cur_next = cur.next
            cur.next = cur_next.next
            cur_next.next = prev.next
            prev.next = cur_next
        return dummyHead.next
