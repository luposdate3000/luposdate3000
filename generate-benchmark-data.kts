#!/bin/kscript

var targetNumberOfTriples = 100L
var numberOfPredicates = 2
var blockCount = 1 //if there is a match, than x elements match in a row
var trashCount = 0 //if there is no match, than x elements dont match in a row

//successful match-blocks and trash-blocks are interleaved in the data (if the data is interpreted in the same ordering as generated)

if (args.size > 0) {
    targetNumberOfTriples = args[0].toLong()
}
if (args.size > 1) {
    numberOfPredicates = args[1].toInt()
}
if (args.size > 2) {
    blockCount = args[2].toInt()
}
if (args.size > 3) {
    trashCount = args[3].toInt()
}

var counter = 0L
loop@ while (targetNumberOfTriples > 0) {
	for (p in 0 until numberOfPredicates) {
		for (j in 0 until blockCount) {
			println("_:s${counter.toString(16)} <p${p}> <o${((j+counter)%100).toString(16)}> .")
		}
	}
	counter++
	for (p in 0 until numberOfPredicates) {
		for (j in 0 until trashCount) {
			println("<s${counter.toString(16)}> <p${p}> <o${((j+counter)%100).toString(16)}> .")
			counter++
		}
	}
	targetNumberOfTriples-=numberOfPredicates*(blockCount+trashCount)
}
