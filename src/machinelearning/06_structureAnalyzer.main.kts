#!/usr/bin/env -S JAVA_OPTS="-Xmx100g" kotlin
@file:Import("06_Turtle.kt")

import parser.Parser
import kotlin.math.log
import kotlin.math.floor
import kotlin.random.Random
import kotlin.math.exp

// configuration -->>
val limitQueries = 5000
// configuration <<--

var ttypeBnode = 1
var ttypeIri = 2
var ttypeLiteral = 4
var parser: Parser? = Parser(java.io.File(args[0]).inputStream())
val numberOfJoinPatterns = args[1].toInt()
val outputfolderName = args[2]
val outputfolder = java.io.File(outputfolderName)
val fastQueryMode = args[3] == "fast"
var currentClass = mutableListOf<Pair<String, String>>()
var currentSubject: String? = null

val knownClassesIDMap3 = mutableListOf<MyClass>()
val knownClassesMap3 = mutableMapOf<String, Int>()
val subjectTypeMap = mutableMapOf<String, Int>()
var idMappings = IntArray(1)

val knownClassesMemberMap = mutableMapOf<Set<String>, Int>()

var ctr = 0
val dictionary = mutableListOf("")

outputfolder.mkdirs()

class MyClass(val key: MutableSet<String>) {
    val variables = mutableMapOf<String, MyType>()
    var id = knownClassesIDMap3.size
    var ids = mutableSetOf(id)
    fun deduplicateProperties() {
        val typeMap = mutableMapOf<Triple<Int, Int, Int>, MutableSet<String>>()
        for ((k, v) in variables) {
            if (v.referencedSubjectClasses.size == 0) {
                val tt = Triple(v.minCount, v.maxCount, v.nodeKind)
                var t = typeMap[tt]
                if (t == null) {
                    t = mutableSetOf()
                    typeMap[tt] = t
                }
                t.add(k)
            }
        }
        for (kk in typeMap.values) {
            val ll = kk.toMutableList()
            val ff = ll.removeFirst()
            val v = variables[ff]!!
            v.paths.addAll(kk)
            for (l in ll) {
                v.datatypes.addAll(variables[l]!!.datatypes)
            }
            for (l in ll) {
                variables.remove(l)
            }
        }
    }

    fun clearType() {
        id = idMappings[id]
        ids.clear()
        ids.add(id)
        for (v in variables.values) {
            v.clearType()
        }
    }

