import kotlin.time.DurationUnit
import kotlin.time.TimeSource.Monotonic
import kotlinx.coroutines.runBlocking
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.EBenchmark
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
import lupos.s00misc.MyMapStringIntPatriciaTrie
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
    IMPORT_STRING
}

@UseExperimental(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun main(args: Array<String>) = runBlocking {
    var mode = ImportMode.valueOf(args[0])
    when (mode) {
        ImportMode.IMPORT_STRING -> {
            var cnt = 0
            val inputFileName = args[1]
            println("importing $inputFileName start")
            val outputTriplesFileName = inputFileName + ".triples"
            val outputDictionaryFileName = inputFileName + ".dictionary"
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
            val outputTriplesFile = File(outputTriplesFileName)
            outputTriplesFile.dataOutputStreamSuspend { out ->
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
            val outputDictionaryFile = File(outputDictionaryFileName)
            outputDictionaryFile.printWriter { out ->
                for (i in 0 until dict.size) {
                    out.println(dict[i])
                }
            }
            println("importing $inputFileName finish with $cnt triples")
        }
    }
}
