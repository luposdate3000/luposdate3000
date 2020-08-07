#!/bin/kscript

var targetNumberOfTriples = 100L
var numberOfPredicates = 2
var blockCount = 1 //if there is a match, than x elements match in a row
var trashCount = 0 //if there is no match, than x elements dont match in a row

val preventMultiplesOfList = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19)

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

//predefining unused values, to prevent mixing unwanted "new" values, which would break the target patterns
for (i in 0 until 100) {
    println("_:a1 <unused> <o${i.toString(16)}> .")
}
for (i in 0 until numberOfPredicates) {
    println("_:a1 <unused> <p${i}> .")
}

var counter = 0L
loop@ while (targetNumberOfTriples > 0) {
    for (p in 0 until numberOfPredicates) {
        for (j in 0 until blockCount) {
            println("_:s${counter.toString(16)} <p${p}> <o${((j + counter) % 100).toString(16)}> .")
        }
    }
    targetNumberOfTriples -= numberOfPredicates * blockCount
    counter++
    if (trashCount > 0) {
        var trashcounter = 1
        for (p in 0 until numberOfPredicates) {
            for (j in 0 until trashCount) {
                if (targetNumberOfTriples <= 0) {
                    break@loop
                }
                println("_:s${counter.toString(16)} <p${p}> <o${((j + counter) % 100).toString(16)}> .")
                targetNumberOfTriples--
                counter++
                trashcounter++
            }
        }
        var flag = true
        trashloop@ while (flag) {
            flag = false
            for (i in preventMultiplesOfList) {
                if (trashcounter % i == 0) {
                    flag = true
                    println("_:s${counter.toString(16)} <unused> <o${(counter % 100).toString(16)}> .")
                    counter++
                    trashcounter++
                }
            }
        }
    }
}
