import lupos.s00misc.File
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.Parallel
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.turtle.Turtle2Parser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithStringTriples
import lupos.s02buildSyntaxTree.turtle.TurtleScanner

enum class ImportMode {
    IMPORT_STRING,
    MERGE_INTERMEDIATE
}

fun helper_clean_string(s: String): String {
    var res: String = s
    while (true) {
        val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
        if (match == null) {
            break
        }
        val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
        res = res.replace(match.value, replacement)
    }
    return res
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = Parallel.runBlocking {
    var mode = ImportMode.valueOf(args[0])
    when (mode) {
        ImportMode.IMPORT_STRING -> {
            var cnt = 0
            val inputFileName = args[1]
            println("importing $inputFileName start")
            val inputFile = File(inputFileName)
            val dict = mutableMapOf<String, Int>()
            var dictCounter = 0
            var dictCounterByType = IntArray(ETripleComponentType.values().size)
            var dictionary2Offset = 0
            val iter = inputFile.readAsInputStream()
            val outputTriplesFile = File(inputFileName + ".triples")
            val outputDictionaryFile = File(inputFileName + ".dictionary")
            val outputDictionary2File = File(inputFileName + ".dictionary2")
            val outputDictionary2OffsetFile = File(inputFileName + ".dictionary2offset")
            val outputDictionaryStatFile = File(inputFileName + ".stat")
            val byteBuf = ByteArray(1)
            try {
                outputDictionaryFile.printWriter { outDictionary ->
                    outputDictionary2File.dataOutputStream { outDictionary2 ->
                        outputDictionary2OffsetFile.dataOutputStream { outDictionary2Offset ->
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
                                                outDictionary.println(tripleCleaned)
                                                val tmp = tripleCleaned.encodeToByteArray()
                                                byteBuf[0] = tripleType[i].ordinal.toByte()
                                                outDictionary2.write(byteBuf)
                                                outDictionary2.write(tmp)
if(dictionary2Offset>0){
                                                outDictionary2Offset.writeInt(dictionary2Offset)
}
                                                dictionary2Offset += tmp.size + 1
                                            }
                                        }
                                        cnt++
                                        if (cnt % 10000 == 0) {
                                            println("$cnt :: $dictCounter $dictionary2Offset")
                                        }
                                    }
                                }
                                x.turtleDoc()
				outDictionary2Offset.writeInt(dictionary2Offset)
                            }
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
        ImportMode.MERGE_INTERMEDIATE -> {
            throw Exception("outdated")
        }
    }
}
