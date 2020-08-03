#!/bin/kscript

var joinPartnerCount = 100L
var blockACount = 1 //if there is a match, than x elements of A match in a row
var blockBCount = 1 //if there is a match, than x elements of A match in a row
var trashACount = 0 //for each match-block there are x elements without join-partner
var trashBCount = 0 //for each match-block there are x elements without join-partner

//successful match-blocks and trash-blocks are interleaved in the data (if the data is interpreted in the same ordering as generated)

if (args.size > 0) {
    joinPartnerCount = args[0].toLong()
}
if (args.size > 1) {
    blockACount = args[1].toInt()
}
if (args.size > 2) {
    blockBCount = args[2].toInt()
}
if (args.size > 3) {
    trashACount = args[3].toInt()
}
if (args.size > 4) {
    trashBCount = args[4].toInt()
}
var counter=0
for (i in 0L until joinPartnerCount) {
    for (j in 0 until blockACount) {
        println("_:s_${counter.toString(16)} <p1> _:o1_${j}_${counter.toString(16)} .")
	//number of new variables :: blockACount + 1
    }
    for (j in 0 until blockBCount) {
        println("_:s_${counter.toString(16)} <p2> _:o2_${j}_${counter.toString(16)} .")
	//number of new variables :: blockBCount
    }
    counter++
    for (j in 0 until trashACount) {
        println("_:s_${counter.toString(16)} <p1> _:o1_t_${counter.toString(16)} .")
        counter++
	//number of new variables :: trashACount * 2
    }
    for (j in 0 until trashBCount) {
        println("_:s_${counter.toString(16)} <p2> _:o2_t_${counter.toString(16)} .")
        counter++
	//number of new variables :: trashACount * 2
    }
}
