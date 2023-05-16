package main

func main() {

}

func isValid(s string) bool {
	stk := []byte{}
	for i, _ := range s {
		stk = append(stk, s[i])
		if len(stk) >= 3 && string(stk[len(stk)-3:]) == "abc" {
			stk = stk[:len(stk)-3]
		}
	}
	return len(stk) == 0
}
