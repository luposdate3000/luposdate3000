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

var counter = -1L
var counters = IntArray(numberOfPredicates)

loop@ while (targetNumberOfTriples > 0) {
    counter++
    for (p in 0 until numberOfPredicates) {
        counters[p] = 1 - counters[p]
        if (counters[p] == 1) {
            for (j in 0 until blockCount) {
                println("_:s_${counter.toString(16)} <p${p}> _:o1_${j}_${counter.toString(16)} .")
            }
            targetNumberOfTriples -= blockCount
        } else {
            for (j in 0 until trashCount) {
                println("_:s_${counter.toString(16)} <p${p}> _:o1_${j}_${counter.toString(16)} .")
                counter++
            }
            targetNumberOfTriples -= trashCount
            continue@loop
        }
    }
}
