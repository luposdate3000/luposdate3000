#!/usr/bin/env -S JAVA_OPTS="-Xmx100g" kotlin
@file:Import("06_Turtle.kt")

import parser.Parser
import kotlin.random.Random

val targetSize = 8


val dictionarySet = mutableSetOf<String>()


var parser: Parser? = Parser(java.io.File(args[0]).inputStream())
parser!!.consumeTriple = { s, p, _ ->
    dictionarySet.add(s)
    dictionarySet.add(p)
}

parser!!.parserDefinedParse()
parser!!.close();

val dictionary = dictionarySet.toTypedArray()
val data = mutableMapOf<Int, MutableMap<Int, MutableSet<Int>>>()

parser = Parser(java.io.File(args[0]).inputStream())
parser!!.consumeTriple = { s, p, o ->
    val si = dictionary.indexOf(s)
    val pi = dictionary.indexOf(p)
    val oi = dictionary.indexOf(o)
    data.getOrPut(si, { mutableMapOf<Int, MutableSet<Int>>() }).getOrPut(pi, { mutableSetOf<Int>() }).add(oi)
}

parser!!.parserDefinedParse()
parser!!.close();
parser = null

fun decode(data: Array<String>, index: Int): String {
    if (index < 0) {
        return "_:${-index}"
    } else {
        return data[index]
    }
}


val possibleStartPoints = data.keys.toMutableSet()

fun getRandomQuery(): String {
    var hasWork = true
        val query = mutableSetOf<Triple<Int, Int, Int>>()
    while (hasWork) {
query.clear()
        hasWork = false

        var firstSubject = Random.nextInt(0, dictionary.size)
        while (!possibleStartPoints.contains(firstSubject)) {
            firstSubject = Random.nextInt(0, dictionary.size)
        }
        var variableCtr = -1
        val solution = mutableMapOf<Int, Int>()//variableId->dictionaryID
        solution[variableCtr--] = firstSubject

        while (query.size < targetSize) {
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
    for (q in query) {
        println(decode(dictionary, q.first) + " " + decode(dictionary, q.second) + " " + decode(dictionary, q.third))
    }
    return "randomQuery"
}









println(getRandomQuery())
