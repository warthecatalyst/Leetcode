package main

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var ans int

func maxAncestorDiff(root *TreeNode) int {
	ans = 0
	dfs(root, root.Val, root.Val)
	return ans
}

func dfs(cur *TreeNode, maxVal, minVal int) {
	if maxVal-cur.Val > ans {
		ans = maxVal - cur.Val
	} else if cur.Val-minVal > ans {
		ans = cur.Val - minVal
	}
	//processLeft
	if cur.Left != nil {
		leftVal := cur.Left.Val
		newMaxVal := maxVal
		newMinVal := minVal
		if leftVal > newMaxVal {
			newMaxVal = leftVal
		}
		if leftVal < newMinVal {
			newMinVal = leftVal
		}
		dfs(cur.Left, newMaxVal, newMinVal)
	}
	if cur.Right != nil {
		rightVal := cur.Right.Val
		newMaxVal := maxVal
		newMinVal := minVal
		if rightVal > newMaxVal {
			newMaxVal = rightVal
		}
		if rightVal < newMinVal {
			newMinVal = rightVal
		}
		dfs(cur.Right, newMaxVal, newMinVal)
	}
}