    fun mergeWith(other: MyClass) {
        key.addAll(other.key)
        ids.addAll(other.ids)
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
                vv.paths.add(k)
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
    val paths = mutableSetOf<String>()
    fun clearType() {
        val tmp = referencedSubjectClasses.map { idMappings[it] }.toSet()
        referencedSubjectClasses.clear()
        referencedSubjectClasses.addAll(tmp)
        for (k in possibleSubjectReferences) {
            var x = subjectTypeMap[k]
            if (x != null) {
                referencedSubjectClasses.add(idMappings[x])
            }
        }
        possibleSubjectReferences.clear()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MyType) {
            return false
        }
        return referencedSubjectClasses == other.referencedSubjectClasses && datatypes == other.datatypes && nodeKind == other.nodeKind
    }

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


fun getClazz(s: String) = knownClassesMap3[s]?.let { knownClassesIDMap3[it] }
fun getClazz(id: Int) = knownClassesIDMap3[id]
fun getAllClazzes() = knownClassesIDMap3.filterIndexed { index, it -> it.id == index }

fun setClazzKeys(clazzID: Int, keys: Set<String>) {
    var clazz = getClazz(clazzID)
    if (clazz.key.containsAll(keys)) {
        clazz.key.addAll(keys)
        for (k in keys) {
            val t = knownClassesMap3[k]
            if (t != null) {
                val otherClazz = getClazz(t)
                if (clazz.id != otherClazz.id) {
                    clazz.mergeWith(otherClazz)
                    for (id in otherClazz.ids) {
                        knownClassesIDMap3[id] = clazz
                    }
                }
            }
        }
    }
}

fun checkEqualClazz(clazzID: Int, keys: Set<String>) {
    var clazz = getClazz(clazzID)
    var t = knownClassesMemberMap[keys]
    if (t != null) {
        val otherClazz = getClazz(t!!)
        if (clazz.id != otherClazz.id) {
            clazz.mergeWith(otherClazz)
            for (id in otherClazz.ids) {
                knownClassesIDMap3[id] = clazz
            }
            for (k in clazz.key) {
                knownClassesMap3[k] = clazz.id
            }
        }
    }
    knownClassesMemberMap[clazz.variables.keys] = clazzID
    knownClassesMemberMap[keys] = clazzID
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
            tt.add(t.id)
            for (k in keys) {
                knownClassesMap3[k] = t.id
            }
        }
        val key = tt.first()
        setClazzKeys(key, keys)
        val clazz = getClazz(key)
        clazz.mergeWith(currentClass)
        checkEqualClazz(key, keys)
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

parser!!.consumeTriple = { s, p, o ->
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


parser!!.parserDefinedParse()
parser!!.close();
parser = null
consumeClass()
checkAllPossibleReferences()


val validIDs = knownClassesIDMap3.map { it.id }.toSet().toList()
for (i in 0 until validIDs.size) {
    val clazz = knownClassesIDMap3[validIDs[i]]
    clazz.deduplicateProperties()
}


//grep targetClass yago1.shacl -A1 | grep property | wc -l .....  69612

var changed = true
loop@ while (changed) {
    changed = false
    val validIDs = knownClassesIDMap3.map { it.id }.toSet().toList()
    idMappings = IntArray(knownClassesIDMap3.size) { -1 }
    for (i in 0 until validIDs.size) {
        val clazz = knownClassesIDMap3[validIDs[i]]
        for (id in clazz.ids) {
            idMappings[id] = i
        }
    }
    for (i in validIDs) {
        knownClassesIDMap3[i].clearType()
    }
    var knownClassesIDMap3Tmp = mutableListOf<MyClass>()
    for (i in 0 until validIDs.size) {
        val t = knownClassesIDMap3[validIDs[i]]
        knownClassesIDMap3Tmp.add(t)
    }
    knownClassesIDMap3.clear()
    knownClassesIDMap3.addAll(knownClassesIDMap3Tmp)
    subjectTypeMap.clear()
    var deletedIDs = mutableSetOf<Int>()
    for (i in 0 until validIDs.size) {
        if (deletedIDs.size > 1000) {
            continue@loop
        }
        if (deletedIDs.contains(i)) {
            continue
        }
        for (j in i + 1 until validIDs.size) {
            if (deletedIDs.contains(j)) {
                continue
            }
            val clazzA = knownClassesIDMap3[i]
            val clazzB = knownClassesIDMap3[j]
            if (clazzA.variables.size == clazzB.variables.size) {
                if (clazzA.variables.keys == clazzB.variables.keys) {
                    if (clazzA.variables == clazzB.variables) {
                        deletedIDs.add(clazzB.id)
                        changed = true
                        clazzA.mergeWith(clazzB)
                        for (id in clazzB.ids) {
                            knownClassesIDMap3[id] = clazzA
                        }
                        for (k in clazzB.key) {
                            knownClassesMap3[k] = clazzA.id
                        }
                    }
                }
            }
        }
    }
}

//grep targetClass yago1.shacl -A1 | grep property | wc -l .....  9006


println()
for (clazz in getAllClazzes()) {

    println("[]")
    for (kk in clazz.key) {
        println("    <http://www.w3.org/ns/shacl#targetClass> ${kk} ;")
    }
    for (v in clazz.variables.values) {
        println("    <http://www.w3.org/ns/shacl#property> [")
        for (k in v.paths) {
            println("        <http://www.w3.org/ns/shacl#path> $k ;")
        }
        val possibleClasses = v.referencedSubjectClasses.map { getClazz(it).key.first() }.toSet()
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


fun addToDictionary(s: String) {
    if (!s.startsWith("?") && !dictionary.contains(s)) {
        dictionary.add(s)
    }
}


fun <K> ReservoirSample(input: Iterator<K>, output: Array<K>) {
    var j = 0
    var i = 0
    while (i < output.size && input.hasNext()) {
        output[i] = input.next()
        j++
        i++
    }
    var W = exp(log(Random.nextDouble(), kotlin.math.E) / output.size)
    while (input.hasNext()) {
        i = i + floor(log(Random.nextDouble(), kotlin.math.E) / log(1 - W, kotlin.math.E)).toInt() + 1
        j++
if(i<output.size*100){
break//my shortcut to prevent extreme calculations for nearly no change
}
        while (j < i && input.hasNext()) {
            input.next()
            j++
        }
        if (input.hasNext()) {
            output[Random.nextInt(0, output.size)] = input.next()
            W = W * exp(log(Random.nextDouble(), kotlin.math.E) / output.size)
        }
    }
}

fun addToJoin(jj: MyJoin, subjectName: String, clazz: MyClass, lastPredicate: String?, depth: Int): Sequence<MyJoin> = sequence {
    var flag = lastPredicate == null
    for ((predicate, objects) in clazz.variables) {
        if (flag) {
            when (predicate) {
                "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" -> {
                    val j = jj.myClone()
                    j.patterns.add(Triple(subjectName, predicate, clazz.key.first()))
                    yieldAll(joinSequenceIteratorRecurse(j, depth + 1))
                }
                "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_1>" -> {
                    if (objects.referencedSubjectClasses.size == 0) {
                        val j = jj.myClone()
                        j.patterns.add(Triple(subjectName, j.nextVariableName(-1), j.nextVariableName(-1)))
                        yieldAll(joinSequenceIteratorRecurse(j, depth + 1))
                    } else {
                        for (objClazz in objects.referencedSubjectClasses) {
                            val j = jj.myClone()
                            j.patterns.add(Triple(subjectName, j.nextVariableName(-1), j.nextVariableName(objClazz)))
                            yieldAll(joinSequenceIteratorRecurse(j, depth + 1))
                        }
                    }
                }
                else -> {
                    if (objects.referencedSubjectClasses.size == 0) {
                        val j = jj.myClone()
                        j.patterns.add(Triple(subjectName, predicate, j.nextVariableName(-1)))
                        yieldAll(joinSequenceIteratorRecurse(j, depth + 1))
                    } else {
                        for (objClazz in objects.referencedSubjectClasses) {
                            val j = jj.myClone()
                            j.patterns.add(Triple(subjectName, predicate, j.nextVariableName(objClazz)))
                            yieldAll(joinSequenceIteratorRecurse(j, depth + 1))
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

fun joinSequenceIteratorRecurse(j: MyJoin, depth: Int): Sequence<MyJoin> = sequence {
    if (depth == numberOfJoinPatterns) {
        yield(j)
    } else {
        var lastPattern = j.patterns.last()
        var lastSubjectType = j.extractVariableID(lastPattern.first)
        if (lastSubjectType != -1) {
            val lastSubjectType2 = j.variableClasses[lastSubjectType]
            if (lastSubjectType2 != -1) {
                val clazz = getClazz(lastSubjectType2)
                yieldAll(addToJoin(j, lastPattern.first, clazz, lastPattern.second, depth))
            }
        }
        var lastObjectType = j.extractVariableID(lastPattern.third)
        if (lastObjectType != -1) {
            val lastObjectType2 = j.variableClasses[lastObjectType]
            if (lastObjectType2 != -1) {
                val clazz = getClazz(lastObjectType2)
                yieldAll(addToJoin(j, lastPattern.third, clazz, null, depth))
            }
        }
    }
}


fun joinSequenceIterator() = sequence {
    for (clazz in getAllClazzes()) {
        val j = MyJoin()
        yieldAll(addToJoin(j, j.nextVariableName(clazz.id), clazz, null, 0))
    }
}

val folder = outputfolder
folder.mkdirs()
var idx = 0
val luposdate3000_query_params = StringBuilder()
val python_ml_params = StringBuilder()
val knownJoins = Array<MyJoin>(limitQueries) { MyJoin() }
ReservoirSample(joinSequenceIterator().iterator(), knownJoins)
for (query in knownJoins) {
if(query.patterns.size!=numberOfJoinPatterns){
continue
}
    luposdate3000_query_params.append(outputfolderName + "/q${idx.toString().padStart(4, '0')}.sparql;")
    python_ml_params.append(outputfolderName + "/q${idx.toString().padStart(4, '0')}.mlq;")
    java.io.File(folder, "q${idx.toString().padStart(4, '0')}.sparql").printWriter().use { out ->
        if (fastQueryMode) {
            out.println("SELECT (COUNT(*) as ?c) WHERE {")
        } else {
            out.println("SELECT ${List(query.variableClasses.size) { query.variableFor(it) }.joinToString(" ")} WHERE {")
        }
        for (p in query.patterns) {
            addToDictionary(p.first)
            addToDictionary(p.second)
            addToDictionary(p.third)
            out.println("  ${p.first} ${p.second} ${p.third} . ")
        }
        out.println("}")
        for (i in 0 until query.variableClasses.size) {
            var k = query.variableClasses[i]
            if (k < 0) {
                out.println("# ${query.variableFor(i)} -> ANY")
            } else {
                out.println("# ${query.variableFor(i)} -> ${getClazz(k).key}")
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
java.io.File(outputfolder, "luposdate3000_query_params").printWriter().use { out ->
    out.print(luposdate3000_query_params.toString().dropLast(1))
}
java.io.File(outputfolder, "python_ml_params").printWriter().use { out ->
    out.print(python_ml_params.toString().dropLast(1))
}
java.io.File(outputfolder, "dictionary").printWriter().use { out ->
    for (i in 1 until dictionary.size) {
        out.println("$i ${dictionary[i]}")
    }
}
