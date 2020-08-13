#!/bin/kscript

import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.io.File
import java.io.PrintWriter

var targetNumberOfTriples = 100L
var numberOfPredicates = 2
var blockCount = 1 //if there is a match, than x elements match in a row
var trashCount = 0 //if there is no match, than x elements dont match in a row
val preventMultiplesOfList = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19)
val object_counter = 100

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
var folderName = "/tmp/luposdate"
if (args.size > 4) {
    folderName = args[4]
}
File(folderName).mkdirs()
var outN3: PrintWriter = File(folderName + "/data0.n3").printWriter()
var outBnodes: PrintWriter = File(folderName + "/bnodes.txt").printWriter()
var outIntermediateDictionary: PrintWriter = File(folderName + "/intermediate.dictionary").printWriter()
var outIntermediateTriples = DataOutputStream(BufferedOutputStream(FileOutputStream(folderName + "/intermediate.triples")))
var outIntermediateDictionaryStat = File(folderName + "/intermediate.stat").printWriter()
var outIntermediateDictionaryStatCounter = 0
var outIntermediateTriplesStatCounter = 0

//predefining unused values, to prevent mixing unwanted "new" values, which would break the target patterns
outIntermediateDictionary.println("_:_a1")
outIntermediateDictionaryStatCounter++
outIntermediateDictionary.println("<unused>")
outIntermediateDictionaryStatCounter++
outBnodes.println("_:a1")
val s_offset = 2 + object_counter + numberOfPredicates
val p_offset = 2 + object_counter
val o_offset = 2
for (i in 0 until object_counter) {
    outN3.println("_:a1 <unused> <o${i.toString(16)}> .")
    outIntermediateDictionary.println("<o${i.toString(16)}>")
    outIntermediateDictionaryStatCounter++
    outIntermediateTriples.writeInt(0)
    outIntermediateTriples.writeInt(1)
    outIntermediateTriples.writeInt(o_offset + i)
outIntermediateTriplesStatCounter++
}
for (i in 0 until numberOfPredicates) {
    outN3.println("_:a1 <unused> <p${i}> .")
    outIntermediateDictionary.println("<p${i}>")
    outIntermediateDictionaryStatCounter++
    outIntermediateTriples.writeInt(0)
    outIntermediateTriples.writeInt(1)
    outIntermediateTriples.writeInt(p_offset + i)
outIntermediateTriplesStatCounter++
}

var counter = 0
loop@ while (targetNumberOfTriples > 0) {
    outIntermediateDictionary.println("_:_s${counter.toString(16)}")
    outIntermediateDictionaryStatCounter++
    outBnodes.println("_:s${counter.toString(16)}")
    for (p in 0 until numberOfPredicates) {
        for (j in 0 until blockCount) {
            outN3.println("_:s${counter.toString(16)} <p${p}> <o${((j + counter) % object_counter).toString(16)}> .")
            outIntermediateTriples.writeInt(s_offset + counter)
            outIntermediateTriples.writeInt(p_offset + p)
            outIntermediateTriples.writeInt(o_offset + ((j + counter) % object_counter))
outIntermediateTriplesStatCounter++
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
                outN3.println("_:s${counter.toString(16)} <p${p}> <o${((j + counter) % object_counter).toString(16)}> .")
                outBnodes.println("_:s${counter.toString(16)}")
                outIntermediateDictionary.println("_:_s${counter.toString(16)}")
                outIntermediateDictionaryStatCounter++
                outIntermediateTriples.writeInt(s_offset + counter)
                outIntermediateTriples.writeInt(p_offset + p)
                outIntermediateTriples.writeInt(o_offset + ((j + counter) % object_counter))
outIntermediateTriplesStatCounter++
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
                    outBnodes.println("_:s${counter.toString(16)}")
                    outIntermediateDictionary.println("_:_s${counter.toString(16)}")
                    outIntermediateDictionaryStatCounter++
                    outIntermediateTriples.writeInt(s_offset + counter)
                    outIntermediateTriples.writeInt(1)
                    outIntermediateTriples.writeInt(o_offset + (counter % object_counter))
outIntermediateTriplesStatCounter++
                    outN3.println("_:s${counter.toString(16)} <unused> <o${(counter % object_counter).toString(16)}> .")
                    counter++
                    trashcounter++
                }
            }
        }
    }
}
outIntermediateDictionaryStat.print(outIntermediateDictionaryStatCounter)
outN3.close()
outBnodes.close()
outIntermediateDictionary.close()
outIntermediateTriples.close()
outIntermediateDictionaryStat.close()
println(outIntermediateTriplesStatCounter)
