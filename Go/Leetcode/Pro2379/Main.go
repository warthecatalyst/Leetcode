package main

func minimumRecolors(blocks string, k int) int {
	n := len(blocks)
	blackCnt := 0
	ans := n
	for i := range blocks {
		if i+k > len(blocks) {
			break
		}
		if i == 0 {
			for j := i; j < k; j++ {
				if blocks[j] == 'B' {
					blackCnt++
				}
			}
		}
		ans = min(ans, min(0, k-blackCnt))
		if blocks[i] == 'B' {
			blackCnt--
		}
		if blocks[i+k] == 'B' {
			blackCnt++
		}
	}
	return ans
}

func min(a, b int) int {
	if a <= b {
		return a
	}
	return b
}
