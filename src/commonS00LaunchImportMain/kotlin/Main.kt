import lupos.s00misc.File
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.Parallel
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
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
            val dict = MyMapStringIntPatriciaTrieDouble()
            val lcit: LexerCharIterator
            if (inputFile.length() < Int.MAX_VALUE) {
                val data = inputFile.readAsString()
                println("read as string data :: ${data.substring(9, 100)}")
                lcit = LexerCharIterator(data)
            } else {
                val data = inputFile.readAsCharIterator()
                println("read as iterator :: $inputFileName")
                lcit = LexerCharIterator(data)
            }
            val tit = TurtleScanner(lcit)
            val ltit = LookAheadTokenIterator(tit, 3)
            val outputTriplesFile = File(inputFileName + ".triples")
            outputTriplesFile.dataOutputStream { out ->
                try {
                    val x = object : TurtleParserWithStringTriples() {
                        suspend override fun consume_triple(s: String, p: String, o: String) {
                            out.writeInt(dict.getOrCreate(s))
                            out.writeInt(dict.getOrCreate(p))
                            out.writeInt(dict.getOrCreate(o))
                            cnt++
                            println("$cnt $s $p $o")
                        }
                    }
                    x.ltit = ltit
                    x.turtleDoc()
                } catch (e: lupos.s02buildSyntaxTree.ParseError) {
                    println("error in file '$inputFileName'")
                    throw e
                }
            }
            val outputDictionaryFile = File(inputFileName + ".dictionary")
            outputDictionaryFile.printWriter { out ->
                for (i in 0 until dict.size) {
                    out.println(dict[i])
                }
            }
            val outputDictionaryStatFile = File(inputFileName + ".stat")
            outputDictionaryStatFile.printWriter { out ->
                out.print(dict.size)
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
