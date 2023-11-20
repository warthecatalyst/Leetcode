package main

func main() {

}

type linkNode struct {
	prev, next *linkNode
	key, val   int
}

type LRUCache struct {
	nodeMap        map[int]*linkNode
	capacity, size int
	head, tail     *linkNode
}

func Constructor(capacity int) LRUCache {
	cache := LRUCache{
		nodeMap:  make(map[int]*linkNode),
		capacity: capacity,
		size:     0,
		head:     new(linkNode),
		tail:     new(linkNode),
	}
	cache.head.next = cache.tail
	cache.tail.prev = cache.head
	return cache
}

func (this *LRUCache) Get(key int) int {
	node, ok := this.nodeMap[key]
	if !ok {
		return -1
	}
	this.moveToHead(node)
	return node.val
}

func (this *LRUCache) Put(key int, value int) {
	node, ok := this.nodeMap[key]
	if !ok {
		newNode := new(linkNode)
		newNode.val = value
		newNode.key = key
		this.nodeMap[key] = newNode
		this.addToHead(newNode)
		this.size++
		if this.size == this.capacity {
			// 删除尾部
			tail := this.deleteTail()
			delete(this.nodeMap, tail.key)
		}
	} else {
		node.val = value
		this.moveToHead(node)
	}
}

func (this *LRUCache) addToHead(node *linkNode) {
	next := this.head.next
	this.head.next = node
	node.prev = this.head
	node.next = next
	next.prev = node
}

func (this *LRUCache) deleteNode(node *linkNode) {
	node.prev.next = node.next
	node.next.prev = node.prev
}

func (this *LRUCache) moveToHead(node *linkNode) {
	this.deleteNode(node)
	this.addToHead(node)
}

func (this *LRUCache) deleteTail() *linkNode {
	node := this.tail.prev
	this.deleteNode(this.tail.prev)
	return node
}
