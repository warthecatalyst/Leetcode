package main

import "sort"

func longestStrChain(words []string) int {
	cnt := map[string]int{}
	sort.Slice(words, func(i, j int) bool {
		return len(words[i]) < len(words[j])
	})
	res := 0
	for _, word := range words {
		cnt[word] = 1
		for i := range word {
			prev := word[:i] + word[i+1:]
			if j := cnt[prev] + 1; j > cnt[word] {
				cnt[word] = j
			}
		}
		if cnt[word] > res {
			res = cnt[word]
		}
	}
	return res
}
