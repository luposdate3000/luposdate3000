package lupos.s09physicalOperators.singleinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
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
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.iterator.ColumnIteratorStore1
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3c
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase




class POPFilter(query: Query, projectedVariables: List<String>, filter: AOPBase, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPFilterID, "POPFilter", arrayOf(child, filter), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return "{SELECT * {" + sparql + ". FILTER (" + children[1].toSparql() + ")}}"
        }
        return "{SELECT * {" + sparql + " FILTER (" + children[1].toSparql() + ")}}"
    }

    override fun equals(other: Any?): Boolean = other is POPFilter && children[0] == other.children[0] && children[1] == other.children[1]
    override fun childrenToVerifyCount() = 1
    override fun cloneOP() = POPFilter(query, projectedVariables, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun getProvidedVariableNamesInternal() = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames() = children[1].getRequiredVariableNamesRecoursive()
    override suspend fun evaluate(): ColumnIteratorRow {
//TODO not-equal shortcut during evaluation based on integer-ids
        val variables = children[0].getProvidedVariableNames()
        val variablesOut = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val columnsIn = Array(variables.size) { child.columns[variables[it]] }
        val columnsLocal = Array(variables.size) { ColumnIteratorQueue() }
        for (variableIndex in 0 until variables.size) {
            outMap[variables[variableIndex]] = ColumnIteratorDebug(uuid, variables[variableIndex], columnsLocal[variableIndex])
            localMap[variables[variableIndex]] = columnsLocal[variableIndex]
        }
        val res = ColumnIteratorRow(outMap)
        val resLocal = ColumnIteratorRow(localMap)
        val columnsOut = Array(variablesOut.size) { resLocal.columns[variablesOut[it]]!! as ColumnIteratorQueue }
        val expression = (children[1] as AOPBase).evaluate(resLocal)
        if (variablesOut.size == 0) {
            if (variables.size == 0) {
                val value = expression()
                res.hasNext = {
                    /*return*/false
                }
                try {
                    if (value.toBoolean()) {
                        res.hasNext = child.hasNext
                    }
                } catch (e: Throwable) {
                }
            } else {
                res.hasNext = {
                    var res2 = false
                    var done = false
                    while (!done) {
                        for (variableIndex2 in 0 until variables.size) {
                            columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
//point each iterator to the current value
                            if (columnsLocal[variableIndex2].tmp == null) {
                                SanityCheck.check { variableIndex2 == 0 }
                                for (variableIndex3 in 0 until variables.size) {
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
                                }
                                done = true
                                break
                            }
                        }
                        if (!done) {
//evaluate
                            val value = expression()
                            try {
                                if (value.toBoolean()) {
//accept/deny row in each iterator
                                    res2 = true
                                }
                            } catch (e: Throwable) {
                            }
                        }
                    }
/*return*/res2
                }
            }
        } else {
            for (variableIndex in 0 until variables.size) {
                columnsLocal[variableIndex].onEmptyQueue = {
                    var done = false
                    while (!done) {
                        for (variableIndex2 in 0 until variables.size) {
                            columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
//point each iterator to the current value
                            if (columnsLocal[variableIndex2].tmp == null) {
                                SanityCheck.check { variableIndex2 == 0 }
                                for (variableIndex3 in 0 until variables.size) {
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
                                }
                                done = true
                                break
                            }
                        }
                        if (!done) {
//evaluate
                            val value = expression()
                            try {
                                if (value.toBoolean()) {
//accept/deny row in each iterator
                                    for (variableIndex2 in 0 until variablesOut.size) {
                                        columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp!!)
                                    }
                                    done = true
                                }
                            } catch (e: Throwable) {
                            }
                        }
                    }
                }
            }
        }
        return res
    }
}
