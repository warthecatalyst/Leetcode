package main

func shortestCommonSupersequence(str1 string, str2 string) string {
	n, m := len(str1), len(str2)
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, m+1)
	}
	for i := 0; i < n; i++ {
		dp[i][m] = n - i
	}
	for i := 0; i < m; i++ {
		dp[n][i] = m - i
	}
	for i := n - 1; i >= 0; i-- {
		for j := m - 1; j >= 0; j-- {
			if str1[i] == str2[j] {
				dp[i][j] = dp[i+1][j+1] + 1
			} else {
				dp[i][j] = min(dp[i+1][j], dp[i][j+1]) + 1
			}
		}
	}
	res := ""
	t1, t2 := 0, 0
	for t1 < n && t2 < m {
		if str1[t1] == str2[t2] {
			res += string(str1[t1])
			t1++
			t2++
		} else if dp[t1+1][t2] == dp[t1][t2]-1 {
			res += string(str1[t1])
			t1++
		} else if dp[t1][t2+1] == dp[t1][t2]-1 {
			res += string(str2[t2])
			t2++
		}
	}
	if t1 < n {
		res += str1[t1:]
	} else if t2 < m {
		res += str2[t2:]
	}
	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
