#!/usr/bin/env kotlin
@file:Import("06_Turtle.kt")

import parser.Parser

val parser = Parser(java.io.File(args[0]).inputStream())
val numberOfJoins = args[1].toInt()
val outputfolderName=args[2]
val outputfolder = java.io.File(outputfolderName)
outputfolder.mkdirs()
var dictionarySet=mutableSetOf<String>()
val fastQueryMode=args[3]=="fast"

class MyClass(val key: Set<String>) {
    val variables = mutableMapOf<String, MyType>()
    val id = knownClassesIDMap.size
    fun mergeWith(values: List<Pair<String, String>>) {
        val counter = mutableMapOf<String, Int>()
        for (k in variables.keys) {
            counter[k] = 0
        }
        for (v in values) {
            if (counter[v.first] == null) {
                counter[v.first] = 1
            } else {
                counter[v.first] = counter[v.first]!! + 1
            }
        }
        for ((k, v) in counter) {
            var vv = variables[k]
            if (vv == null) {
                vv = MyType(v)
                variables[k] = vv
            }
            vv.updateMinMax(v)
        }
        for (v in values) {
            if (v.second.startsWith("_:") || (v.second.startsWith("<") && v.second.endsWith(">"))) {
                variables[v.first]!!.addPossibleReference(v.second)
            }else if(v.second.startsWith("\"")&&v.second.endsWith(">")&&v.second.contains("\"^^<")){
variables[v.first]!!.datatypes.add(v.second.drop(v.second.lastIndexOf("\"^^<")+3))
}else{
TODO(v.second)
}
        }
    }

    fun checkPossibleReferences(): Boolean {
        var res = false
        for (v in variables.values) {
            res = v.checkPossibleReferences() || res
        }
        return res
    }
}

class MyType(count: Int) {
    var minCount = count
    var maxCount = count
    var referencedSubjectClasses = mutableSetOf<Int>()
    var possibleSubjectReferences = mutableSetOf<String>()
    var datatypes=mutableSetOf<String>()
    fun addPossibleReference(s: String) {
        var x = subjectTypeMap[s]
        if (x != null) {
            referencedSubjectClasses.add(x)
        } else {
            possibleSubjectReferences.add(s)
        }
    }

    fun checkPossibleReferences(): Boolean {
        var tmp = mutableSetOf<String>()
        tmp.addAll(possibleSubjectReferences)
        possibleSubjectReferences.clear()
        for (s in tmp) {
            addPossibleReference(s)
        }
        return possibleSubjectReferences.size > 0
    }

    fun updateMinMax(count: Int) {
        if (minCount > count) {
            minCount = count
        }
        if (maxCount < count) {
            maxCount = count
        }
    }
}

var currentClass = mutableListOf<Pair<String, String>>()
var currentSubject: String? = null
val knownClassesIDMap = mutableListOf<MyClass>()
val knownClassesMap = mutableMapOf<Set<String>, MyClass>()
val subjectTypeMap = mutableMapOf<String, Int>()


fun findClassFor(values: List<Pair<String, String>>): Set<String> {
    // return currentClass.map { it.first }.toSet()
    return currentClass.filter { it.first == "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" }.map { it.second }.toSet()
}

fun consumeClass() {
    val key = findClassFor(currentClass)
    if (key.size > 0) {
        var t: MyClass? = knownClassesMap[key]
        if (t == null) {
            t = MyClass(key)
            knownClassesMap[key] = t
            knownClassesIDMap.add(t)
        }
        t.mergeWith(currentClass)
        if (subjectTypeMap[currentSubject] != null) {
            TODO("currentSubject $currentSubject")
        }
        subjectTypeMap[currentSubject!!] = t.id
    }
}

fun checkAllPossibleReferences() {
    for (c in knownClassesIDMap) {
        c.checkPossibleReferences()
    }
}


parser.consumeTriple = { s, p, o ->
dictionarySet.add(p)
    if (currentSubject != s) {
        if (currentSubject != null) {
            consumeClass()
        }
        currentClass.clear()
        currentSubject = s
    }
    if (p.startsWith("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_") && p.endsWith(">")) {
        currentClass.add("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_1>" to o)
    } else {
        currentClass.add(p to o)
    }
}

parser.parserDefinedParse()
parser.close();
consumeClass()
checkAllPossibleReferences()
val dictionary=listOf("")+dictionarySet.toList()

println()
for (clazz in knownClassesIDMap) {
    println("[]")
    println("    sh:targetClass ${clazz.key.first()} ;")
    for ((k, v) in clazz.variables) {
        println("    sh:property [")
        println("        sh:path $k ;")
        println("        sh:minCount ${v.minCount} ;")
        println("        sh:maxCount ${v.maxCount} ;")
        val possibleClasses=v.referencedSubjectClasses.map { knownClassesIDMap[it].key }.flatten().toSet()
        for(c in possibleClasses){
            println("        sh:class $c ;")
        }
val datatypes=v.datatypes+v.possibleSubjectReferences.map{
if(it.startsWith("_:")){
"sh:BNODE"
}else{
"sh:IRI"
}
}
for(dd in datatypes){
println("        sh:datatype $dd ;")
}
        println("    ] ;")
    }
    println("    a sh:NodeShape .")
}
println()

class MyJoin {
    val patterns = mutableListOf<Triple<String, String, String>>()
    var variableClasses = mutableListOf<Int>()
    fun myClone(): MyJoin {
        val res = MyJoin()
        res.patterns.addAll(patterns)
        res.variableClasses.addAll(variableClasses)
        return res
    }

