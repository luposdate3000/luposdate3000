package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
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
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.sparql1_1.*
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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAddition
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRLANG
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRSTARTS
import lupos.s04arithmetikOperators.multiinput.AOPDivision
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPAggregationAVG
import lupos.s04arithmetikOperators.singleinput.AOPAggregationCOUNT
import lupos.s04arithmetikOperators.singleinput.AOPAggregationMAX
import lupos.s04arithmetikOperators.singleinput.AOPAggregationMIN
import lupos.s04arithmetikOperators.singleinput.AOPAggregationSAMPLE
import lupos.s04arithmetikOperators.singleinput.AOPAggregationSUM
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallABS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBNODE1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCEIL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallFLOOR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIRI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsIri
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsLITERAL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsNUMERIC
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallROUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSECONDS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRLEN
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTIMEZONE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTZ
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallUCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallURI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallYEAR
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorDistinct
import lupos.s04logicalOperators.iterator.ColumnIteratorMergeSort
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.ColumnIteratorStore1
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3c
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s06buildOperatorGraph.*
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.multiinput.POPJoinMergeSingleColumn
import lupos.s09physicalOperators.multiinput.POPJoinWithStore
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPValuesImportBase
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s16network.*
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.convertToOPBase
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.endpointServer
import lupos.s14endpoint.EndpointServerImpl
import lupos.s15tripleStoreDistributed.DistributedGraph
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.SparqlTestSuite

enum class TestCase(val action: suspend (TestRandom) -> Unit) {
    ETripleStore(TripleStoreLocalTest::invoke),
    EColumnIteratorRepeatValue(ColumnIteratorRepeatValueTest::invoke),
    EColumnIteratorMultiValue(ColumnIteratorMultiValueTest::invoke),
    EColumnIteratorRepeatIterator(ColumnIteratorRepeatIteratorTest::invoke),
    EColumnIteratorMultiIterator(ColumnIteratorMultiIteratorTest::invoke),
    EColumnIteratorChildIterator(ColumnIteratorChildIteratorTest::invoke),
    EColumnIteratorQueue(ColumnIteratorQueueTest::invoke),
    EPOPValues(POPValuesTest::invoke),
    EPOPBind(POPBindTest::invoke),
    EPOPJoin(POPJoinTest::invoke)
//    Sparql(::executeBinaryTest),
}

enum class ValueEnum {
    ValueBnode,
    ValueBoolean,
    ValueDateTime,
    ValueDecimal,
    ValueDouble,
    ValueInteger,
    ValueIri,
    ValueLanguageTaggedLiteral,
    ValueSimpleLiteral,
    ValueTypedLiteral,
    ValueUndef
}

fun ValueToID(v: ValueDefinition): ValueEnum {
    return when (v) {
        is ValueBnode -> {
            ValueEnum.ValueBoolean
        }
        is ValueBoolean -> {
            ValueEnum.ValueBoolean
        }
        is ValueDateTime -> {
            ValueEnum.ValueDateTime
        }
        is ValueDecimal -> {
            ValueEnum.ValueDecimal
        }
        is ValueDouble -> {
            ValueEnum.ValueDouble
        }
        is ValueInteger -> {
            ValueEnum.ValueInteger
        }
        is ValueIri -> {
            ValueEnum.ValueIri
        }
        is ValueLanguageTaggedLiteral -> {
            ValueEnum.ValueLanguageTaggedLiteral
        }
        is ValueSimpleLiteral -> {
            ValueEnum.ValueSimpleLiteral
        }
        is ValueTypedLiteral -> {
            ValueEnum.ValueTypedLiteral
        }
        is ValueUndef -> {
            ValueEnum.ValueUndef
        }
        else -> {
            throw Exception("type error")
        }
    }
}

class ExceptionTopLevelOperator(@JvmField val data: OPBase) : Exception("this object needs to be toplevel")

val MAX_SET = 10
val MAX_VARIABLES = 10
val MAX_LIMIT = 100
val MAX_OFFSET = 100
val MAX_TRIPLES = 100
val MAX_GRAPH_NAMES = 10
val testDictionaryVarName = ThreadSafeMutableList<String?>()
val testDictionaryValue = ThreadSafeMutableList<String?>()
val testDictionaryValueTyped = mutableMapOf<ValueEnum, ThreadSafeMutableList<String?>>()
var hadArrayIndexOutOfBoundsException = false
fun fromBinaryListOfVariables(query: Query, random: TestRandom, count: Int): MutableList<AOPVariable> {
    var res = mutableListOf<AOPVariable>()
    for (i in 0 until count)
        res.add(AOPVariable(query, nextStringVarName(query, random, res)))
    return res
}

fun nextStringVarName(query: Query, random: TestRandom, exclude: MutableList<AOPVariable> = mutableListOf()): String {
    while (true) {
        val v = AOPVariable(query, testDictionaryVarName[random.nextInt(testDictionaryVarName.size())]!!)
        var found = false
        for (c in exclude)
            if (c.name == v.name) {
                found = true
                break
            }
        if (!found)
            return v.name
    }
}

fun nextStringValue(query: Query, random: TestRandom): String {
    return testDictionaryValue[random.nextInt(testDictionaryValue.size())]!!
}

