class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

#
#
# @param head ListNode类
# @return bool布尔型
#
class Solution:
    def hasCycle(self , head: ListNode) -> bool:
        if head is None:
            return False
        slow, fast = head, head
        while slow is not None and fast is not None:
            slow = slow.next
            fast = fast.next
            if fast is not None:
                fast = fast.next
            if slow == fast:
                return True
        return False