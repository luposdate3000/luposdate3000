package lupos.s09physicalOperators.noinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.Partition
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase
public class POPEmptyRow public constructor(query: IQuery, projectedVariables: List<String>) : POPBase(query, projectedVariables, EOperatorIDExt.POPEmptyRowID, "POPEmptyRow", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = 1
    override fun cloneOP(): IOPBase = POPEmptyRow(query, projectedVariables)
    override fun toSparql(): String = "{}"
    override fun equals(other: Any?): Boolean = other is POPEmptyRow
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        return IteratorBundle(1)
    }
}