fun nextStringValueTyped(query: Query, random: TestRandom, type: ValueEnum): String {
    val idx = random.nextInt(testDictionaryValue.size())
    val tmp = ValueDefinition(testDictionaryValue[idx])
    if (ValueToID(tmp) == type)
        return testDictionaryValue[idx]!!
    return testDictionaryValueTyped[type]!![idx % testDictionaryValueTyped[type]!!.size()]!!
}

fun fromBinaryAOPConstOrVar(query: Query, random: TestRandom): AOPBase {
    try {
        if (random.nextInt(2) == 0)
            return AOPVariable(query, testDictionaryVarName[random.nextInt(testDictionaryVarName.size())]!!)
        return AOPConstant(query, ValueDefinition(testDictionaryValue[random.nextInt(testDictionaryValue.size())]!!))
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return AOPConstant(query, ValueUndef())
    }
}

fun fromBinaryValueIriOrVar(query: Query, random: TestRandom): AOPBase {
    try {
        if (random.nextInt(2) == 0)
            return AOPVariable(query, testDictionaryVarName[random.nextInt(testDictionaryVarName.size())]!!)
        val resultSet = testDictionaryValueTyped[ValueEnum.ValueIri]!!
        return AOPConstant(query, ValueDefinition(resultSet[random.nextInt(resultSet.size())]))
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        val resultSet = testDictionaryValueTyped[ValueEnum.ValueIri]!!
        return AOPConstant(query, ValueDefinition(resultSet[0]))
    }
}

