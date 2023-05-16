package main

func lengthOfLongestSubstring(s string) int {
	cnt := make(map[uint8]int, 0)
	left := 0
	ans := 0
	for i := 0; i < len(s); i++ {
		value, ok := cnt[s[i]]
		if ok {
			left = max(left, value+1)
		}
		ans = max(ans, i-left+1)
		cnt[s[i]] = i
	}
	return ans
}

func max(a, b int) int {
	if a >= b {
		return a
	}
	return b
}

func main() {

}
