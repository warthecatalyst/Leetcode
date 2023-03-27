package main

import "sort"

type player struct {
	score, age int
}

func bestTeamScore(scores []int, ages []int) int {
	n := len(scores)
	players := make([]player, 0)
	for i := range scores {
		players = append(players, player{
			score: scores[i],
			age:   ages[i],
		})
	}
	sort.Slice(players, func(i, j int) bool {
		if players[i].score == players[j].score {
			return players[i].age < players[j].age
		}
		return players[i].score < players[j].score
	})
	dp := make([]int, n)
	res := 0
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			if players[j].age <= players[i].age {
				dp[i] = max(dp[i], dp[j])
			}
		}
		dp[i] += players[i].score
		res = max(res, dp[i])
	}
	return res
}

func max(a, b int) int {
	if b > a {
		return b
	}
	return a
}
