import kotlin.time.DurationUnit
import kotlin.time.TimeSource.Monotonic
import kotlinx.coroutines.runBlocking
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithStringTriples
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToString
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.convertToOPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s16network.HttpEndpoint
import lupos.s16network.ServerCommunicationSend

enum class ImportMode {
    IMPORT_STRING,
    MERGE_INTERMEDIATE
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = runBlocking {
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
                lcit = LexerCharIterator(data)
            } else {
                val data = inputFile.readAsCharIterator()
                lcit = LexerCharIterator(data)
            }
            val tit = TurtleScanner(lcit)
            val ltit = LookAheadTokenIterator(tit, 3)
            val outputTriplesFile = File(inputFileName + ".triples")
            outputTriplesFile.dataOutputStream { out ->
                try {
                    TurtleParserWithStringTriples({ s, p, o ->
                        out.writeInt(dict.getOrCreate(s))
                        out.writeInt(dict.getOrCreate(p))
                        out.writeInt(dict.getOrCreate(o))
                        cnt++
                    }, ltit).turtleDoc()
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
