package main

func longestDecomposition(text string) int {
	if len(text) <= 1 {
		return 1
	}
	str := text
	ans := 0
	for len(str) > 0 {
		l, r := 1, len(str)-1
		for l <= r {
			if str[:l] == str[r:] {
				ans += 2
				str = str[l:r]
				break
			}
			l++
			r--
		}
		if l > r && len(str) > 0 {
			ans++
			break
		}
	}
	return ans
}

func main() {

}
