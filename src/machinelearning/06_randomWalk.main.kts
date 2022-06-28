#!/usr/bin/env -S JAVA_OPTS="-Xmx100g" kotlin
@file:Import("06_Turtle.kt")

import parser.Parser
import kotlin.random.Random

val queryCount = 5

val dictionarySet = mutableSetOf<String>("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_")
val data = mutableMapOf<Int, MutableMap<Int, MutableSet<Int>>>()

val resultDictionary = mutableListOf<String>()


var parser: Parser? = Parser(java.io.File(args[0]).inputStream())
val numberOfJoinPatterns = args[1].toInt()
val outputfolderName = args[2]
val outputfolder = java.io.File(outputfolderName)
outputfolder.mkdirs()
var begin = System.nanoTime()

var triple = 0L
parser!!.consumeTriple = { s, p, _ ->
    dictionarySet.add(s)
if(!p.startsWith("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_")){
    dictionarySet.add(p)
}
    triple++
    if (triple % 1000L == 0L) {
        println("loaded $triple triples(1) in ${((System.nanoTime() - begin) / 1000000L).toDouble() / 1000.0} s")
    }
}
println("loaded data step 1")
parser!!.parserDefinedParse()
parser!!.close();
triple = 0L
begin = System.nanoTime()
val dictionary = dictionarySet.toTypedArray()
dictionary.sort()
parser = Parser(java.io.File(args[0]).inputStream())
parser!!.consumeTriple = { s, p, o ->
    val si = dictionary.binarySearch(s)
    val pi = if(p.startsWith("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_")) dictionary.binarySearch("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_") else dictionary.binarySearch(p)
    val oi = dictionary.binarySearch(o)
    data.getOrPut(si, { mutableMapOf<Int, MutableSet<Int>>() }).getOrPut(pi, { mutableSetOf<Int>() }).add(oi)
    triple++
    if (triple % 1000L == 0L) {
        println("loaded $triple triples(2) in ${((System.nanoTime() - begin) / 1000000L).toDouble() / 1000.0} s")
    }
}
parser!!.parserDefinedParse()
parser!!.close();
val possibleStartPoints = data.keys.toMutableSet()
parser = null

println("loaded data step 2")

fun decode(index: Int): String {
    if (index < 0) {
        return "_:${-index}"
    } else {
        return dictionary[index]
    }
}

fun resultIndex(value: String): Int {
    var res = resultDictionary.indexOf(value)
    if (res < 0) {
        res = resultDictionary.size
        resultDictionary.add(value)
    }
    return res
}

fun mapVariable(value: Int, off: Int): Int = if (value < 0) value + off else resultIndex(decode(value))
fun dictMap(value: Int, dict: MutableMap<Int, Int>): Int {
    if (value < 0) {
        var res = dict[value]
        if (res == null) {
            val rr = -dict.size - 1
            dict[value] = rr
            return rr
        } else {
            return res!!
        }
    } else {
        return value
    }
}

fun getRandomQuery(): String {
    var hasWork = true
    val query = mutableSetOf<Triple<Int, Int, Int>>()
    var variableCtr = -1
    while (hasWork) {
        hasWork = false
        query.clear()
        variableCtr = -1
        var firstSubject = Random.nextInt(0, dictionary.size)
        while (!possibleStartPoints.contains(firstSubject)) {
            firstSubject = Random.nextInt(0, dictionary.size)
        }
        val solution = mutableMapOf<Int, Int>()//variableId->dictionaryID
        solution[variableCtr--] = firstSubject
        while (query.size < numberOfJoinPatterns) {
            var possibleChoices = mutableSetOf<Pair<Int, Int>>()
            for ((varname, subject) in solution) {
                if (subject >= 0) {
                    val predicates = data[subject]
                    if (predicates != null) {
                        for (predicate in predicates.keys) {
                            possibleChoices.add(varname to predicate)
                        }
                    }
                }
            }
            for (q in query) {
                possibleChoices.remove(q.first to q.second)
            }
            if (possibleChoices.size == 0) {
                possibleStartPoints.removeAll(solution.values)
                hasWork = true
                break
            }
            val possibleChoicesList = possibleChoices.toTypedArray()
            val (subject, predicate) = possibleChoicesList[Random.nextInt(0, possibleChoicesList.size)]
            val possibleObjects = data[solution[subject]!!]!![predicate]!!.toTypedArray()
            val obj = possibleObjects[Random.nextInt(0, possibleObjects.size)]
            val objectID = variableCtr--
            solution[objectID] = obj
            query.add(Triple(subject, predicate, objectID))
        }
    }
    val res = mutableListOf<Int>()

    val query2 = mutableSetOf<Triple<Int, Int, Int>>()
    val scores = mutableMapOf<Int, Int>()
    for (q in query) {
        val t = Triple(mapVariable(q.first, variableCtr * 3), mapVariable(q.second, variableCtr * 3), mapVariable(q.third, variableCtr * 3))
        query2.add(t)
        var score = scores[t.first]
        if (score == null) {
            score = 0
        }
        score += t.second
        scores[t.first] = score
    }
    val scoresInverse = scores.toList().sortedBy { -it.second }
    val mapping = mutableMapOf<Int, Int>()
    val query3 = mutableListOf<Triple<Int, Int, Int>>()
    for (ss in scoresInverse) {
        dictMap(ss.first, mapping)
    }
    val distinctJoinVariables = (-1 - mapping.size)
    for (ss in scoresInverse) {
        val tmp = mutableListOf<Triple<Int, Int, Int>>()
        for (q in query2) {
            if (q.first == ss.first) {
                tmp.add(q)
            }
        }
        query2.removeAll(tmp)
        val tmp2 = tmp.sortedWith(compareBy({ it.second }))
        for (tt in tmp2) {
            query3.add(Triple(dictMap(tt.first, mapping), dictMap(tt.second, mapping), dictMap(tt.third, mapping)))
        }
    }

    println()
    for (q in query3) {
        res.add(q.first)
        val predicate = resultDictionary[q.second]
        var used_predicate = ""
        if (predicate.startsWith("_:")) {
            used_predicate = "_:" + (-variableCtr--)
            res.add(variableCtr--)
        } else if (predicate.startsWith("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_")) {
            used_predicate = "_:" + (-variableCtr--)
            res.add(variableCtr--)
        } else {
            res.add(resultIndex(predicate))
            used_predicate = predicate
        }
        res.add(q.third)
//        if (q.third >= distinctJoinVariables) {
//            println("_:" + (-q.first) + " " + used_predicate + " _:" + (-q.third))
//        }
    }
    return res.joinToString()
}





java.io.File(outputfolder, "queries").printWriter().use { out ->
    for (i in 0 until queryCount) {
        out.println(getRandomQuery().replace("\\s".toRegex(), ""))
    }
}
java.io.File(outputfolder, "dictionary").printWriter().use { out ->
    for (i in 0 until resultDictionary.size) {
        out.println("$i ${resultDictionary[i]}")
    }
}
