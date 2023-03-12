package Pro622

type MyCircularQueue struct {
	capacity int
	front    int
	rear     int
	data     []int
}

func Constructor(k int) MyCircularQueue {
	return MyCircularQueue{
		capacity: k + 1,
		front:    0,
		rear:     0,
		data:     make([]int, k+1),
	}
}

func (q *MyCircularQueue) EnQueue(value int) bool {
	if q.IsFull() {
		return false
	}
	q.data[q.rear] = value
	q.rear = (q.rear + 1) % q.capacity
	return true
}

func (q *MyCircularQueue) DeQueue() bool {
	if q.IsEmpty() {
		return false
	}
	q.front = (q.front + 1) % q.capacity
	return true
}

func (q *MyCircularQueue) Front() int {
	if q.IsEmpty() {
		return -1
	}
	return q.data[q.front]
}

func (q *MyCircularQueue) Rear() int {
	if q.IsEmpty() {
		return -1
	}
	return q.data[(q.rear-1+q.capacity)%q.capacity]
}

func (q *MyCircularQueue) IsEmpty() bool {
	return q.front == q.rear
}

func (q *MyCircularQueue) IsFull() bool {
	return (q.rear+1)%q.capacity == q.front
}
