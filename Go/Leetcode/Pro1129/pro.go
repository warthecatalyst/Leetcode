package Pro1129

func shortestAlternatingPaths(n int, redEdges [][]int, blueEdges [][]int) []int {
	type pair struct{ x, color int }
	graph := make([][]pair, n)
	for _, e := range redEdges {
		graph[e[0]] = append(graph[e[0]], pair{e[1], 0})
	}
	for _, e := range blueEdges {
		graph[e[0]] = append(graph[e[0]], pair{e[1], 1})
	}

	dis := make([]int, n)
	for i := range dis {
		dis[i] = -1
	}
	vis := make([][2]bool, n)
	vis[0] = [2]bool{true, true}
	q := []pair{{0, 0}, {0, 1}}
	for level := 0; len(q) > 0; level++ {
		tmp := q
		q = nil
		for _, p := range tmp {
			x := p.x
			if dis[x] < 0 {
				dis[x] = level
			}
			for _, to := range graph[x] {
				if to.color != p.color && !vis[to.x][to.color] {
					vis[to.x][to.color] = true
					q = append(q, to)
				}
			}
		}
	}
	return dis
}

func shortestAlternatingPaths_reference(n int, redEdges, blueEdges [][]int) []int {
	type pair struct{ x, color int }
	graph := make([][]pair, n)
	for _, e := range redEdges {
		graph[e[0]] = append(graph[e[0]], pair{e[1], 0})
	}
	for _, e := range blueEdges {
		graph[e[0]] = append(graph[e[0]], pair{e[1], 1})
	}

	dis := make([]int, n)
	for i := range dis {
		dis[i] = -1
	}
	vis := make([][2]bool, n)
	vis[0] = [2]bool{true, true}
	q := []pair{{0, 0}, {0, 1}}
	for level := 0; len(q) > 0; level++ {
		tmp := q
		q = nil
		for _, p := range tmp {
			x := p.x
			if dis[x] < 0 {
				dis[x] = level
			}
			for _, to := range graph[x] {
				if to.color != p.color && !vis[to.x][to.color] {
					vis[to.x][to.color] = true
					q = append(q, to)
				}
			}
		}
	}
	return dis
}
