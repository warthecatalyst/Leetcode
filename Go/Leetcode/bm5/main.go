package main

import "math"

type ListNode struct {
	Val  int
	Next *ListNode
}

// 合并K个已排序的链表
func mergeKLists(lists []*ListNode) *ListNode {
	// write code here
	dummyHead := new(ListNode)
	cur := dummyHead
	pointers := make([]*ListNode, 0)
	for _, list := range lists {
		pointers = append(pointers, list)
	}
	idx := getPointer(pointers)
	for idx != -1 {
		cur.Next = pointers[idx]
		pointers[idx] = pointers[idx].Next
		cur = cur.Next
		idx = getPointer(pointers)
	}
	return dummyHead.Next
}

func getPointer(pointers []*ListNode) int {
	idx := -1
	minVal := math.MaxInt32
	for i, pointer := range pointers {
		if pointer != nil && pointer.Val < minVal {
			idx = i
		}
	}
	return idx
}
