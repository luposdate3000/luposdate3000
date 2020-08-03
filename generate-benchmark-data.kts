#!/bin/kscript

var joinPartnerCount = 100L
var blockACount = 1
var blockBCount = 1

if (args.size > 0) {
    joinPartnerCount = args[0].toLong()
}
if (args.size > 1) {
    blockACount = args[1].toInt()
}
if (args.size > 2) {
    blockBCount = args[2].toInt()
}
for (i in 0L until joinPartnerCount) {
    for (j in 0 until blockACount) {
        println("_:s_${i.toString(16)} <p1> _:o1_${j}_${i.toString(16)} .")
    }
    for (j in 0 until blockBCount) {
        println("_:s_${i.toString(16)} <p2> _:o2_${j}_${i.toString(16)} .")
    }
}
