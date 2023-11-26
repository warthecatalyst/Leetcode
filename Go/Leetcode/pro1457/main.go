package main

func main() {

}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var ans int

func pseudoPalindromicPaths(root *TreeNode) int {
	ans = 0
	dfs(root, make(map[int]int))
	return ans
}

func dfs(cur *TreeNode, dict map[int]int) {
	dict[cur.Val]++
	defer func() { dict[cur.Val]-- }()
	if cur.Left == nil && cur.Right == nil {
		if isPalindrone(dict) {
			ans++
		}
		return
	}
	if cur.Left != nil {
		dfs(cur.Left, dict)
	}
	if cur.Right != nil {
		dfs(cur.Right, dict)
	}
}

func isPalindrone(dict map[int]int) bool {
	oddCount := 0
	for _, v := range dict {
		if v%2 == 1 {
			oddCount++
		}
	}
	return oddCount == 0 || oddCount == 1
}
