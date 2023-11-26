package main

import (
	"bytes"
	"fmt"
)

func main() {
	str := entityParser("&amp; is an HTML entity but &ambassador; is not.")
	fmt.Println(str)
}

var html_entity = map[string]string{
	"&quot;":  "\"",
	"&apos;":  "'",
	"&amp;":   "&",
	"&gt;":    ">",
	"&lt;":    "<",
	"&frasl;": "/",
}

func entityParser(text string) string {
	var ans bytes.Buffer
	for i := 0; i < len(text); {
		r := text[i]
		if r == '&' {
			startIdx := i
			endIdx := i + 1
			for endIdx < len(text) && text[endIdx] != ';' {
				endIdx++
			}
			if endIdx < len(text) {
				endIdx++
			}
			if res, ok := html_entity[text[startIdx:endIdx]]; ok {
				ans.WriteString(res)
				i = endIdx
			} else {
				ans.WriteByte(r)
				i++
			}
		} else {
			ans.WriteByte(r)
			i++
		}
	}
	return ans.String()
}
