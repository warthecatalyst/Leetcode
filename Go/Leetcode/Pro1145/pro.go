package Pro1145

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func btreeGameWinningMove(root *TreeNode, n int, x int) bool {
	var xNode *TreeNode
	var getSubtreeSize func(*TreeNode) int
	getSubtreeSize = func(node *TreeNode) int {
		if node == nil {
			return 0
		}
		if node.Val == x {
			xNode = node
		}
		return 1 + getSubtreeSize(node.Left) + getSubtreeSize(node.Right)
	}
	getSubtreeSize(root)
	leftSize := getSubtreeSize(xNode.Left)
	if leftSize >= (n+1)/2 {
		return true
	}
	rightSize := getSubtreeSize(xNode.Right)
	if rightSize >= (n+1)/2 {
		return true
	}
	remain := n - leftSize - rightSize - 1
	return remain >= (n+1)/2
}

func buildTreeFromSlice(treeSlice []int) *TreeNode {
	if len(treeSlice) == 0 {
		return nil
	}
	n := len(treeSlice)
	root := new(TreeNode)
	type pair struct {
		node *TreeNode
		idx  int
	}
	queue := make(chan pair, n)
	defer close(queue)
	queue <- pair{node: root, idx: 0}
	for len(queue) > 0 {
		cur := <-queue
		cur.node.Val = treeSlice[cur.idx]
		if cur.idx*2+1 < n {
			// 在此题的条件中，可以保证cur.idx*2+2也在范围内
			leftNode := new(TreeNode)
			rightNode := new(TreeNode)
			cur.node.Left = leftNode
			cur.node.Right = rightNode
			queue <- pair{leftNode, cur.idx*2 + 1}
			queue <- pair{rightNode, cur.idx*2 + 2}
		}
	}
	return root
}

func Main() {
	treeslice := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}
	root := buildTreeFromSlice(treeslice)
	queue := make(chan *TreeNode, len(treeslice))
	queue <- root
	for len(queue) > 0 {
		cur := <-queue
		fmt.Print(cur.Val, " ")
		if cur.Left != nil {
			queue <- cur.Left
			queue <- cur.Right
		}
	}
}
