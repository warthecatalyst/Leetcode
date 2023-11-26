package main

import "math"

func main() {

}

func minPathCost(grid [][]int, moveCost [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
		for j := 0; j < n; j++ {
			dp[i][j] = math.MaxInt
		}
	}
	for i := 0; i < n; i++ {
		dp[0][i] = grid[0][i]
	}
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < n; k++ {
				dp[i][j] = min(dp[i][j], dp[i-1][k]+moveCost[grid[i-1][k]][j])
			}
			dp[i][j] += grid[i][j]
		}
	}
	ans := math.MaxInt
	for i := 0; i < n; i++ {
		ans = min(ans, dp[m-1][i])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
