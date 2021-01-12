package lupos.s09physicalOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueueEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorQueueExt
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField
public class POPBind public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val name: AOPVariable, value: AOPBase, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPBindID, "POPBind", arrayOf(child, value), ESortPriorityExt.BIND) {
    override fun getPartitionCount(variable: String): Int {
        return if (variable == name.name) {
            1
        } else {
            children[0].getPartitionCount(variable)
        }
    }
    override fun toSparql(): String {
        if (children[1] is AOPConstant && (children[1] as AOPConstant).value == ResultSetDictionaryExt.undefValue) {
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
    override fun cloneOP(): IOPBase = POPBind(query, projectedVariables, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPBind && name == other.name && children[0] == other.children[0]
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNamesInternal(): List<String> = (children[0].getProvidedVariableNames() + name.name).distinct()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive()
    override /*suspend*/ fun toXMLElement(): XMLElement = super.toXMLElement().addAttribute("name", name.name)
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variablesOut = getProvidedVariableNames()
        val variablesLocal = getProvidedVariableNamesInternal()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        val columnsLocal = Array<ColumnIteratorQueue>(variablesLocal.size) { ColumnIteratorQueueEmpty() }
        var expression: () -> ValueDefinition = { ValueError() }
        val columnsOut = Array<ColumnIteratorQueue>(variablesOut.size) { ColumnIteratorQueueEmpty() }
        if (variablesLocal.size == 1 && children[0].getProvidedVariableNames().isEmpty()) {
            outMap[name.name] = ColumnIteratorRepeatValue(child.count(), query.getDictionary().createValue(expression()))
        } else {
            var boundIndex = -1
            for (variableIndex in variablesLocal.indices) {
                if (variablesLocal[variableIndex] == name.name) {
                    boundIndex = variableIndex
                }
            }
            SanityCheck.check { boundIndex != -1 }
            val columnsIn = Array(variablesLocal.size) { child.columns[variablesLocal[it]] }
            for (variableIndex in variablesLocal.indices) {
                columnsLocal[variableIndex] = object : ColumnIteratorQueue() {
                    override /*suspend*/ fun close() {
                        ColumnIteratorQueueExt._close(this)
                    }
                    override /*suspend*/ fun next(): Int {
                        return ColumnIteratorQueueExt.nextHelper(
                            this,
                            {
                                var done = false
                                for (variableIndex2 in variablesLocal.indices) {
                                    if (boundIndex != variableIndex2) {
                                        val value = columnsIn[variableIndex2]!!.next()
                                        if (value == ResultSetDictionaryExt.nullValue) {
                                            SanityCheck.check { variableIndex2 == 0 || (boundIndex == 0 && variableIndex2 == 1) }
                                            for (variableIndex3 in 0 until variablesLocal.size) {
                                                ColumnIteratorQueueExt.closeOnEmptyQueue(columnsLocal[variableIndex3])
                                            }
                                            for (closeIndex in variablesLocal.indices) {
                                                if (boundIndex != closeIndex) {
                                                    columnsIn[closeIndex]!!.close()
                                                }
                                            }
                                            done = true
                                            break
                                        }
// point each iterator to the current value
                                        columnsLocal[variableIndex2].tmp = value
                                    }
                                }
                                if (!done) {
                                    columnsLocal[boundIndex].tmp = query.getDictionary().createValue(expression())
                                    for (variableIndex2 in columnsOut.indices) {
                                        columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp)
                                    }
                                }
                            },
                            { ColumnIteratorQueueExt._close(this) }
                        )
                    }
                }
            }
        }
        for (variableIndex in variablesLocal.indices) {
            localMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
            if (projectedVariables.contains(variablesLocal[variableIndex])) {
                outMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
            }
        }
        for (it in variablesOut.indices) {
            columnsOut[it] = localMap[variablesOut[it]] as ColumnIteratorQueue
        }
        expression = (children[1] as AOPBase).evaluate(IteratorBundle(localMap))
        SanityCheck.check { variablesLocal.isNotEmpty() }
        return IteratorBundle(outMap)
    }
}
