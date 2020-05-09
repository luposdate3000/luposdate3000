import dev.fuzzit.javafuzz.core.AbstractFuzzTarget
import dev.fuzzit.javafuzz.core.Fuzzer
import java.time.Duration
import java.time.Instant
import lupos.*
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.executeBinaryTest
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.*


fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    endpointServer = EndpointServerImpl("localhost")
    P2P.start(null)
    mapOf(/*return*/
/*return*/testDictionaryVarName to "DictionaryVarName.txt",
            /*return*/testDictionaryValue to "DictionaryValue.txt"
    ).forEach { (k, v) ->
        java.io.File("resources/$v").forEachLine {
            k.add(it)
        }
    }
    val query = Query()
    testDictionaryValue.forEach {
        try {
            val tmp = ValueDefinition.create(it)
            if (testDictionaryValueTyped[ValueToID(tmp)] == null) {
                testDictionaryValueTyped[ValueToID(tmp)] = ThreadSafeMutableList()
            }
            testDictionaryValueTyped[ValueToID(tmp)]!!.add(it!!)
        } catch (e: Throwable) {
            testDictionaryValueTyped[ValueEnum.ValueSimpleLiteral]!!.add("\"" + it!! + "\"")
        }
    }
    query.commit()
    if (args.size > 0) {
        JenaRequest.db = args[0]
        JenaRequest.dbwascreated = true
    }
    val fuzzer = Fuzzer(FuzzInstance(), "javafuzz/${JenaRequest.db}")
    fuzzer.start()
}

//https://gitlab.com/akihe/radamsa
//https://github.com/fuzzitdev/javafuzz
class FuzzInstance() : AbstractFuzzTarget() {
    @JvmField
    var timepoint = Instant.now()

    override fun fuzz(data: ByteArray) = CoroutinesHelper.runBlock {
        val timepointNext = Instant.now()
        val elapsed = Duration.between(timepoint, timepointNext)
        timepoint = timepointNext
        if (data.size >= 4) {
            val input = DynamicByteArray(data)
            executeBinaryTest(input!!)
        }
        val timepointNext2 = Instant.now()
        val elapsed2 = Duration.between(timepoint, timepointNext2)
        timepoint = timepointNext2
    }
}
