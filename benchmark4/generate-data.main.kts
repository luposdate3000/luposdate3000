#!/usr/bin/env kotlin
@file:Import("../src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("../src/luposdate3000_shared_inline/src/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("../src/luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:CompilerOptions("-Xmulti-platform")


import lupos.s00misc.Platform
import java.lang.ProcessBuilder.Redirect
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

enum class ETripleComponentType {
    IRI,
    BLANK_NODE,
    STRING,
    INTEGER,
    DECIMAL,
    DOUBLE,
    BOOLEAN,
    STRING_TYPED,
    STRING_LANG,
}
//generateTriples("${Platform.getBenchmarkHome()}/luposdate-testdata/bench_4", 2097152, 16,1)

fun generateTriples(folderName: String, count:Int,trash:Int,join:Int): Int {
    val byteBuf = ByteArray(1)
File(folderName).mkdirs()
    var outN3: PrintWriter = File(folderName + "/intermediate.n3").printWriter()
    var outIntermediateDictionary = DataOutputStream(BufferedOutputStream(FileOutputStream(folderName + "/intermediate.n3.dictionary")))
    var outIntermediateTriples = DataOutputStream(BufferedOutputStream(FileOutputStream(folderName + "/intermediate.n3.triples")))
    var outIntermediateDictionaryStat = File(folderName + "/intermediate.n3.stat")
    var dictCounterBnode = 0
    var dictCounterIri = 0
    var outIntermediateTriplesStatCounter = 0
    var idMapping = mutableMapOf<String, Int>()
    var idMappingCounter = 0
    fun writeToDict(s: String): Int {
        if (idMapping[s] != null) {
            return idMapping[s]!!
        }
        if (s.startsWith("_:")) {
            dictCounterBnode++
            byteBuf[0] = ETripleComponentType.BLANK_NODE.ordinal.toByte()
        } else {
            dictCounterIri++
            byteBuf[0] = ETripleComponentType.IRI.ordinal.toByte()
        }
        val tmp = s.encodeToByteArray()
        outIntermediateDictionary.writeInt(tmp.size)
        outIntermediateDictionary.write(byteBuf)
        outIntermediateDictionary.write(tmp)
        idMapping[s] = idMappingCounter++
        return idMapping[s]!!
    }

    fun appendTriple(s: String, p: String, o: String) {
        val si = writeToDict(s)
        val pi = writeToDict(p)
        val oi = writeToDict(o)
        outN3.println("$s $p $o .")
        outIntermediateTriples.writeInt(si)
        outIntermediateTriples.writeInt(pi)
        outIntermediateTriples.writeInt(oi)
        outIntermediateTriplesStatCounter++
    }

for(i in 0 until count){
for(j in 0 until join){
appendTriple("_:a${i}","<a>","_:${j}")
}
for(k in 0 until join){
appendTriple("_:b${i}","<b>","_:${k}")
}
for(l in 0 until join){
appendTriple("_:c${i}","<c>","_:${l}")
}
for(j in 0 until trash){
appendTriple("_:a${i}_${j}","<a>","_:${j}")
}
for(k in 0 until trash){
appendTriple("_:b${i}_${k}","<b>","_:${k}")
}
for(l in 0 until trash){
appendTriple("_:c${i}_${l}","<c>","_:${l}")
}
}

    outIntermediateTriples.close()
File("$folderName/intermediate.n3.partitions").printWriter().use { out ->
for(i in listOf(2,4,8)){
out.println("PSO,1,$i")
out.println("PSO,2,$i")
}
}

    outIntermediateDictionaryStat.printWriter().use { out ->
        out.println("total=${dictCounterBnode + dictCounterIri}")
        for (t in ETripleComponentType.values()) {
            if (t == ETripleComponentType.BLANK_NODE) {
                out.println("$t=$dictCounterBnode")
            } else if (t == ETripleComponentType.IRI) {
                out.println("$t=$dictCounterIri")
            } else {
                out.println("$t=0")
            }
        }
    }
    outN3.close()
    outIntermediateDictionary.close()
    outIntermediateTriples.close()
    return outIntermediateTriplesStatCounter
}
