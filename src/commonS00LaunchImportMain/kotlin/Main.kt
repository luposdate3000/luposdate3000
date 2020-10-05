import lupos.s00misc.File
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
            var dictionary2Offset = 0
            val iter = inputFile.readAsInputStream()
            val outputTriplesFile = File(inputFileName + ".triples")
            val outputDictionaryFile = File(inputFileName + ".dictionary")
            val outputDictionary2File = File(inputFileName + ".dictionary2")
            val outputDictionary2OffsetFile = File(inputFileName + ".dictionary2offset")
            val outputDictionaryStatFile = File(inputFileName + ".stat")
            try {
                outputDictionaryFile.printWriter { outDictionary ->
                    outputDictionary2File.dataOutputStream { outDictionary2 ->
                        outputDictionary2OffsetFile.dataOutputStream { outDictionary2Offset ->
                            outputTriplesFile.dataOutputStream { outTriples ->
                                val x = object : Turtle2Parser(iter) {
                                    override fun onTriple(triple: Array<String>) {
                                        for (i in 0 until 3) {
                                            val v = dict[triple[i]]
                                            if (v != null) {
                                                outTriples.writeInt(v)
                                            } else {
                                                val v2 = dictCounter++
                                                dict[triple[i]] = v2
                                                outTriples.writeInt(v2)
                                                outDictionary.println(triple[i])
                                                val tmp = triple[i].encodeToByteArray()
                                                outDictionary2.write(tmp)
                                                outDictionary2Offset.writeInt(dictionary2Offset)
                                                dictionary2Offset += tmp.size
                                            }
                                        }
                                        cnt++
                                        if (cnt % 10000 == 0) {
                                            println("$cnt :: $dictCounter $dictionary2Offset")
                                        }
                                    }
                                }
                                x.turtleDoc()
                            }
                        }
                    }
                }
            } catch (e: lupos.s02buildSyntaxTree.ParseError) {
                println("error in file '$inputFileName'")
                throw e
            }
            outputDictionaryStatFile.printWriter { out ->
                out.print(dictCounter)
            }
            println("importing $inputFileName finish with $cnt triples")
        }
        ImportMode.MERGE_INTERMEDIATE -> {
            val outDirectory = args[1].substring(0, args[1].lastIndexOf("/")) + "/out"
            val tmp = args.toMutableList()
            tmp.removeAt(0)
            File(outDirectory).mkdirs()
            val outputFileName = File("").createTempFile("out_", ".n3", outDirectory)
            println("merging $tmp into $outputFileName")
            val dict = MyMapStringIntPatriciaTrieDouble()
            val outputTriplesFile = File(outputFileName + ".triples")
            outputTriplesFile.dataOutputStream { out ->
                for (argIndex in 1 until args.size) {
                    val fileName = args[argIndex]
                    val fileTriples = File(fileName + ".triples")
                    val fileDictionary = File(fileName + ".dictionary")
                    val fileDictionaryStat = File(fileName + ".stat")
                    val size = fileDictionaryStat.readAsString().toInt()
                    val mapping = IntArray(size)
                    var idx = 0
                    fileDictionary.forEachLine {
                        mapping[idx++] = dict.getOrCreate(it)
                    }
                    var cnt = fileTriples.length().toInt() / 12
                    fileTriples.dataInputStream {
                        for (i in 0 until cnt) {
                            var s = it.readInt()
                            var p = it.readInt()
                            var o = it.readInt()
                            out.writeInt(mapping[s])
                            out.writeInt(mapping[p])
                            out.writeInt(mapping[o])
                        }
                    }
                }
            }
            val outputDictionaryFile = File(outputFileName + ".dictionary")
            outputDictionaryFile.printWriter { out ->
                for (i in 0 until dict.size) {
                    out.println(dict[i])
                }
            }
            val outputDictionaryStatFile = File(outputFileName + ".stat")
            outputDictionaryStatFile.printWriter { out ->
                out.print(dict.size)
            }
            println("merging $tmp into $outputFileName finish")
        }
    }
}
