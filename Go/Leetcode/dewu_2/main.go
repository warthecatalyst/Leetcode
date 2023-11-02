package main

import (
	"fmt"
	"sort"
)

var n, m int

func main() {
	fmt.Scan(&n, &m)
	nums := make([]int, n)
	for i := 0; i < n; i++ {
		fmt.Scan(&nums[i])
	}
	sort.Ints(nums)
	// fmt.Println(nums)
	dp := make([]int, m+1)
	for i := 0; i <= m; i++ {
		dp[i] = 0x3f3f3f3f
	}
	for _, num := range nums {
		if num > m {
			break
		}
		dp[num] = 1
		for idx := num; idx <= m; idx++ {
			if dp[idx-num] != 0x3f3f3f3f {
				dp[idx] = min(dp[idx], dp[idx-num]+1)
			}
		}
	}
	if dp[m] == 0x3f3f3f3f {
		fmt.Println("No solution")
	} else {
		fmt.Println(dp[m])
	}
}

func min(a, b int) int {
	if a < b {
		return a
	} else {
		return b
	}
}