fun fromBinaryValueIriOrBnodeOrVar(query: Query, random: TestRandom): AOPBase {
    try {
        var id = random.nextInt(3)
        when (id % 3) {
            0 -> return AOPVariable(query, testDictionaryVarName[random.nextInt(testDictionaryVarName.size())]!!)
            1 -> {
                val resultSet = testDictionaryValueTyped[ValueEnum.ValueIri]!!
                return AOPConstant(query, ValueDefinition(resultSet[random.nextInt(resultSet.size())]))
            }
            else -> {
                return AOPBuildInCallBNODE0(query)
            }
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return AOPBuildInCallBNODE0(query)
    }
}

suspend fun fromBinary(query: Query, random: TestRandom): OPBase {
    try {
        var id = random.nextInt(EOperatorID.values().size, false)
        val operatorID = EOperatorID.values()[id]
        if (operatorID == EOperatorID.OPEmptyRowID)
            return OPEmptyRow(query)
        if (EOperatorIDLOP.contains(operatorID))
            return fromBinaryLOP(query, random)
        if (EOperatorIDPOP.contains(operatorID))
            return fromBinaryPOP(query, random)
        if (EOperatorIDAOP.contains(operatorID))
            return fromBinaryAOP(query, random)
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
    }
    return OPEmptyRow(query)
}

suspend fun fromBinaryPOPLOP(query: Query, random: TestRandom): OPBase {
    try {
        val poploplist = EOperatorIDPOP + EOperatorIDLOP
        var id = random.nextInt(poploplist.size)
        val operatorID = poploplist[id]
        if (operatorID == EOperatorID.OPEmptyRowID)
            return OPEmptyRow(query)
        if (EOperatorIDLOP.contains(operatorID))
            return fromBinaryLOP(query, random)
        if (EOperatorIDPOP.contains(operatorID))
            return fromBinaryPOP(query, random)
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
    }
    return OPEmptyRow(query)
}

suspend fun fromBinaryPOP(query: Query, random: TestRandom): POPBase {
    try {
        var id = random.nextInt()
        if (id < 0)
            id = -id
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDPOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDPOP[id % EOperatorIDPOP.size]
        else
            operatorID = EOperatorID.values()[id]
        when (operatorID) {
            EOperatorID.POPServiceIRIID -> {
                return fromBinaryPOP(query, random)
            }
            EOperatorID.POPImportFromNetworkPackageID -> {
                return fromBinaryPOP(query, random)
            }
            EOperatorID.POPModifyDataID -> {
                val type = EModifyType.values()[random.nextInt(EModifyType.values().size)]
                val data = mutableListOf<LOPTriple>()
                val count = random.nextInt(MAX_TRIPLES)
                for (i in 0 until count)
                    data.add(fromBinaryLopTriple(query, random))
                throw ExceptionTopLevelOperator(POPModifyData(query, type, data))
            }
            EOperatorID.POPModifyID -> {
                val child = fromBinaryPOPLOP(query, random)
                val insert = mutableListOf<LOPTriple>()
                val delete = mutableListOf<LOPTriple>()
                val insertCount = random.nextInt(MAX_TRIPLES)
                for (i in 0 until insertCount)
                    insert.add(fromBinaryLopTriple(query, random))
                val deleteCount = random.nextInt(MAX_TRIPLES)
                for (i in 0 until deleteCount)
                    delete.add(fromBinaryLopTriple(query, random))
                throw ExceptionTopLevelOperator(POPModify(query, insert, delete, child))
            }
            EOperatorID.POPEmptyRowID -> {
                return POPEmptyRow(query)
            }
            EOperatorID.POPUnionID -> {
                val childA = fromBinaryPOPLOP(query, random)
                val childB = fromBinaryPOPLOP(query, random)
                return POPUnion(query, childA, childB)
            }
            EOperatorID.POPJoinHashMapID -> {
                val childA = fromBinaryPOPLOP(query, random)
                val childB = fromBinaryPOPLOP(query, random)
                val optional = DynamicByteArray.intToBool(random.nextInt(2))
                return POPJoinHashMap(query, childA, childB, optional)
            }
            EOperatorID.POPJoinNestedLoopID -> {
                val childA = fromBinaryPOPLOP(query, random)
                val childB = fromBinaryPOPLOP(query, random)
                val optional = DynamicByteArray.intToBool(random.nextInt(2))
                return POPJoinHashMap(query, childA, childB, optional)
            }
            EOperatorID.POPFilterID -> {
                val filter = fromBinaryAOP(query, random)
                val child = fromBinaryPOPLOP(query, random)
                return POPFilter(query, filter, child)
            }
            EOperatorID.POPMakeBooleanResultID -> {
                val child = fromBinaryPOPLOP(query, random)
                throw ExceptionTopLevelOperator(POPMakeBooleanResult(query, child))
            }
            EOperatorID.POPBindID -> {
                val name = AOPVariable(query, nextStringVarName(query, random))
                val value = fromBinaryAOP(query, random)
                val child = fromBinaryPOPLOP(query, random)
                return POPBind(query, name, value, child)
            }
            EOperatorID.POPSortID -> {
                val sortBy = AOPVariable(query, nextStringVarName(query, random))
                val sortOrder = DynamicByteArray.intToBool(random.nextInt(2))
                val child = fromBinaryPOPLOP(query, random)
                return POPSort(query, arrayOf(sortBy), sortOrder, child)
            }
            EOperatorID.POPDistinctID -> {
                val child = fromBinaryPOPLOP(query, random)
                return POPDistinct(query, child)
            }
            EOperatorID.POPGroupID -> {
                var bindings: POPBind? = null
                val byCount = random.nextInt(MAX_VARIABLES)
                val by = fromBinaryListOfVariables(query, random, byCount)
                val bindCount = random.nextInt(MAX_VARIABLES)
                val tmpList = mutableListOf<AOPVariable>()
                tmpList.addAll(by)
                for (i in 0 until bindCount) {
                    val name = AOPVariable(query, nextStringVarName(query, random, tmpList))
                    tmpList.add(name)
                    val value = fromBinaryAOP(query, random)
                    if (bindings == null)
                        bindings = POPBind(query, name, value, POPEmptyRow(query))
                    else
                        bindings = POPBind(query, name, value, bindings)
                }
                val child = fromBinaryPOPLOP(query, random)
                return POPGroup(query, by, bindings, child)
            }
            EOperatorID.POPProjectionID -> {
                val childCount = random.nextInt(MAX_VARIABLES)
                if (childCount == 0)
                    return fromBinaryPOP(query, random)
                val variables = fromBinaryListOfVariables(query, random, childCount)
                val child = fromBinaryPOPLOP(query, random)
                return POPProjection(query, variables, child)
            }
            EOperatorID.POPLimitID -> {
                var value = random.nextInt(MAX_LIMIT)
                val child = fromBinaryPOPLOP(query, random)
                return POPLimit(query, value, child)
            }
            EOperatorID.POPOffsetID -> {
                var value = random.nextInt(MAX_OFFSET)
                val child = fromBinaryPOPLOP(query, random)
                return POPOffset(query, value, child)
            }
            EOperatorID.POPGraphOperationID -> {
                val action = EGraphOperationType.values()[random.nextInt(EGraphOperationType.values().size)]
                val silent = DynamicByteArray.intToBool(random.nextInt(2))
                val graph1type = EGraphRefType.values()[random.nextInt(EGraphRefType.values().size)]
                val graph1nameTmp = (nextStringValueTyped(query, random, ValueEnum.ValueIri))
                val graph1name = graph1nameTmp.substring(1, graph1nameTmp.length - 1)
                val graph2type = EGraphRefType.values()[random.nextInt(EGraphRefType.values().size)]
                val graph2nameTmp = (nextStringValueTyped(query, random, ValueEnum.ValueIri))
                val graph2name = graph2nameTmp.substring(1, graph2nameTmp.length - 1)
                throw ExceptionTopLevelOperator(POPGraphOperation(query, silent, graph1type, graph1name, graph2type, graph2name, action))
            }
            EOperatorID.TripleStoreIteratorGlobalID -> {
                val graphNameTmp = (nextStringValueTyped(query, random, ValueEnum.ValueIri))
                val graphName = graphNameTmp.substring(1, graphNameTmp.length - 1)
                val graph = DistributedTripleStore.getNamedGraph(query, graphName)
                val s = fromBinaryValueIriOrBnodeOrVar(query, random)
                val p = fromBinaryValueIriOrVar(query, random)
                val o = fromBinaryAOPConstOrVar(query, random)
                val idx = EIndexPattern.values()[random.nextInt(EIndexPattern.values().size)]
                val tripleCount = random.nextInt(MAX_TRIPLES)
                val data = mutableListOf<List<String?>>()
                for (i in 0 until tripleCount) {
                    val row = mutableListOf<String?>()
                    for (j in 0 until 3) {
                        row.add(nextStringValue(query, random))
                    }
                    data.add(row)
                }
                val iterators = POPValues(query, listOf("s", "p", "o"), data).evaluate()
                graph.modify(arrayOf(iterators.columns["s"]!!, iterators.columns["p"]!!, iterators.columns["o"]!!), EModifyType.INSERT)
                query.commit()
                return DistributedTripleStore.getNamedGraph(query, graphName).getIterator(arrayOf(s, p, o), idx)
            }
            EOperatorID.POPValuesID -> {
                val variables = mutableListOf<String>()
                val values = mutableListOf<List<String?>>()
                val variableCount = random.nextInt(MAX_VARIABLES)
                for (i in 0 until variableCount)
                    variables.add(nextStringVarName(query, random))
                val valuesCount = random.nextInt(MAX_TRIPLES)
                for (j in 0 until valuesCount) {
                    val map = mutableListOf<String?>()
                    values.add(map)
                    for (i in 0 until variableCount) {
                        val isNull = DynamicByteArray.intToBool(random.nextInt(2))
                        if (!isNull)
                            map.add(nextStringValue(query, random))
                        else
                            map.add(null)
                    }
                }
                return POPValues(query, variables, values)
            }
            else -> throw Exception("BinaryHelper.fromBinaryPOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return POPEmptyRow(query)
    }
}

suspend fun fromBinaryLOP(query: Query, random: TestRandom): LOPBase {
    try {
        var id = random.nextInt()
        if (id < 0)
            id = -id
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDLOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDLOP[id % EOperatorIDLOP.size]
        else
            operatorID = EOperatorID.values()[id]
        when (operatorID) {
            EOperatorID.LOPServiceVARID -> {
//TODO
                return fromBinaryLOP(query, random)
            }
            EOperatorID.LOPServiceIRIID -> {
//TODO
                return fromBinaryLOP(query, random)
            }
            EOperatorID.LOPOptionalID -> {
//TODO
                return fromBinaryLOP(query, random)
            }
            EOperatorID.LOPReducedID -> {
                val child = fromBinaryPOPLOP(query, random)
                return LOPReduced(query, child)
            }
            EOperatorID.LOPPrefixID -> {
                val child = fromBinaryPOPLOP(query, random)
                val name = nextStringValueTyped(query, random, ValueEnum.ValueIri)
                val prefix = nextStringValueTyped(query, random, ValueEnum.ValueIri)
                return LOPPrefix(query, name.substring(1, name.length - 1), prefix.substring(1, prefix.length - 1), child)
            }
            EOperatorID.LOPGroupID -> {
                var bindings: POPBind? = null
                val byCount = random.nextInt(MAX_VARIABLES)
                val by = fromBinaryListOfVariables(query, random, byCount)
                val tmpList = mutableListOf<AOPVariable>()
                tmpList.addAll(by)
                val bindCount = random.nextInt(MAX_VARIABLES)
                for (i in 0 until bindCount) {
                    val name = AOPVariable(query, nextStringVarName(query, random, tmpList))
                    tmpList.add(name)
                    val value = fromBinaryAOP(query, random)
                    if (bindings == null)
                        bindings = POPBind(query, name, value, POPEmptyRow(query))
                    else
                        bindings = POPBind(query, name, value, bindings)
                }
                val child = fromBinaryPOPLOP(query, random)
                return LOPGroup(query, by, bindings, child)
            }
            EOperatorID.LOPNOOPID -> {
                val child = fromBinaryPOPLOP(query, random)
                return LOPNOOP(query, child)
            }
            EOperatorID.LOPSubGroupID -> {
                val child = fromBinaryPOPLOP(query, random)
                return LOPSubGroup(query, child)
            }
            EOperatorID.LOPModifyID -> {
                val child = fromBinaryPOPLOP(query, random)
                val insert = mutableListOf<LOPTriple>()
                val delete = mutableListOf<LOPTriple>()
                val insertCount = random.nextInt(MAX_TRIPLES)
                for (i in 0 until insertCount)
                    insert.add(fromBinaryLopTriple(query, random))
                val deleteCount = random.nextInt(MAX_TRIPLES)
                for (i in 0 until deleteCount)
                    delete.add(fromBinaryLopTriple(query, random))
                throw ExceptionTopLevelOperator(LOPModify(query, insert, delete, child))
            }
            EOperatorID.LOPModifyDataID -> {
                val type = EModifyType.values()[random.nextInt(EModifyType.values().size)]
                val data = mutableListOf<LOPTriple>()
                val count = random.nextInt(MAX_TRIPLES)
                for (i in 0 until count)
                    data.add(fromBinaryLopTriple(query, random))
                throw ExceptionTopLevelOperator(LOPModifyData(query, type, data))
            }
            EOperatorID.LOPGraphOperationID -> {
                val action = EGraphOperationType.values()[random.nextInt(EGraphOperationType.values().size)]
                val silent = DynamicByteArray.intToBool(random.nextInt(2))
                val graph1type = EGraphRefType.values()[random.nextInt(EGraphRefType.values().size)]
                val graph1name = nextStringValueTyped(query, random, ValueEnum.ValueIri)
                val graph2type = EGraphRefType.values()[random.nextInt(EGraphRefType.values().size)]
                val graph2name = nextStringValueTyped(query, random, ValueEnum.ValueIri)
                throw ExceptionTopLevelOperator(LOPGraphOperation(query, action, silent, graph1type, graph1name, graph2type, graph2name))
            }
            EOperatorID.LOPMakeBooleanResultID -> {
                val child = fromBinaryPOPLOP(query, random)
                throw ExceptionTopLevelOperator(LOPMakeBooleanResult(query, child))
            }
            EOperatorID.LOPUnionID -> {
                val childA = fromBinaryPOPLOP(query, random)
                val childB = fromBinaryPOPLOP(query, random)
                return LOPUnion(query, childA, childB)
            }
            EOperatorID.LOPJoinID -> {
                val childA = fromBinaryPOPLOP(query, random)
                val childB = fromBinaryPOPLOP(query, random)
                val optional = DynamicByteArray.intToBool(random.nextInt(2))
                return LOPJoin(query, childA, childB, optional)
            }
            EOperatorID.LOPFilterID -> {
                val filter = fromBinaryAOP(query, random)
                val child = fromBinaryPOPLOP(query, random)
                return LOPFilter(query, filter, child)
            }
            EOperatorID.LOPBindID -> {
                val name = AOPVariable(query, nextStringVarName(query, random))
                val value = fromBinaryAOP(query, random)
                val child = fromBinaryPOPLOP(query, random)
                return LOPBind(query, name, value, child)
            }
            EOperatorID.LOPSortID -> {
                val sortBy = AOPVariable(query, nextStringVarName(query, random))
                val sortOrder = DynamicByteArray.intToBool(random.nextInt(2))
                val child = fromBinaryPOPLOP(query, random)
                return LOPSort(query, sortOrder, sortBy, child)
            }
            EOperatorID.LOPDistinctID -> {
                val child = fromBinaryPOPLOP(query, random)
                return LOPDistinct(query, child)
            }
            EOperatorID.LOPProjectionID -> {
                val childCount = random.nextInt(MAX_VARIABLES)
                val variables = fromBinaryListOfVariables(query, random, childCount)
                val child = fromBinaryPOPLOP(query, random)
                return LOPProjection(query, variables, child)
            }
            EOperatorID.LOPLimitID -> {
                var value = random.nextInt(MAX_LIMIT)
                val child = fromBinaryPOPLOP(query, random)
                return LOPLimit(query, value, child)
            }
            EOperatorID.LOPOffsetID -> {
                var value = random.nextInt(MAX_OFFSET)
                val child = fromBinaryPOPLOP(query, random)
                return LOPOffset(query, value, child)
            }
            EOperatorID.LOPTripleID -> {
                return fromBinaryLopTriple(query, random)
            }
            EOperatorID.LOPValuesID -> {
                val values = mutableListOf<AOPValue>()
                val variableCount = random.nextInt(MAX_VARIABLES)
                val variables = fromBinaryListOfVariables(query, random, variableCount)
                val valuesCount = random.nextInt(MAX_TRIPLES)
                for (j in 0 until valuesCount) {
                    val list = mutableListOf<AOPConstant>()
                    for (i in 0 until variableCount) {
                        val isNull = DynamicByteArray.intToBool(random.nextInt(2))
                        if (!isNull) {
                            val value = nextStringValue(query, random)
                            list.add(AOPConstant(query, ValueDefinition(value)))
                        } else
                            list.add(AOPConstant(query, ValueUndef()))
                    }
                    values.add(AOPValue(query, list))
                }
                return LOPValues(query, variables, values)
            }
            else -> throw Exception("BinaryHelper.fromBinaryLOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return LOPTriple(query, AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), DistributedTripleStore.getGraphNames(true).last(), false)
    }
}

suspend fun fromBinaryLopTriple(query: Query, random: TestRandom): LOPTriple {
    val graphNameTmp = (nextStringValueTyped(query, random, ValueEnum.ValueIri))
    val graphName = graphNameTmp.substring(1, graphNameTmp.length - 1)
    val graph = DistributedTripleStore.getNamedGraph(query, graphName)
    var s = fromBinaryValueIriOrBnodeOrVar(query, random)
    var p = fromBinaryValueIriOrVar(query, random)
    var o = fromBinaryAOPConstOrVar(query, random)
    val idx = EIndexPattern.values()[random.nextInt(EIndexPattern.values().size)]
    val tripleCount = random.nextInt(MAX_TRIPLES)
    val data = mutableListOf<List<String?>>()
    for (i in 0 until tripleCount) {
        val row = mutableListOf<String?>()
        for (j in 0 until 3) {
            row.add(nextStringValue(query, random))
        }
        data.add(row)
    }
    val iterators = POPValues(query, listOf("s", "p", "o"), data).evaluate()
    graph.modify(arrayOf(iterators.columns["s"]!!, iterators.columns["p"]!!, iterators.columns["o"]!!), EModifyType.INSERT)
    query.commit()
    return LOPTriple(query, s, p, o, graphName, false)
}

fun fromBinaryAOP(query: Query, random: TestRandom): AOPBase {
    try {
        var id = random.nextInt()
        if (id < 0)
            id = -id
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDAOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDAOP[id % EOperatorIDAOP.size]
        else
            operatorID = EOperatorID.values()[id]
        when (operatorID) {
            EOperatorID.AOPBuildInCallIsIriID -> {
                val child = fromBinaryAOP(query, random)
                return AOPBuildInCallIsIri(query, child)
            }
            EOperatorID.AOPBuildInCallIsLITERALID -> {
                val child = fromBinaryAOP(query, random)
                return AOPBuildInCallIsLITERAL(query, child)
            }
            EOperatorID.AOPAndID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPAnd(query, childA, childB)
            }
            EOperatorID.AOPLEQID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPLEQ(query, childA, childB)
            }
            EOperatorID.AOPMultiplicationID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPMultiplication(query, childA, childB)
            }
            EOperatorID.AOPSetID -> {
                val childCount = random.nextInt(MAX_SET)
                val list = mutableListOf<AOPBase>()
                for (i in 0 until childCount)
                    list.add(fromBinaryAOP(query, random))
                return AOPSet(query, list)
            }
            EOperatorID.AOPValueID -> {
                val childCount = random.nextInt(MAX_SET)
                val list = mutableListOf<AOPConstant>()
                for (i in 0 until childCount) {
                    list.add(AOPConstant(query, ValueDefinition(nextStringValue(query, random))))
                }
                return AOPValue(query, list)
            }
            EOperatorID.AOPBuildInCallSTRUUIDID -> {
                return AOPBuildInCallSTRUUID(query)
            }
            EOperatorID.AOPBuildInCallUUIDID -> {
                return AOPBuildInCallUUID(query)
            }
            EOperatorID.AOPLTID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPLT(query, childA, childB)
            }
            EOperatorID.AOPGTID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPGT(query, childA, childB)
            }
            EOperatorID.AOPSubtractionID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPSubtraction(query, childA, childB)
            }
            EOperatorID.AOPNEQID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPNEQ(query, childA, childB)
            }
            EOperatorID.AOPNotID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPNot(query, childA)
            }
            EOperatorID.AOPInID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPIn(query, childA, childB)
            }
            EOperatorID.AOPNotInID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPNotIn(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallBOUNDID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallBOUND(query, childA)
            }
            EOperatorID.AOPBuildInCallTIMEZONEID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallTIMEZONE(query, childA)
            }
            EOperatorID.AOPAdditionID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPAddition(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallCONTAINSID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPBuildInCallCONTAINS(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallIsNUMERICID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallIsNUMERIC(query, childA)
            }
            EOperatorID.AOPBuildInCallLANGMATCHESID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPBuildInCallLANGMATCHES(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRENDSID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPBuildInCallSTRENDS(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRSTARTSID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPBuildInCallSTRSTARTS(query, childA, childB)
            }
            EOperatorID.AOPEQID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPEQ(query, childA, childB)
            }
            EOperatorID.AOPGEQID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPGEQ(query, childA, childB)
            }
            EOperatorID.AOPOrID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPOr(query, childA, childB)
            }
            EOperatorID.AOPVariableID -> {
                val name = nextStringVarName(query, random)
                return AOPVariable(query, name)
            }
            EOperatorID.AOPBuildInCallABSID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallABS(query, childA)
            }
            EOperatorID.AOPBuildInCallBNODE0ID -> {
                return AOPBuildInCallBNODE0(query)
            }
            EOperatorID.AOPBuildInCallBNODE1ID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallBNODE1(query, childA)
            }
            EOperatorID.AOPBuildInCallCEILID -> {
                val childA = fromBinaryAOP(query, random)
                if (childA is AOPBuildInCallCEIL)
                    return childA
                return AOPBuildInCallCEIL(query, childA)
            }
            EOperatorID.AOPBuildInCallCONCATID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPBuildInCallCONCAT(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallDATATYPEID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallDATATYPE(query, childA)
            }
            EOperatorID.AOPBuildInCallDAYID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallDAY(query, childA)
            }
            EOperatorID.AOPBuildInCallFLOORID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallFLOOR(query, childA)
            }
            EOperatorID.AOPBuildInCallHOURSID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallHOURS(query, childA)
            }
            EOperatorID.AOPBuildInCallIFID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                val childC = fromBinaryAOP(query, random)
                return AOPBuildInCallIF(query, childA, childB, childC)
            }
            EOperatorID.AOPBuildInCallIRIID -> {
                val childA = fromBinaryAOP(query, random)
                val prefix = nextStringValue(query, random)
                return AOPBuildInCallIRI(query, childA, prefix)
            }
            EOperatorID.AOPBuildInCallLANGID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallLANG(query, childA)
            }
            EOperatorID.AOPBuildInCallLCASEID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallLCASE(query, childA)
            }
            EOperatorID.AOPBuildInCallMD5ID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallMD5(query, childA)
            }
            EOperatorID.AOPBuildInCallMINUTESID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallMINUTES(query, childA)
            }
            EOperatorID.AOPBuildInCallMONTHID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallMONTH(query, childA)
            }
            EOperatorID.AOPBuildInCallROUNDID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallROUND(query, childA)
            }
            EOperatorID.AOPBuildInCallSECONDSID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallSECONDS(query, childA)
            }
            EOperatorID.AOPBuildInCallSHA1ID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallSHA1(query, childA)
            }
            EOperatorID.AOPBuildInCallSHA256ID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallSHA256(query, childA)
            }
            EOperatorID.AOPBuildInCallSTRDTID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPBuildInCallSTRDT(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallSTR(query, childA)
            }
            EOperatorID.AOPBuildInCallSTRLANGID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPBuildInCallSTRLANG(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRLENID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallSTRLEN(query, childA)
            }
            EOperatorID.AOPBuildInCallTZID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallTZ(query, childA)
            }
            EOperatorID.AOPBuildInCallUCASEID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallUCASE(query, childA)
            }
            EOperatorID.AOPBuildInCallURIID -> {
                val childA = fromBinaryAOP(query, random)
                val prefix = nextStringValue(query, random)
                return AOPBuildInCallURI(query, childA, prefix)
            }
            EOperatorID.AOPBuildInCallYEARID -> {
                val childA = fromBinaryAOP(query, random)
                return AOPBuildInCallYEAR(query, childA)
            }
            EOperatorID.AOPConstantID -> {
                return AOPConstant(query, ValueDefinition(nextStringValue(query, random)))
            }
            EOperatorID.AOPAggregationCOUNTID -> {
                val distinct = DynamicByteArray.intToBool(random.nextInt(2))
                val count = random.nextInt(2)
                val variables = Array(count) { fromBinaryAOP(query, random) }
                return AOPAggregationCOUNT(query, distinct, variables)
            }
            EOperatorID.AOPAggregationSAMPLEID -> {
                val distinct = DynamicByteArray.intToBool(random.nextInt(2))
                val count = random.nextInt(2)
                val variables = Array(count) { fromBinaryAOP(query, random) }
                return AOPAggregationSAMPLE(query, distinct, variables)
            }
            EOperatorID.AOPAggregationSUMID -> {
                val distinct = DynamicByteArray.intToBool(random.nextInt(2))
                val count = random.nextInt(2)
                val variables = Array(count) { fromBinaryAOP(query, random) }
                return AOPAggregationSUM(query, distinct, variables)
            }
            EOperatorID.AOPAggregationAVGID -> {
                val distinct = DynamicByteArray.intToBool(random.nextInt(2))
                val count = random.nextInt(2)
                val variables = Array(count) { fromBinaryAOP(query, random) }
                return AOPAggregationAVG(query, distinct, variables)
            }
            EOperatorID.AOPAggregationMINID -> {
                val distinct = DynamicByteArray.intToBool(random.nextInt(2))
                val count = random.nextInt(2)
                val variables = Array(count) { fromBinaryAOP(query, random) }
                return AOPAggregationMIN(query, distinct, variables)
            }
            EOperatorID.AOPAggregationMAXID -> {
                val distinct = DynamicByteArray.intToBool(random.nextInt(2))
                val count = random.nextInt(2)
                val variables = Array(count) { fromBinaryAOP(query, random) }
                return AOPAggregationMAX(query, distinct, variables)
            }
            EOperatorID.AOPDivisionID -> {
                val childA = fromBinaryAOP(query, random)
                val childB = fromBinaryAOP(query, random)
                return AOPDivision(query, childA, childB)
            }
            else -> throw Exception("BinaryHelper.fromBinaryAOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return AOPConstant(query, ValueUndef())
    }
}

