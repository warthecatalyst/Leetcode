package main

import (
	"fmt"
	"sort"
)

func main() {
	result := getTarget([]int{10, 1, 2, 7, 6, 1, 5}, 9)
	fmt.Println(result)
}

// Input: candidates = [10,1,2,7,6,1,5], target = 8
// Output:
// [
// [1,1,6],
// [1,2,5],
// [1,7],
// [2,6]
// ]
func getTarget(candidates []int, target int) [][]int {
	sort.Ints(candidates)
	ans := make([][]int, 0)
	chosen := make([]int, 0)
	visited := make([]bool, len(candidates))
	backTrack(candidates, chosen, 0, target, &ans, visited)
	return ans
}

func backTrack(candidates, chosen []int, curIndex, target int, result *[][]int, visited []bool) {
	if target == 0 {
		//去重, 不太好的方法
		//if contains(result, chosen) {
		//	return
		//}
		// deep copy
		tmp := make([]int, len(chosen))
		copy(tmp, chosen)
		*result = append(*result, tmp)
		return
	}
	//排序完成之后可以剪枝
	if curIndex == len(candidates) || target < candidates[curIndex] {
		return
	}
	//当前数字选或者不选
	if curIndex > 0 && candidates[curIndex] == candidates[curIndex-1] && visited[curIndex-1] {
		//当前数字不能选
		visited[curIndex] = true
		backTrack(candidates, chosen, curIndex+1, target, result, visited)
		return
	}
	chosen = append(chosen, candidates[curIndex])
	target -= candidates[curIndex]
	backTrack(candidates, chosen, curIndex+1, target, result, visited)
	// 回溯
	target += candidates[curIndex]
	chosen = chosen[:len(chosen)-1]
	// 回溯完成之后认为当前数字已经遍历过
	visited[curIndex] = true
	backTrack(candidates, chosen, curIndex+1, target, result, visited)
}

func contains(result *[][]int, tmp []int) bool {
	for _, arr := range *result {
		if len(arr) == len(tmp) {
			allSame := true
			for i := 0; i < len(arr); i++ {
				if arr[i] != tmp[i] {
					allSame = false
				}
			}
			if allSame {
				return true
			}
		}
	}
	return false
}
