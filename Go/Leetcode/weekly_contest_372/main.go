package main

import (
	"fmt"
)

func main() {
	ans := maximumXorProduct(4, 7, 3)
	fmt.Println(ans)
}

// Pro1
func findMinimumOperations(s1 string, s2 string, s3 string) int {
	ans := 0
	for s1 != s2 || s1 != s3 {
		if len(s1) >= len(s2) && len(s1) >= len(s3) {
			s1 = s1[:len(s1)-1]
		} else if len(s2) >= len(s1) && len(s2) >= len(s3) {
			s2 = s2[:len(s2)-1]
		} else {
			s3 = s3[:len(s3)-1]
		}
		ans++
	}
	if len(s1) > 0 {
		return ans
	}
	return -1
}

// Pro2
func minimumSteps(s string) int64 {
	//计算每个1右边有多少个0就行
	n := len(s)
	zeroCount := 0
	ans := int64(0)
	for i := n - 1; i >= 0; i-- {
		if s[i] == '0' {
			zeroCount++
		} else {
			ans += int64(zeroCount)
		}
	}
	return ans
}

// Pro3
func maximumXorProduct(a int64, b int64, n int) int {
	if a < b {
		a, b = b, a
	}
	// fmt.Println(a, b)
	// a >= b
	const MOD = 1e9 + 7
	bitCount_A := make([]int, 50)
	bitCount_B := make([]int, 50)
	for i := 0; i < 50; i++ {
		if (a>>i)&0x1 == 1 {
			bitCount_A[i]++
		}
		if (b>>i)&0x1 == 1 {
			bitCount_B[i]++
		}
	}
	if a == b {
		//乘积最大
		for i := n - 1; i >= 0; i-- {
			if bitCount_A[i] == 0 && bitCount_B[i] == 0 {
				bitCount_A[i] = 1
				bitCount_B[i] = 1
			}
		}
	} else {
		//a更大
		firstA := 0
		for i := 49; i >= 0; i-- {
			if bitCount_A[i] == 1 && bitCount_B[i] == 0 {
				firstA = i
				break
			}
		}
		// fmt.Println(firstA)
		for i := n - 1; i >= 0; i-- {
			if bitCount_A[i] == 0 && bitCount_B[i] == 0 {
				bitCount_A[i] = 1
				bitCount_B[i] = 1
			} else {
				if bitCount_A[i] == 1 && bitCount_B[i] == 0 && i != firstA {
					bitCount_A[i] = 0
					bitCount_B[i] = 1
				}
			}
		}
	}
	newA := int64(0)
	newB := int64(0)
	for i := 49; i >= 0; i-- {
		if bitCount_A[i] == 1 {
			newA += 1 << i
		}
		if bitCount_B[i] == 1 {
			newB += 1 << i
		}
	}
	//fmt.Println(newA)
	//fmt.Println(newB)
	newA = newA % MOD
	newB = newB % MOD
	return int((newA * newB) % MOD)
}

// Pro4
