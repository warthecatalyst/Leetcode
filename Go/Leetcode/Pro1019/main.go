package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

func nextLargerNodes(head *ListNode) []int {
	var ans []int
	var stack [][]int
	cur := head
	idx := -1
	for cur != nil {
		idx++
		ans = append(ans, 0)
		for len(stack) > 0 && stack[len(stack)-1][0] < cur.Val {
			top := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			ans[top[1]] = cur.Val
		}
		stack = append(stack, []int{cur.Val, idx})
		cur = cur.Next
	}
	return ans
}

func buildList(values []int) *ListNode {
	dummyHead := &ListNode{
		Val: 0,
	}
	cur := dummyHead
	for _, v := range values {
		cur.Next = &ListNode{
			Val: v,
		}
		cur = cur.Next
	}
	return dummyHead.Next
}

func printList(node *ListNode) {
	cur := node
	for cur != nil {
		fmt.Print(cur.Val, "->")
		cur = cur.Next
	}
	fmt.Println()
}

func main() {
	head := buildList([]int{2, 7, 4, 3, 5})
	printList(head)
	ans := nextLargerNodes(head)
	fmt.Println(ans)
}
