package main

import "strings"

func main() {

}

func longestCommonPrefix(strs []string) string {
	firstStr := strs[0]
	//对于str的每个前缀去判断
	front := ""
	for i := 1; i <= len(firstStr); i++ {
		frontStr := firstStr[:i]
		for _, str := range strs {
			if !strings.HasPrefix(str, frontStr) {
				return front
			}
		}
		front = frontStr
	}
	return front
}
