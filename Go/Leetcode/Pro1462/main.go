package main

func main() {

}

func checkIfPrerequisite(numCourses int, prerequisites [][]int, queries [][]int) []bool {
	g := makeGraph(numCourses)
	for _, prerequisite := range prerequisites {
		g.addEdge(prerequisite[0], prerequisite[1])
	}
	visited := make([]bool, numCourses)
	for i := 0; i < numCourses; i++ {
		g.dfs(i, visited)
	}
	ans := make([]bool, len(queries))
	for i, query := range queries {
		ans[i] = g.isPre[query[0]][query[1]]
	}
	return ans
}

type graph struct {
	edges     [][]int
	inDegrees []int
	isPre     [][]bool
}

func makeGraph(numCourses int) *graph {
	g := graph{
		edges:     make([][]int, numCourses),
		inDegrees: make([]int, numCourses),
		isPre:     make([][]bool, numCourses),
	}
	for i := range g.isPre {
		g.isPre[i] = make([]bool, numCourses)
	}
	return &g
}

func (g *graph) addEdge(from, to int) {
	g.edges[from] = append(g.edges[from], to)
	g.inDegrees[to]++
}

func (g *graph) getRoot() []int {
	ans := make([]int, 0)
	for i := 0; i < len(g.edges); i++ {
		if g.inDegrees[i] == 0 {
			ans = append(ans, i)
		}
	}
	return ans
}

func (g *graph) dfs(vertex int, visited []bool) {
	if visited[vertex] {
		return
	}
	visited[vertex] = true
	for _, neigh := range g.edges[vertex] {
		g.dfs(neigh, visited)
		g.isPre[vertex][neigh] = true
		for i := 0; i < len(g.isPre); i++ {
			g.isPre[vertex][i] = g.isPre[vertex][i] || g.isPre[neigh][i]
		}
	}
}