suspend fun executeBinaryTests(folder: String) {
    var testcases = 0
    try {
        File(folder).walk {
            if (it.endsWith(".bin")) {
                testcases++
                val tmp = DistributedTripleStore.getGraphNames().toList()
                val query = Query()
                tmp.forEach {
                    DistributedTripleStore.dropGraph(query, it)
                }
                query.commit()
                executeBinaryTest(it, false)
            }
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    println("executed testcases : $testcases")
}

suspend fun executeBinaryTest(filename: String, detailedLog: Boolean) {
    val buffer = File(filename).readAsDynamicByteArray()
    executeBinaryTest(TestRandom(buffer))
}

suspend fun executeBinaryTest(random: TestRandom) {
    val query = Query()
    var node1: OPBase = OPEmptyRow(query)
    var node2: OPBase = OPEmptyRow(query)
    var node3: OPBase = OPEmptyRow(query)
    var node4: OPBase = OPEmptyRow(query)
    var node5: OPBase = OPEmptyRow(query)
    val lOptimizer = LogicalOptimizer(query)
    val pOptimizer = PhysicalOptimizer(query)
    val dOptimizer = KeyDistributionOptimizer(query)
    try {
        val optimizerEnabledCount = random.nextInt(EOptimizerID.values().size)
        ExecuteOptimizer.enabledOptimizers.clear()
        for (o in 0 until optimizerEnabledCount) {
            val optimizer = EOptimizerID.values()[random.nextInt(EOptimizerID.values().size)]
            ExecuteOptimizer.enabledOptimizers[optimizer] = true
        }
    } catch (e: Throwable) {
    }
    val backupOptimizers = ExecuteOptimizer.enabledOptimizers
    ExecuteOptimizer.enabledOptimizers.clear()
    var globalSparql = mutableListOf<String>()
    hadArrayIndexOutOfBoundsException = false
    val totalRandomAvailable = random.randomAvailable()
    while (!hadArrayIndexOutOfBoundsException) {
        try {
            var node1: OPBase
            try {
                if (random.randomAvailable() < totalRandomAvailable / 2) {
                    try {
                        val graphNameTmp = (nextStringValueTyped(query, random, ValueEnum.ValueIri))
                        val graphName = graphNameTmp.substring(1, graphNameTmp.length - 1)
                        val graph = DistributedTripleStore.getNamedGraph(query, graphName)
                        val s = fromBinaryValueIriOrBnodeOrVar(query, random)
                        val p = fromBinaryValueIriOrVar(query, random)
                        val o = fromBinaryAOPConstOrVar(query, random)
                        val idx = EIndexPattern.values()[random.nextInt(EIndexPattern.values().size)]
                        val tripleCount = random.nextInt(MAX_TRIPLES)
                        val data = mutableListOf<List<String?>>()
                        for (i in 0 until tripleCount) {
                            val row = mutableListOf<String?>()
                            for (j in 0 until 3) {
                                row.add(nextStringValue(query, random))
                            }
                            data.add(row)
                        }
                        val iterators = POPValues(query, listOf("s", "p", "o"), data).evaluate()
                        graph.modify(arrayOf(iterators.columns["s"]!!, iterators.columns["p"]!!, iterators.columns["o"]!!), EModifyType.INSERT)
                    } catch (e: Throwable) {
                    }
                    query.commit()
                }
                node1 = OPEmptyRow(query)
                node2 = OPEmptyRow(query)
                node3 = OPEmptyRow(query)
                node4 = OPEmptyRow(query)
                node1 = fromBinaryPOPLOP(query, random)
            } catch (e: ExceptionTopLevelOperator) {
                node1 = e.data
            }
            node2 = lOptimizer.optimizeCall(node1)
            node3 = pOptimizer.optimizeCall(node2)
            node4 = dOptimizer.optimizeCall(node3)
            val sparql = node4.toSparqlQuery()
            globalSparql.add(sparql)
        } catch (e: Throwable) {
        }
    }
    for (gname in DistributedTripleStore.getGraphNames(true)) {
        val g = DistributedTripleStore.getNamedGraph(query, gname)
        val iterator = g.getIterator(arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPattern.SPO)
        val data = QueryResultToXML.toXML(iterator)
        var hasData = false
        var sparql = "INSERT DATA { "
        if (gname != PersistentStoreLocal.defaultGraphName)
            sparql += "GRAPH <$gname> "
        if (data.tag != "sparql")
            throw Exception("can only parse sparql xml into an iterator")
        for (node in data["results"]!!.childs) {
            val result = mutableMapOf<String, String>()
            for (v in node.childs) {
                val name = v.attributes["name"]
                val child = v.childs.first()
                val content = child.content
                val value = when {
                    child.tag == "uri" -> "<" + content + ">"
                    child.tag == "literal" && child.attributes["datatype"] != null -> "\"" + content + "\"^^<" + child.attributes["datatype"] + ">"
                    child.tag == "literal" && child.attributes["xml:lang"] != null -> "\"" + content + "\"@" + child.attributes["xml:lang"]
                    child.tag == "bnode" -> "_:" + content
                    else -> "\"" + content + "\""
                }
                result[name!!] = value!!
            }
            hasData = true
            sparql += "( " + result["s"] + " " + result["p"] + " " + result["o"] + " " + ")."
        }
        sparql += "}"
        if (hasData)
            globalSparql.add(0, sparql)
    }
    for (i in 0 until 3) {
        ExecuteOptimizer.enabledOptimizers.clear()
        if (1 > 0)
            backupOptimizers.forEach { k, v ->
                ExecuteOptimizer.enabledOptimizers[k] = v
            }
        val jena = JenaRequest()
        ServerCommunicationSend.graphClearAll(query)
        query.commit()
        try {
            for (sparql in globalSparql) {
                var e1: Throwable? = null
                var e2: Throwable? = null
                var output = XMLElement("crashed")
                var isUpdate = false
                node1 = OPEmptyRow(query)
                node2 = OPEmptyRow(query)
                node3 = OPEmptyRow(query)
                node4 = OPEmptyRow(query)
                node5 = OPEmptyRow(query)
                try {
                    //println("sparql::" + sparql)
                    val lcit = LexerCharIterator(sparql)
                    val tit = TokenIteratorSPARQLParser(lcit)
                    val ltit = LookAheadTokenIterator(tit, 3)
                    val parser = SPARQLParser(ltit)
                    val ast_node = parser.expr()
                    node1 = ast_node.visit(OperatorGraphVisitor(query))
                    node2 = lOptimizer.optimizeCall(node1)
                    node3 = pOptimizer.optimizeCall(node2)
                    node4 = dOptimizer.optimizeCall(node3)
                    node5 = node4.cloneOP()
                    SanityCheck.checkEQ({ node4 }, { node5 })
                    node5 = node1.cloneOP()
                    SanityCheck.checkEQ({ node1 }, { node5 })
                    val node1xml = node1.toXMLElement()
                    node5 = XMLElement.convertToOPBase(query, node1xml)
                    SanityCheck.checkEQ({ node1 }, { node5 })
                    isUpdate = node4 is POPGraphOperation || node4 is POPModifyData || node4 is POPModify
                    output = QueryResultToXML.toXML(node4)
                    query.commit()
                } catch (e: Throwable) {
                    e1 = e
                }
                if (isUpdate) {
                    try {
                        val expected = jena.requestUpdate(sparql)
                    } catch (e: Throwable) {
                        if (e is ExceptionJenaBug)
                            throw e
                        e2 = e
                    }
                } else {
                    var expected = XMLElement("crashed")
                    try {
                        expected = jena.requestQuery(sparql)
                    } catch (e: Throwable) {
                        if (e is ExceptionJenaBug)
                            throw e
                        e2 = e
                    }
                    if (!expected.myEqualsUnclean(output, true, true, true)) {
                        println(node1)
                        println(node2)
                        println(node3)
                        println(node4)
                        println(node5)
                        println("expected :: $expected")
                        println("output :: $output")
                        e1?.printStackTrace()
                        e2?.printStackTrace()
                        throw Exception("failed $sparql")
                    }
                }
            }
        } catch (e: ExceptionJenaBug) {
        } finally {
            jena.finalize()
        }
    }
}
