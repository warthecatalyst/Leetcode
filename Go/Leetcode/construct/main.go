package main

import "fmt"

func main() {
	result := largestPalindrome("abcdefgfedcbj")
	fmt.Println(result)
}

var directions = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func countIslands(mapping [][]int) int {
	//数岛屿，上下左右都为1
	n := len(mapping)
	if n == 0 {
		return 0
	}
	m := len(mapping[0])
	if m == 0 {
		return 0
	}

	visited := make([][]bool, n)
	for i := 0; i < n; i++ {
		visited[i] = make([]bool, m)
	}
	cnt := 0
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if mapping[i][j] == 1 && !visited[i][j] {
				cnt++
				dfs(mapping, visited, i, j, n, m)
			}
		}
	}
	return cnt
}

func dfs(mapping [][]int, visited [][]bool, i, j, n, m int) {
	visited[i][j] = true
	for k := 0; k < 4; k++ {
		newi := i + directions[k][0]
		newj := j + directions[k][1]
		if isIn(newi, newj, n, m) && !visited[newi][newj] && mapping[newi][newj] == 1 {
			dfs(mapping, visited, newi, newj, n, m)
		}
	}
}

func isIn(i, j, n, m int) bool {
	return i >= 0 && i < n && j >= 0 && j < m
}

func largestPalindrome(str string) int {
	//最长回文子串, dp?
	n := len(str)
	dp := make([][]bool, n) //dp[i][j]表示[i, j]是否是回文串
	maxLength := 1
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n+1)
		dp[i][i] = true
		if i < n-1 && str[i] == str[i+1] {
			dp[i][i+1] = true
			maxLength = 2
		}
	}

	for i := 2; i < n; i++ {
		for j := i - maxLength; j >= 0; j-- {
			dp[i][j] = dp[i-1][j+1] && str[i] == str[j]
			if dp[i][j] {
				maxLength = max(maxLength, i-j+1)
			}
		}
	}
	return maxLength
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
