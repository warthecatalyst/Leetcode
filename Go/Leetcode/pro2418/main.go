package main

import "sort"

func main() {

}

func sortPeople(names []string, heights []int) []string {
	type elem struct {
		name   string
		height int
	}
	elems := make([]elem, 0)

	for i := range names {
		elems = append(elems, elem{
			name:   names[i],
			height: heights[i],
		})
	}
	sort.Slice(elems, func(i, j int) bool {
		return elems[i].height > elems[j].height
	})
	ans := make([]string, 0)
	for _, elem := range elems {
		ans = append(ans, elem.name)
	}
	return ans
}
