package main

func main() {

}

var romToInt = map[byte]int{
	'I': 1,
	'V': 5,
	'X': 10,
	'L': 50,
	'C': 100,
	'D': 500,
	'M': 1000,
}

func romanToInt(s string) int {
	ans := 0
	n := len(s)
	for i := 0; i < n; i++ {
		value := romToInt[s[i]]
		if i < n-1 && value < romToInt[s[i+1]] {
			ans -= value
		} else {
			ans += value
		}
	}
	return ans
}
