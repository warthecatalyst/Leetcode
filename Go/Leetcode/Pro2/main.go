package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	cur1 := l1
	cur2 := l2
	add := 0
	dummyhead := new(ListNode)
	ans := dummyhead
	for cur1 != nil || cur2 != nil {
		if cur1 == nil {
			curNum := cur2.Val + add
			if curNum >= 10 {
				curNum -= 10
				add = 1
			} else {
				add = 0
			}
			ans.Next = &ListNode{
				Val:  curNum,
				Next: nil,
			}
			cur2 = cur2.Next
		} else if cur2 == nil {
			curNum := cur1.Val + add
			if curNum >= 10 {
				curNum -= 10
				add = 1
			} else {
				add = 0
			}
			ans.Next = &ListNode{
				Val:  curNum,
				Next: nil,
			}
			cur1 = cur1.Next
		} else {
			curNum := cur1.Val + cur2.Val + add
			if curNum >= 10 {
				curNum -= 10
				add = 1
			} else {
				add = 0
			}
			ans.Next = &ListNode{
				Val:  curNum,
				Next: nil,
			}
			cur1 = cur1.Next
			cur2 = cur2.Next
		}
		ans = ans.Next
	}
	if add == 1 {
		ans.Next = &ListNode{
			Val:  1,
			Next: nil,
		}
	}
	return dummyhead.Next
}

func main() {
	fmt.Println("Hello world")
}
