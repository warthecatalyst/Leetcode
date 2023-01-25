package Pro2287

func rearrangeCharacters(s string, target string) int {
	var cntS, cntT [26]int
	for _, c := range s {
		cntS[c-'a']++
	}
	for _, c := range target {
		cntT[c-'a']++
	}
	ans := len(s)
	for i, c := range cntT {
		if c > 0 {
			ans = min(ans, cntS[i]/c)
			if ans == 0 {
				return 0
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a > b {
		return b
	}
	return a
}
