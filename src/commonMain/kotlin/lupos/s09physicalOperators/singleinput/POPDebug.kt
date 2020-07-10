package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMerge
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.iterator.RowIteratorMerge
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPDebug(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPDebugID, "POPDebug", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?): Boolean = other is POPDebug && children[0] == other.children[0]
    override fun cloneOP() = POPDebug(query, projectedVariables, children[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = listOf<String>()
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> = (children[0]as POPBase).getProvidedVariableNamesInternal()
    override fun toSparql(): String = children[0].toSparql()

    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val child = children[0].evaluate(parent)
        val target = children[0].getProvidedVariableNames()
SanityCheck.println({"POPDebug-child-mode ... ${uuid} ${children[0].uuid} ${child.mode}"})
        if (child.hasColumnMode()) {
            val columnMode = mutableListOf<String>()
            for ((k, v) in child.columns) {
                columnMode.add(k)
            }
            SanityCheck { columnMode.containsAll(target) }
            SanityCheck { target.containsAll(columnMode) }
        } else if (child.hasRowMode()){
            val rowMode = child.rows.columns.toMutableList()
            SanityCheck { rowMode.containsAll(target) }
            SanityCheck { target.containsAll(rowMode) }
        }
        return child
    }
}
