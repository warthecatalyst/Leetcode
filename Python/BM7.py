class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def EntryNodeOfLoop(self, pHead):
        if pHead is None:
            return None
        fast, slow = pHead, pHead
        while fast is not None and fast.next is not None:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                break
        if fast is None or fast.next is None:
            return None
        fast = pHead
        while fast != slow:
            fast = fast.next
            slow = slow.next
        return fast
