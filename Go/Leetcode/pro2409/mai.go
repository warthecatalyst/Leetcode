package main

import (
	"strconv"
	"strings"
)

var daysOfMonth = []int{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}

func countDaysTogether(arriveAlice string, leaveAlice string, arriveBob string, leaveBob string) int {
	startDate := getMinMaxDate(arriveAlice, arriveBob, false)
	endDate := getMinMaxDate(leaveAlice, leaveBob, true)
	ans := endDate - startDate + 1
	if ans <= 0 {
		return 0
	}
	return ans
}

func getMinMaxDate(date1, date2 string, isMin bool) int {
	date1Str := strings.Split(date1, "-")
	date2Str := strings.Split(date2, "-")
	month1, _ := strconv.Atoi(date1Str[0])
	day1, _ := strconv.Atoi(date1Str[1])
	month2, _ := strconv.Atoi(date2Str[0])
	day2, _ := strconv.Atoi(date2Str[1])
	for i := 0; i < month1-1; i++ {
		day1 += daysOfMonth[i]
	}
	for i := 0; i < month2-1; i++ {
		day2 += daysOfMonth[i]
	}
	if isMin {
		if day1 < day2 {
			return day1
		} else {
			return day2
		}
	} else {
		if day1 > day2 {
			return day1
		} else {
			return day2
		}
	}
}
