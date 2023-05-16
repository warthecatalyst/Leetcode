package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	dummyHead := &ListNode{Val: 0}
	cur := dummyHead
	carry := 0
	for l1 != nil || l2 != nil {
		bit := 0
		if l1 == nil {
			bit = l2.Val + carry
			l2 = l2.Next
		} else if l2 == nil {
			bit = l1.Val + carry
			l1 = l1.Next
		} else {
			bit = l1.Val + l2.Val + carry
			l1 = l1.Next
			l2 = l2.Next
		}
		if bit >= 10 {
			bit -= 10
			carry = 1
		} else {
			carry = 0
		}
		cur.Next = &ListNode{
			Val: bit,
		}
		cur = cur.Next
	}
	if carry == 1 {
		cur.Next = &ListNode{Val: carry}
	}
	return dummyHead.Next
}

func main() {
	fmt.Println("Hello world")
}
