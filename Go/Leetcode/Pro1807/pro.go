package Pro1807

import (
	"fmt"
	"strings"
)

func evaluate(s string, knowledge [][]string) string {
	dict := map[string]string{}
	for _, kd := range knowledge {
		dict[kd[0]] = kd[1]
	}

	ans := &strings.Builder{}
	start := -1
	for i, c := range s {
		if c == '(' {
			start = i
		} else if c == ')' {
			if t, ok := dict[s[start+1:i]]; ok {
				ans.WriteString(t)
			} else {
				ans.WriteByte('?')
			}
			start = -1
		} else if start < 0 {
			ans.WriteRune(c)
		}
	}
	return ans.String()
}

func Main() {
	str := "(name)is(age)yearsold"
	var knowledge [][]string
	knowledge = append(knowledge, []string{"name", "Bob"})
	knowledge = append(knowledge, []string{"age", "eighteen"})
	ans := evaluate(str, knowledge)
	fmt.Println(ans)
}
