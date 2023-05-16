package main

import (
	"fmt"
	"math"
)

var statusChanges = map[string][]string{
	"start":  {"start", "signed", "number", "end"},
	"signed": {"end", "end", "number", "end"},
	"number": {"end", "end", "number", "end"},
	"end":    {"end", "end", "end", "end"},
}

type automata struct {
	status string
	sign   int
	ans    int
}

func (a *automata) getCol(c byte) int {
	if c == ' ' {
		return 0
	}
	if c == '+' || c == '-' {
		return 1
	}
	if c >= '0' && c <= '9' {
		return 2
	}
	return 3
}

func (a *automata) getChar(c byte) {
	a.status = statusChanges[a.status][a.getCol(c)]
	//fmt.Println(a.status)
	if a.status == "number" {
		a.ans = a.ans*10 + (int(c) - '0')
		if a.sign == 1 {
			a.ans = min(a.ans, math.MaxInt32)
		} else if a.sign == -1 { // 因为math.MaxInt32和-math.MinInt32并不相等（相差1）
			a.ans = min(a.ans, -math.MinInt32)
		}
	} else if a.status == "signed" {
		if c == '-' {
			a.sign = -1
		}
	}
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func myAtoi(s string) int {
	autoIns := &automata{status: "start", sign: 1, ans: 0}
	for i := 0; i < len(s); i++ {
		c := s[i]
		//fmt.Printf("%v\n", c)
		autoIns.getChar(c)
	}
	return autoIns.sign * autoIns.ans
}

func main() {
	ans := myAtoi("42")
	fmt.Println("ans = ", ans)
}
