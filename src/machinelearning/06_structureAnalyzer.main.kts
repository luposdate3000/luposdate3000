#!/usr/bin/env -S JAVA_OPTS="-Xmx32g" kotlin
@file:Import("06_Turtle.kt")

import parser.Parser


// configuration -->>
val limitQueries = 10000
// configuration <<--

var ttypeBnode = 1
var ttypeIri = 2
var ttypeLiteral = 4
val parser = Parser(java.io.File(args[0]).inputStream())
val numberOfJoins = args[1].toInt()
val outputfolderName = args[2]
val outputfolder = java.io.File(outputfolderName)
outputfolder.mkdirs()
var dictionarySet = mutableSetOf<String>()
val fastQueryMode = args[3] == "fast"

class MyClass(val key: MutableSet<String>) {
    val variables = mutableMapOf<String, MyType>()
    val id = knownClassesIDMap3.size
    fun mergeWith(other: MyClass) {
        println("merging $key ${other.key}")
        key.addAll(other.key)
        val a = other.variables.keys - variables.keys
        val b = other.variables.keys - a
        val c = variables.keys - other.variables.keys
        for (s in a) {
            variables[s] = other.variables[s]!!
            variables[s]!!.minCount = 0
        }
        for (s in b) {
            variables[s]!!.mergeWith(other.variables[s]!!)
        }
        for (s in c) {
            variables[s]!!.minCount = 0
        }
    }

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
            val vv = variables[v.first]!!
            if (v.second.startsWith("_:")) {
                vv.addPossibleReference(v.second)
                vv.datatypes.add("<http://www.w3.org/ns/shacl#BlankNode>")
                vv.nodeKind = vv.nodeKind or ttypeBnode
            } else if ((v.second.startsWith("<") && v.second.endsWith(">"))) {
                vv.addPossibleReference(v.second)
                vv.datatypes.add("<http://www.w3.org/ns/shacl#IRI>")
                vv.nodeKind = vv.nodeKind or ttypeIri
            } else if (v.second.startsWith("\"") && v.second.endsWith(">") && v.second.contains("\"^^<")) {
                vv.datatypes.add(v.second.drop(v.second.lastIndexOf("\"^^<") + 3))
                vv.nodeKind = vv.nodeKind or ttypeLiteral
            } else if (v.second.startsWith("\"") && v.second.contains("\"@")) {
                vv.datatypes.add("<http://www.w3.org/1999/02/22-rdf-syntax-ns#langString>")
                vv.nodeKind = vv.nodeKind or ttypeLiteral
            } else if (v.second.startsWith("\"") && v.second.endsWith("\"")) {
                vv.datatypes.add("<http://www.w3.org/2001/XMLSchema#string>")
                vv.nodeKind = vv.nodeKind or ttypeLiteral
            } else {
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
    var datatypes = mutableSetOf<String>()
    var nodeKind = 0
    fun mergeWith(other: MyType) {
        if (other.minCount < minCount) {
            minCount = other.minCount
        }
        if (other.maxCount < maxCount) {
            maxCount = other.maxCount
        }
        referencedSubjectClasses.addAll(other.referencedSubjectClasses)
        possibleSubjectReferences.addAll(other.possibleSubjectReferences)
        datatypes.addAll(other.datatypes)
        nodeKind = nodeKind or other.nodeKind
    }

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
val knownClassesIDMap3 = mutableListOf<MyClass>()
val knownClassesMap3 = mutableMapOf<String, Int>()
val knownClassesRenamed3 = mutableListOf<Int>()
val subjectTypeMap = mutableMapOf<String, Int>()
fun getClazz(s: String): MyClass? = knownClassesMap3[s]?.let { knownClassesRenamed3[it]?.let { it2 -> knownClassesIDMap3[it2] } }
fun getClazz(id: Int): MyClass? = knownClassesRenamed3[id]?.let { it -> knownClassesIDMap3[it] }
fun getAllClazzes(): List<MyClass> = knownClassesRenamed3.toSet().map { knownClassesIDMap3[it]!! }

fun setClazzKeys(clazzID: Int, keys: Set<String>) {
    var clazz = getClazz(clazzID)!!
    clazz.key.addAll(keys)
    for (k in keys) {
        val t = knownClassesMap3[k]
        if (t == null) {
            knownClassesMap3[k] = clazzID
        } else {
            val otherClazz = getClazz(t)!!
            if (clazz.id != otherClazz.id) {
                clazz.mergeWith(otherClazz)
                for (k2 in otherClazz.key.map { getClazz(it)!!.id }) {
                    knownClassesRenamed3[k2] = clazz.id
                }
            }
        }
    }
}

fun findClassFor(values: List<Pair<String, String>>): Set<String> {
    // return values.map { it.first }.toSet()
    return values.filter { it.first == "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" }.map { it.second }.toSet()
}

fun consumeClass() {
    val keys = findClassFor(currentClass).toMutableSet()
    if (keys.size > 0) {
        val tt = keys.map { getClazz(it)?.id }.filterNotNull().toMutableSet()
        if (tt.size == 0) {
            val t = MyClass(keys)
            knownClassesIDMap3.add(t)
            knownClassesRenamed3.add(t.id)
            tt.add(t.id)
        }
        val key = tt.first()
        setClazzKeys(key, keys)
        val clazz = getClazz(key)!!
        clazz.mergeWith(currentClass)
        if (subjectTypeMap[currentSubject] != null) {
            TODO("currentSubject $currentSubject")
        }
        subjectTypeMap[currentSubject!!] = key
    }
}

fun checkAllPossibleReferences() {
    for (c in getAllClazzes()) {
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
        dictionarySet.add(o)
    } else {
        currentClass.add(p to o)
    }
}

parser.parserDefinedParse()
parser.close();
consumeClass()
checkAllPossibleReferences()
val dictionary = listOf("") + dictionarySet.toList()

println()
for (clazz in getAllClazzes()) {

    println("[]")
    for (kk in clazz.key) {
        println("    <http://www.w3.org/ns/shacl#targetClass> ${kk} ;")
    }
    for ((k, v) in clazz.variables) {
        println("    <http://www.w3.org/ns/shacl#property> [")
        println("        <http://www.w3.org/ns/shacl#path> $k ;")
        val possibleClasses = v.referencedSubjectClasses.map { getClazz(it)!!.key }.flatten().toSet()
        val datatypes = v.datatypes + v.possibleSubjectReferences.map {
            if (it.startsWith("_:")) {
                "<http://www.w3.org/ns/shacl#BlankNode>"
            } else {
                "<http://www.w3.org/ns/shacl#IRI>"
            }
        }
        for (c in possibleClasses) {
            println("        <http://www.w3.org/ns/shacl#class> $c ;")
        }
        if (possibleClasses.size == 0) {
            if (datatypes.size == 1) {
                println("        <http://www.w3.org/ns/shacl#datatype> ${datatypes.toList().first()} ;")
            }
        }
        if (v.nodeKind > 0 && v.nodeKind < 7) {
            val kind = arrayOf("", "<http://www.w3.org/ns/shacl#BlankNode>", "<http://www.w3.org/ns/shacl#IRI>", "<http://www.w3.org/ns/shacl#BlankNodeOrIRI>", "<http://www.w3.org/ns/shacl#Literal>", "<http://www.w3.org/ns/shacl#BlankNodeOrLiteral>", "<http://www.w3.org/ns/shacl#IRIOrLiteral>")[v.nodeKind]
            println("        <http://www.w3.org/ns/shacl#nodeKind> $kind ;")
        }
        println("        <http://www.w3.org/ns/shacl#minCount> ${v.minCount} ;")
        println("        <http://www.w3.org/ns/shacl#maxCount> ${v.maxCount} .")
        println("    ] ;")
    }
    println("    a <http://www.w3.org/ns/shacl#NodeShape> .")
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

for (clazz in getAllClazzes()) {
    val j = MyJoin()
    addToJoin(j, j.nextVariableName(clazz.id), clazz)
}

fun writeDownQueries(patternCount: Int) {
    val folder = java.io.File(outputfolder, "patterns_$patternCount")
    folder.mkdirs()
    var idx = 0
    val luposdate3000_query_params = StringBuilder()
    val python_ml_params = StringBuilder()
    val knownJoins2 = knownJoins.shuffled().take(limitQueries)
    for (query in knownJoins2) {
        luposdate3000_query_params.append(outputfolderName + "/patterns_$patternCount/q${idx.toString().padStart(4, '0')}.sparql;")
        python_ml_params.append(outputfolderName + "/patterns_$patternCount/q${idx.toString().padStart(4, '0')}.mlq;")
        java.io.File(folder, "q${idx.toString().padStart(4, '0')}.sparql").printWriter().use { out ->
            if (fastQueryMode) {
                out.println("SELECT (COUNT(*) as ?c) WHERE {")
            } else {
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
                    out.println("# ${query.variableFor(i)} -> ${getClazz(k)!!.key}")
                }
            }
        }
        java.io.File(folder, "q${idx.toString().padStart(4, '0')}.mlq").printWriter().use { out ->
            for (p in query.patterns) {
                fun mlqMapping(s: String): Int {
                    var c = query.extractVariableID(s)
                    if (c < 0) {
                        return dictionary.indexOf(s)
                    } else {
                        return -c - 1
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
    for (i in 1 until dictionary.size) {
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
                val clazz = getClazz(lastSubjectType2)!!
                addToJoin(j, lastPattern.first, clazz, lastPattern.second)
            }
        }
        var lastObjectType = j.extractVariableID(lastPattern.third)
        if (lastObjectType != -1) {
            val lastObjectType2 = j.variableClasses[lastObjectType]
            if (lastObjectType2 != -1) {
                val clazz = getClazz(lastObjectType2)!!
                addToJoin(j, lastPattern.third, clazz)
            }
        }
    }
    writeDownQueries(joinCount + 2)
}


