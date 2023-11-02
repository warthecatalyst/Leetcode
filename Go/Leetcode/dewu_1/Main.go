package main

import "fmt"

func main() {
	var n, x int
	_, err := fmt.Scan(&n, &x)
	if err != nil {
		fmt.Println(err)
	}
	var s string
	fmt.Scan(&s)
	flag := false
	for i := 0; i <= n-x; i++ {
		// 检查i到i+x是否是回文串
		left, right := i, i+x-1
		for left < right {
			if s[left] == s[right] {
				left++
				right--
			} else {
				break
			}
		}
		if left >= right {
			flag = true
			break
		}
	}
	if flag {
		fmt.Println(1)
	} else {
		fmt.Println(0)
	}
}
