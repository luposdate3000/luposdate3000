import lupos.s00misc.ETripleComponentType
import lupos.s00misc.File
import lupos.s00misc.Parallel
import lupos.s02buildSyntaxTree.turtle.Turtle2Parser
import lupos.s16network.LuposdateEndpoint

enum class ImportMode {
    IMPORT_STRING,
    MERGE_INTERMEDIATE
}

fun helper_clean_string(s: String): String {
    var res: String = s
    while (true) {
        val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res) ?: break
        val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
        res = res.replace(match.value, replacement)
    }
    return res
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = Parallel.runBlocking {
    println("importing args ${args.map { it }}")
    LuposdateEndpoint.initialize()
    var cnt = 0
    val inputFileName = args[0]
    println("importing $inputFileName start")
    val inputFile = File(inputFileName)
    val dict = mutableMapOf<String, Int>()
    var dictCounter = 0
    val dictCounterByType = IntArray(ETripleComponentType.values().size)
    var DictionaryOffset = 0
    val iter = inputFile.readAsInputStream()
    val outputTriplesFile = File("$inputFileName.triples")
    val outputDictionaryFile = File("$inputFileName.dictionary")
    val outputDictionaryOffsetFile = File("$inputFileName.dictionaryoffset")
    val outputDictionaryStatFile = File("$inputFileName.stat")
    val byteBuf = ByteArray(1)
    try {
        outputDictionaryFile.dataOutputStream { outDictionary ->
            outputDictionaryOffsetFile.dataOutputStream { outDictionaryOffset ->
                outputTriplesFile.dataOutputStream { outTriples ->
                    val x = object : Turtle2Parser(iter) {
                        override fun onTriple(triple: Array<String>, tripleType: Array<ETripleComponentType>) {
                            for (i in 0 until 3) {
                                val tripleCleaned = helper_clean_string(triple[i])
                                val v = dict[tripleCleaned]
                                if (v != null) {
                                    outTriples.writeInt(v)
                                } else {
                                    val v2 = dictCounter++
                                    dictCounterByType[tripleType[i].ordinal]++
                                    dict[tripleCleaned] = v2
                                    outTriples.writeInt(v2)
                                    var tripleCleaned2 = tripleCleaned
                                    if (tripleType[i] == ETripleComponentType.IRI) {
                                        tripleCleaned2 = tripleCleaned.substring(1, tripleCleaned.length - 1)
                                    }
                                    val tmp = tripleCleaned2.encodeToByteArray()
                                    byteBuf[0] = tripleType[i].ordinal.toByte()
                                    outDictionary.write(byteBuf)
                                    outDictionary.write(tmp)
                                    if (DictionaryOffset > 0) {
                                        outDictionaryOffset.writeInt(DictionaryOffset)//TODO this is too small ... integer-overflow
                                    }
                                    DictionaryOffset += tmp.size + 1
                                }
                            }
                            cnt++
                            if (cnt % 10000 == 0) {
                                println("$cnt :: $dictCounter $DictionaryOffset")
                            }
                        }
                    }
                    x.turtleDoc()
                    outDictionaryOffset.writeInt(DictionaryOffset)
                }
            }
        }
    } catch (e: lupos.s02buildSyntaxTree.ParseError) {
        println("error in file '$inputFileName'")
        throw e
    }
    outputDictionaryStatFile.printWriter { out ->
        out.println("total=$dictCounter")
        for (t in ETripleComponentType.values()) {
            out.println("$t=${dictCounterByType[t.ordinal]}")
        }
    }
    println("importing $inputFileName finish with $cnt triples")
}
