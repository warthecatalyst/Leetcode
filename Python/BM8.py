class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
#
# 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
#
#
# @param pHead ListNode类
# @param k int整型
# @return ListNode类
#
class Solution:
    def FindKthToTail(self , pHead: ListNode, k: int) -> ListNode:
        # write code here
        length = 0
        it = pHead
        while it is not None:
            it = it.next
            length+=1
        length -= k
        if length < 0:
            return None
        it = pHead
        for i in range(0,length):
            it = it.next
        return it