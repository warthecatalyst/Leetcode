package main

import "container/list"

func main() {

}

type FrontMiddleBackQueue struct {
	left  *list.List
	right *list.List
}

func Constructor() FrontMiddleBackQueue {
	return FrontMiddleBackQueue{
		left:  list.New(),
		right: list.New(),
	}
}

func (this *FrontMiddleBackQueue) PushFront(val int) {
	this.left.PushFront(val)
	if this.left.Len() == this.right.Len()+2 {
		this.right.PushFront(this.left.Back().Value.(int))
		this.left.Remove(this.left.Back())
	}
}

func (this *FrontMiddleBackQueue) PushMiddle(val int) {
	if this.left.Len() == this.right.Len()+1 {
		this.right.PushFront(this.left.Back().Value.(int))
		this.left.Remove(this.left.Back())
	}
	this.left.PushBack(val)
}

func (this *FrontMiddleBackQueue) PushBack(val int) {
	this.right.PushBack(val)
	if this.left.Len()+1 == this.right.Len() {
		this.left.PushBack(this.right.Front().Value.(int))
		this.right.Remove(this.right.Front())
	}
}

func (this *FrontMiddleBackQueue) PopFront() int {
	if this.left.Len() == 0 {
		return -1
	}
	val := this.left.Front()
	this.left.Remove(this.left.Front())
	if this.left.Len()+1 == this.right.Len() {
		this.left.PushBack(this.right.Front().Value.(int))
		this.right.Remove(this.right.Front())
	}
	return val.Value.(int)
}

func (this *FrontMiddleBackQueue) PopMiddle() int {
	if this.left.Len() == 0 {
		return -1
	}
	val := this.left.Back()
	this.left.Remove(this.left.Back())
	if this.left.Len()+1 == this.right.Len() {
		this.left.PushBack(this.right.Front().Value.(int))
		this.right.Remove(this.right.Front())
	}
	return val.Value.(int)
}

func (this *FrontMiddleBackQueue) PopBack() int {
	if this.left.Len() == 0 {
		return -1
	}
	if this.right.Len() == 0 {
		val := this.left.Back()
		this.left.Remove(this.left.Back())
		return val.Value.(int)
	} else {
		val := this.right.Back()
		this.right.Remove(this.right.Back())
		if this.left.Len() == this.right.Len()+2 {
			this.right.PushFront(this.left.Back().Value.(int))
			this.left.Remove(this.left.Back())
		}
		return val.Value.(int)
	}
}
