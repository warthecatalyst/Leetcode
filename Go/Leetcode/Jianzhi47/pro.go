package main

func maxValue(grid [][]int) int {
	n, m := len(grid), len(grid[0])
	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, m)
	}
	for i, g := range grid {
		for j, x := range g {
			if i > 0 {
				dp[i][j] = max(dp[i][j], dp[i-1][j])
			}
			if j > 0 {
				dp[i][j] = max(dp[i][j], dp[i][j-1])
			}
			dp[i][j] += x
		}
	}
	return dp[n-1][m-1]
}

func max(a, b int) int {
	if a >= b {
		return a
	}
	return b
}

func main() {

}
