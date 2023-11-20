package main

import (
	"container/heap"
	"fmt"
)

type PriorityQueue []int

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i] > pq[j]
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *PriorityQueue) Push(x interface{}) {
	it := x.(int)
	*pq = append(*pq, it)
}

func (pq *PriorityQueue) Pop() interface{} {
	it := (*pq)[len(*pq)-1]
	*pq = (*pq)[:len(*pq)-1]
	return it
}

func main() {
	pq := make(PriorityQueue, 0)
	pq = append(pq, 2)
	pq = append(pq, 1)
	pq = append(pq, 3)
	heap.Init(&pq)
	heap.Push(&pq, 5)
	for pq.Len() > 0 {
		fmt.Println(heap.Pop(&pq).(int))
	}
}
