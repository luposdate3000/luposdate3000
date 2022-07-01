#!/usr/bin/env -S JAVA_OPTS="-Xmx100g" kotlin
@file:Import("00_Turtle.kt")

import parser.Parser
import kotlin.random.Random

val dictionarySet = mutableSetOf<String>()

fun cleanString(s: String): String {
if(s.startsWith("_:")|| (s.startsWith("<")&&s.endsWith(">"))){
return s.replace("[\\s,]".toRegex(), "")
}else{
return "0"
}
}

var ctr=0L
var parser = Parser(java.io.File(args[0]).inputStream())
parser!!.consumeTriple = { s, p, o ->
    dictionarySet.add(cleanString(s))
    dictionarySet.add(cleanString(p))
    dictionarySet.add(cleanString(o))
if(ctr%10000L==0L){
println("read a $ctr")
}
ctr++
}
parser!!.parserDefinedParse()
parser!!.close();

val dictionary = dictionarySet.toTypedArray()
dictionary.sort()

java.io.File(args[1] + ".dictionary").printWriter().use { out ->
    for (i in 0 until dictionary.size) {
        out.println(dictionary[i])
    }
}
ctr=0L
parser = Parser(java.io.File(args[0]).inputStream())
java.io.File(args[1] + ".data").printWriter().use { out ->
    parser!!.consumeTriple = { s, p, o ->
        val si = dictionary.binarySearch(cleanString(s))
        val pi = dictionary.binarySearch(cleanString(p))
        val oi = dictionary.binarySearch(cleanString(o))
        out.println(""+si + "," + pi + "," + oi)
if(ctr%10000L==0L){
println("read b $ctr")
}
ctr++
    }
    parser!!.parserDefinedParse()
    parser!!.close();
}
