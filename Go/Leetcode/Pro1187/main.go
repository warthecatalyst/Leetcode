package main

import (
	"math"
	"sort"
)

func makeArrayIncreasing(arr1 []int, arr2 []int) int {
	sort.Ints(arr2)
	n, m := len(arr1), len(arr2)
	dp := make([][]int, n+1)
	for i := range dp {
		dp[i] = make([]int, min(n, m)+1)
		for j := range dp[i] {
			dp[i][j] = math.MaxInt
		}
	}
	dp[0][0] = -1
	for i := 1; i <= n; i++ {
		for j := 0; j <= min(i, m); j++ {
			if arr1[i-1] > dp[i-1][j] {
				dp[i][j] = arr1[i-1]
			}
			if j > 0 && dp[i-1][j-1] != math.MaxInt {
				k := j - 1 + sort.SearchInts(arr2[j-1:], dp[i-1][j-1]+1)
				if k < m {
					dp[i][j] = min(dp[i][j], arr2[k])
				}
			}
			if i == n && dp[i][j] != math.MaxInt {
				return j
			}
		}
	}
	return -1
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func main() {

}
