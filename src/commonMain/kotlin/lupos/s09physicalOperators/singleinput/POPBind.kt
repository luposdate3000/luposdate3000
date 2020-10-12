package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueueEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPBind(query: Query, projectedVariables: List<String>, @JvmField val name: AOPVariable, value: AOPBase, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPBindID, "POPBind", arrayOf(child, value), ESortPriority.BIND) {
    override fun getPartitionCount(variable: String): Int {
if(variable==name.name){
return 1
}else{
 return children[0].getPartitionCount(variable)
}
}
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
    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val variablesOut = getProvidedVariableNames()
        val variablesLocal = getProvidedVariableNamesInternal()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        val columnsLocal = Array<ColumnIteratorQueue>(variablesLocal.size) { ColumnIteratorQueueEmpty() }
        var expression: () -> ValueDefinition = { ValueError() }
        val columnsOut = Array<ColumnIteratorQueue>(variablesOut.size) { ColumnIteratorQueueEmpty() }
        if (variablesLocal.size == 1 && children[0].getProvidedVariableNames().size == 0) {
            outMap[name.name] = ColumnIteratorRepeatValue(child.count(), query.dictionary.createValue(expression()))
        } else {
            var boundIndex = -1
            for (variableIndex in 0 until variablesLocal.size) {
                if (variablesLocal[variableIndex] == name.name) {
                    boundIndex = variableIndex
                }
            }
            SanityCheck.check { boundIndex != -1 }
            val columnsIn = Array(variablesLocal.size) { child.columns[variablesLocal[it]] }
            for (variableIndex in 0 until variablesLocal.size) {
                columnsLocal[variableIndex] = object : ColumnIteratorQueue() {
                    override suspend fun close() {
                        _close()
                    }

                    override suspend fun next(): Value {
                        return next_helper({
                            var done = false
                            for (variableIndex2 in 0 until variablesLocal.size) {
                                if (boundIndex != variableIndex2) {
                                    val value = columnsIn[variableIndex2]!!.next()
                                    if (value == ResultSetDictionary.nullValue) {
                                        SanityCheck.check { variableIndex2 == 0 || (boundIndex == 0 && variableIndex2 == 1) }
                                        for (variableIndex3 in 0 until variablesLocal.size) {
                                            columnsLocal[variableIndex3].closeOnEmptyQueue()
                                        }
                                        for (closeIndex in 0 until variablesLocal.size) {
                                            if (boundIndex != closeIndex) {
                                                columnsIn[closeIndex]!!.close()
                                            }
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
                                    columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp)
                                }
                            }
                        }, { _close() })
                    }
                }
            }
        }
        for (variableIndex in 0 until variablesLocal.size) {
            localMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
            if (projectedVariables.contains(variablesLocal[variableIndex])) {
                outMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
            }
        }
        for (it in 0 until variablesOut.size) {
            columnsOut[it] = localMap[variablesOut[it]] as ColumnIteratorQueue
        }
        expression = (children[1] as AOPBase).evaluate(IteratorBundle(localMap))
        SanityCheck.check { variablesLocal.size != 0 }
        return IteratorBundle(outMap)
    }
}
