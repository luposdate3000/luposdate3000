package lupos.s09physicalOperators.singleinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
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
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
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



class POPBind(query: Query, projectedVariables: List<String>, @JvmField val name: AOPVariable, value: AOPBase, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPBindID, "POPBind", arrayOf(child, value), ESortPriority.BIND) {
    override fun toSparql(): String {
        if (children[1] is AOPConstant && (children[1] as AOPConstant).value == ResultSetDictionary.undefValue) {
            return children[0].toSparql()
        }
        var res = "{SELECT "
        for (v in children[0].getProvidedVariableNames()) {
            res += AOPVariable(query, v).toSparql() + " "
        }
        res += "(" + children[1].toSparql() + " as " + name.toSparql() + "){"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP() = POPBind(query, projectedVariables, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPBind && name == other.name && children[0] == other.children[0]
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNamesInternal(): List<String> = (children[0].getProvidedVariableNames() + name.name).distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive()
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
    override suspend fun evaluate(): ColumnIteratorRow {
        val variablesOut = getProvidedVariableNames()
        val variablesLocal = getProvidedVariableNamesInternal()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val columnsIn = Array(variablesLocal.size) { child.columns[variablesLocal[it]] }
        val columnsLocal = Array(variablesLocal.size) { ColumnIteratorQueue() }
        var boundIndex = -1
        for (variableIndex in 0 until variablesLocal.size) {
            localMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
            outMap[variablesLocal[variableIndex]] = ColumnIteratorDebug(uuid, variablesLocal[variableIndex], columnsLocal[variableIndex])
            if (variablesLocal[variableIndex] == name.name) {
                boundIndex = variableIndex
            }
        }
        val columnsOut = Array(variablesOut.size) {
            /*return*/            localMap[variablesOut[it]] as ColumnIteratorQueue
        }
        val res = ColumnIteratorRow(outMap)
        val expression = (children[1] as AOPBase).evaluate(ColumnIteratorRow(localMap))
        SanityCheck.check { variablesLocal.size != 0 }
        if (variablesLocal.size == 1 && children[0].getProvidedVariableNames().size == 0) {
            val columnBound = ColumnIteratorRepeatValue(child.count, query.dictionary.createValue(expression()))
            outMap[name.name] = ColumnIteratorDebug(uuid, name.name, columnBound)
        } else {
            SanityCheck.check { boundIndex != -1 }
            for (variableIndex in 0 until variablesLocal.size) {
                columnsLocal[variableIndex].onEmptyQueue = {
                    var done = false
                    for (variableIndex2 in 0 until variablesLocal.size) {
                        if (boundIndex != variableIndex2) {
                            val value = columnsIn[variableIndex2]!!.next()
                            if (value == null) {
                                SanityCheck.check { variableIndex2 == 0 || (boundIndex == 0 && variableIndex2 == 1) }
                                for (variableIndex3 in 0 until variablesLocal.size) {
                                    columnsLocal[variableIndex3].onEmptyQueue = columnsLocal[variableIndex3]::_onEmptyQueue
                                }
                                done = true
                                break
                            }
//point each iterator to the current value
                            columnsLocal[variableIndex2].tmp = value
                        }
                    }
                    if (!done) {
                        columnsLocal[boundIndex].tmp = query.dictionary.createValue(expression())
                        for (variableIndex2 in 0 until columnsOut.size) {
                            columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp!!)
                        }
                    }
                }
            }
        }
        return res
    }
}
