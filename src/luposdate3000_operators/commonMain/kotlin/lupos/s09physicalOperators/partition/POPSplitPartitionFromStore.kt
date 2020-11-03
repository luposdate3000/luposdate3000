package lupos.s09physicalOperators.partition

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPSplitPartitionFromStore(query: IQuery, projectedVariables: List<String>, val partitionVariable: String, val partitionCount:Int,child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPSplitPartitionFromStoreID, "POPSplitPartitionFromStore", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        if (variable == partitionVariable) {
            return partitionCount
        } else {
            return 1
        }
    }

    override suspend fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCount", ""+partitionCount)
        return res
    }

    override fun getRequiredVariableNames(): List<String> = listOf<String>()
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> {
        val tmp = children[0]
        if (tmp is POPBase) {
            return tmp.getProvidedVariableNamesInternal()
        } else {
            return tmp.getProvidedVariableNames()
        }
    }

    override fun cloneOP(): IOPBase = POPSplitPartitionFromStore(query, projectedVariables, partitionVariable, partitionCount,children[0].cloneOP())
    override fun toSparql() = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPSplitPartitionFromStore && children[0] == other.children[0] && partitionVariable == other.partitionVariable&&partitionCount==other.partitionCount
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        return children[0].evaluate(parent)
    }
}
