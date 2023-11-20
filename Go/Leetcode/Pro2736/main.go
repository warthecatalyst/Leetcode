package main

import "sort"

func main() {

}

func maximumSumQueries(nums1 []int, nums2 []int, queries [][]int) []int {
	n := len(nums1)
	sortedNums := make([][]int, n)
	for i := 0; i < n; i++ {
		sortedNums[i] = []int{nums1[i], nums2[i]}
	}
	sort.Slice(sortedNums, func(i, j int) bool {
		return sortedNums[i][0] > sortedNums[j][0]
	})

	sortedQueries := make([][]int, len(queries))
	for i := 0; i < len(queries); i++ {
		sortedQueries[i] = []int{i, queries[i][0], queries[i][1]}
	}
	sort.Slice(sortedQueries, func(i, j int) bool {
		return sortedQueries[i][1] > sortedQueries[j][1]
	})
	var stack [][]int
	answer := make([]int, len(queries))
	for i := 0; i < len(queries); i++ {
		answer[i] = -1
	}
	j := 0
	for _, q := range sortedQueries {
		i, x, y := q[0], q[1], q[2]
		for j < len(sortedNums) && sortedNums[j][0] >= x {
			num1, num2 := sortedNums[j][0], sortedNums[j][1]
			for len(stack) > 0 && stack[len(stack)-1][1] <= num1+num2 {
				stack = stack[:len(stack)-1]
			}
			if len(stack) == 0 || stack[len(stack)-1][0] < num2 {
				stack = append(stack, []int{num2, num1 + num2})
			}
			j++
		}
		k := sort.Search(len(stack), func(i int) bool {
			return stack[i][0] >= y
		})

		if k < len(stack) {
			answer[i] = stack[k][1]
		}
	}
	return answer
}
