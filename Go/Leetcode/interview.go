package main

func main() {
}

type TreeNode struct {
	val         int
	left, right *TreeNode
}

var treeSize map[int][]int

var maxScore int

func calculateMaxScore(root *TreeNode) int {
	treeSize = make(map[int][]int)
	// dfs(root)
	//maxScore := 0
	//for key, val := range treeSize {
	//	if key == root.val {
	//		maxScore = max(maxScore, val[0]*val[1])
	//	} else {
	//		// 树大小减去子树大小
	//		tmp := treeSize[root.val][0] + treeSize[root.val][1] - val[0] - val[1]
	//		if val[0] == 0 && val[1] == 0 {
	//			maxScore = max(maxScore, tmp)
	//		} else if val[0] == 0 {
	//			maxScore = max(maxScore, tmp*val[1])
	//		} else if val[1] == 0 {
	//			maxScore = max(maxScore, tmp*val[0])
	//		} else {
	//			maxScore = max(maxScore, tmp*val[0]*val[1])
	//		}
	//	}
	//}
	maxScore = 0
	rootSize := dfs(root)
	dfs1(root, rootSize)
	return maxScore
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

// 找到所有节点的左右子树大小
func dfs(node *TreeNode) int {
	if node == nil {
		return 0
	}
	leftSize := dfs(node.left)
	rightSize := dfs(node.right)
	return leftSize + rightSize + 1
}

func dfs1(node *TreeNode, rootSize int) int {
	scores := make([]int, 3)
	scores[1] = dfs1(node.left, rootSize)            //左子树
	scores[2] = dfs1(node.right, rootSize)           //右子树
	scores[0] = rootSize - scores[1] - scores[2] - 1 //根节点的树
	res := 1
	for _, score := range scores {
		if score == 0 {
			continue
		}
		res *= score
	}
	maxScore = max(res, maxScore)
	return rootSize - scores[0]
}