    fun nextVariableName(clazz: Int): String {
        variableClasses.add(clazz)
        return "?v${variableClasses.size - 1}"
    }

    fun variableFor(i: Int): String = "?v${i}"
    fun extractVariableID(s: String): Int {
        return if (s.startsWith("?v")) {
            s.drop(2).toInt()
        } else {
            return -1
        }
    }
}

var knownJoins = mutableListOf<MyJoin>()


fun addToJoin(jj: MyJoin, subjectName: String, clazz: MyClass, lastPredicate: String? = null) {
    var flag = lastPredicate == null
    for ((predicate, objects) in clazz.variables) {
        if (flag) {
            when (predicate) {
                "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" -> {
                    val j = jj.myClone()
                    j.patterns.add(Triple(subjectName, predicate, clazz.key.first()))
                    knownJoins.add(j)
                }
                "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_1>" -> {
                    if (objects.referencedSubjectClasses.size == 0) {
                        val j = jj.myClone()
                        j.patterns.add(Triple(subjectName, j.nextVariableName(-1), j.nextVariableName(-1)))
                        knownJoins.add(j)
                    } else {
                        for (objClazz in objects.referencedSubjectClasses) {
                            val j = jj.myClone()
                            j.patterns.add(Triple(subjectName, j.nextVariableName(-1), j.nextVariableName(objClazz)))
                            knownJoins.add(j)
                        }
                    }
                }
                else -> {
                    if (objects.referencedSubjectClasses.size == 0) {
                        val j = jj.myClone()
                        j.patterns.add(Triple(subjectName, predicate, j.nextVariableName(-1)))
                        knownJoins.add(j)
                    } else {
                        for (objClazz in objects.referencedSubjectClasses) {
                            val j = jj.myClone()
                            j.patterns.add(Triple(subjectName, predicate, j.nextVariableName(objClazz)))
                            knownJoins.add(j)
                        }
                    }
                }
            }
        }
        if (predicate == lastPredicate) {
            flag = true
        }
    }
}

for (clazz in knownClassesIDMap) {
    val j = MyJoin()
    addToJoin(j, j.nextVariableName(clazz.id), clazz)
}

fun writeDownQueries(patternCount: Int) {
    val folder = java.io.File(outputfolder, "patterns_$patternCount")
    folder.mkdirs()
    var idx = 0
val luposdate3000_query_params=StringBuilder()
val python_ml_params=StringBuilder()
    for (query in knownJoins) {
luposdate3000_query_params.append(outputfolderName+"/patterns_$patternCount/q${idx.toString().padStart(4, '0')}.sparql;")
python_ml_params.append(outputfolderName+"/patterns_$patternCount/q${idx.toString().padStart(4, '0')}.mlq;")
       java.io.File(folder, "q${idx.toString().padStart(4, '0')}.sparql").printWriter().use { out ->
if(fastQueryMode){
            out.println("SELECT (COUNT(*) as ?c) WHERE {")
}else{
            out.println("SELECT ${List(query.variableClasses.size) { query.variableFor(it) }.joinToString(" ")} WHERE {")
}
            for (p in query.patterns) {
                out.println("  ${p.first} ${p.second} ${p.third} . ")
            }
            out.println("}")
            for (i in 0 until query.variableClasses.size) {
                var k = query.variableClasses[i]
                if (k < 0) {
                    out.println("# ${query.variableFor(i)} -> ANY")
                } else {
                    out.println("# ${query.variableFor(i)} -> ${knownClassesIDMap[k].key}")
                }
            }
        }
java.io.File(folder, "q${idx.toString().padStart(4, '0')}.mlq").printWriter().use { out ->
for (p in query.patterns) {
fun mlqMapping(s:String):Int{
var c=query.extractVariableID(s)
if(c<0){
return dictionary.indexOf(s)
}else{
return -c-1
}
}
out.print(mlqMapping(p.first))
out.print(",")
out.print(mlqMapping(p.second))
out.print(",")
out.print(mlqMapping(p.third))
out.print(";")
}
}
        idx++
    }
java.io.File(outputfolder, "luposdate3000_query_params_$patternCount").printWriter().use { out ->
out.print(luposdate3000_query_params.toString().dropLast(1))
}
java.io.File(outputfolder, "python_ml_params_$patternCount").printWriter().use { out ->
out.print(python_ml_params.toString().dropLast(1))
}
}

java.io.File(outputfolder, "dictionary").printWriter().use { out ->
for(i in 1 until dictionary.size){
out.println("$i ${dictionary[i]}")
}
}


var knownJoinsPrev = mutableListOf<MyJoin>()
writeDownQueries(1)
for (joinCount in 0 until numberOfJoins) {
    knownJoinsPrev = knownJoins
    knownJoins = mutableListOf()
    for (j in knownJoinsPrev) {
        var lastPattern = j.patterns.last()
        var lastSubjectType = j.extractVariableID(lastPattern.first)
        if (lastSubjectType != -1) {
            val lastSubjectType2 = j.variableClasses[lastSubjectType]
            if (lastSubjectType2 != -1) {
                val clazz = knownClassesIDMap[lastSubjectType2]
                addToJoin(j, lastPattern.first, clazz, lastPattern.second)
            }
        }
        var lastObjectType = j.extractVariableID(lastPattern.third)
        if (lastObjectType != -1) {
            val lastObjectType2 = j.variableClasses[lastObjectType]
            if (lastObjectType2 != -1) {
                val clazz = knownClassesIDMap[lastObjectType2]
                addToJoin(j, lastPattern.third, clazz)
            }
        }
    }
    writeDownQueries(joinCount + 2)
}


