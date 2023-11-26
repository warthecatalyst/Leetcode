package main

import "sort"

func main() {

}

func areSimilar(mat [][]int, k int) bool {
	n, m := len(mat), len(mat[0])
	//偶数行右移，奇数行左移
	k = k % m
	for i := 0; i < n; i++ {
		line := mat[i]
		if i%2 == 0 {
			for j := 0; j < m; j++ {
				if line[(j+k)%m] != line[j] {
					return false
				}
			}
		} else {
			for j := 0; j < m; j++ {
				if line[(j+m-k)%m] != line[j] {
					return false
				}
			}
		}
	}
	return true
}

func beautifulSubstrings(s string, k int) int {
	n := len(s)
	ans := 0
	dpVowel := make([][]int, n)
	dpCons := make([][]int, n)
	for i := 0; i < n; i++ {
		dpVowel[i] = make([]int, n)
		dpCons[i] = make([]int, n)
		if isVowel(s[i]) {
			dpVowel[i][i] = 1
		} else {
			dpCons[i][i] = 1
		}
		for j := i + 1; j < n; j++ {
			if isVowel(s[j]) {
				dpVowel[i][j] = dpVowel[i][j-1] + 1
				dpCons[i][j] = dpCons[i][j-1]
			} else {
				dpVowel[i][j] = dpVowel[i][j-1]
				dpCons[i][j] = dpCons[i][j-1] + 1
			}
			if dpVowel[i][j] == dpCons[i][j] && (dpVowel[i][j]*dpCons[i][j])%k == 0 && dpVowel[i][j] > 0 {
				ans++
			}
		}
	}
	return ans
}

func isVowel(c byte) bool {
	return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
}

func lexicographicallySmallestArray(nums []int, limit int) []int {
	n := len(nums)
	vals := make([][]int, n)
	for i := 0; i < n; i++ {
		vals[i] = []int{nums[i], i}
	}
	sort.Slice(vals, func(i, j int) bool {
		return vals[i][0] < vals[j][0]
	})
	j := 0
	ans := make([]int, n)
	for j < n {
		idx := make([]int, 0)
		values := make([]int, 0)
		values = append(values, vals[j][0])
		idx = append(idx, vals[j][1])
		t := j + 1
		for t < n && vals[t][0]-vals[t-1][0] <= limit {
			values = append(values, vals[t][0])
			idx = append(idx, vals[t][1])
			t++
		}
		sort.Ints(values)
		sort.Ints(idx)
		for i := 0; i < len(values); i++ {
			ans[idx[i]] = values[i]
		}
		j = t
	}
	return ans
}
